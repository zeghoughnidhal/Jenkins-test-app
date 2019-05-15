package com.cloudwatt.example.service;

import com.cloudwatt.example.ApplicationConfiguration;
import com.cloudwatt.example.domain.jenkins.HudsonFolder;
import com.cloudwatt.example.domain.jenkins.HudsonNode;
import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class FolderService {

    @Autowired
    private ApplicationConfiguration configuration;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceProperties serviceProperties;

    private Logger logger = Logger.getLogger("");

    private LoadingCache<String, HudsonFolder> cacheFolders;
    private LoadingCache<String, HudsonJob> cacheJobs;

    public FolderService() {

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        cacheFolders = CacheBuilder.newBuilder()
                // .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, HudsonFolder>() {
                            public HudsonFolder load(String key) {
                                logger.info("Call Jenkins on : " + key);
                                UriComponentsBuilder builder = UriComponentsBuilder
                                        .fromHttpUrl(key + "/api/json");
                               return restTemplate.getForObject(builder.build().toString(), HudsonFolder.class);
                            }
                        });

        cacheJobs = CacheBuilder.newBuilder()
                // .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, HudsonJob>() {
                            public HudsonJob load(String key) {
                                logger.info("Call Jenkins on : " + key);
                                UriComponentsBuilder builder = UriComponentsBuilder
                                        .fromHttpUrl(key + "/api/json");
                                return restTemplate.getForObject(builder.build().toString(), HudsonJob.class);
                            }
                        });
    }

    @Cacheable
    public HudsonFolder getFolder(String projectName, Integer depth) {

        String fooResourceUrl = configuration.getUrl();

        Map<String, Integer> queryParams = Maps.newHashMap();
        queryParams.put("depth", depth);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(fooResourceUrl + projectName + "/api/json")
                .queryParam("depth", depth);

        HudsonFolder response = restTemplate.getForObject(builder.build().toString(), HudsonFolder.class);

        for (HudsonNode j : response.getJobs()) {
            j.setEnv(extractEnvFrom(j.getName()));
        }

        return response;
    }

    public Map<String, Object> getFolderForView(String folderPath) throws ExecutionException {

        HashMap<String, Object> folderForView = Maps.newHashMap();
        List<String> folders = Lists.newArrayList();
        List<HudsonJob> jobs = Lists.newArrayList();

        HudsonFolder folder = cacheFolders.get(configuration.getUrl() + folderPath);

        for (HudsonNode node : folder.getJobs()) {
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, add it to the list of folders
                folders.add(node.getName());
            } else {
                // get full data of the job
                HudsonJob job = cacheJobs.get(node.getUrl());

                if (job != null) {
                    // mapping additional attributes
                    job.setEnv(extractEnvFrom(node.getName()));
                    job.setFolderName(folder.getName());
                    job.setViewName(extractNameFrom(node.getName()));
                    // add to returned list
                    jobs.add(job);
                }
            }
        }

        folderForView.put("sub_folders", folders);
        folderForView.put("jobs", jobs);

        return folderForView;
    }

    public List<HudsonJob> getJobsFrom(String folderPath) throws ExecutionException {
        return getJobsFromUrl(configuration.getUrl() + folderPath);
    }

    private List<HudsonJob> getJobsFromUrl(String fullUrl) throws ExecutionException {

        ArrayList<HudsonJob> foundedJobs = Lists.newArrayList();

        HudsonFolder hudsonNode = cacheFolders.get(fullUrl);

        for (HudsonNode node : hudsonNode.getJobs()) {
            String nodeUrl = node.getUrl();
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, call the method again to scan it
                List<HudsonJob> jobsFromUrl = getJobsFromUrl(nodeUrl);
                // add to returned list
                foundedJobs.addAll(jobsFromUrl);
            } else {
                // get full data of the job
                HudsonJob job = cacheJobs.get(nodeUrl);
                if (job != null) {
                    // mapping additional attributes
                    job.setEnv(extractEnvFrom(node.getName()));
                    job.setFolderName(hudsonNode.getName());
                    job.setViewName(extractNameFrom(node.getName()));
                    // add to returned list
                    foundedJobs.add(job);
                }
            }
        }

        return foundedJobs;
    }

    protected String extractEnvFrom(String name) {

        if (name == null || name.equals("")) {
            return "NO_ENV";
        }

        String[] elements = name.split("-");
        if (elements.length == 1) {
            return "NO_ENV";
        }


        return elements[elements.length - 1];
    }

    protected String extractNameFrom(String name) {

        if (name == null || name.equals("")) {
            return "NO_NAME";
        }

        String[] elements = name.split("-");
        if (elements.length == 1) {
            return "NO_NAME";
        }


        return elements[elements.length - 2];
    }

}



