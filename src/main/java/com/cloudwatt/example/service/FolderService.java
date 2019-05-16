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
import com.google.common.collect.Sets;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.*;
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
                                        .fromHttpUrl(key)
                                        .path("/api/json")
                                        .queryParam("depth", 2);
                                return restTemplate.getForObject(builder.build().toString(), HudsonJob.class);
                            }
                        });
    }

    //-------------------------------------------
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
            String nodeName = node.getName();
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, add it to the list of folders
                folders.add(nodeName);
            } else {
                // get full data of the job
                HudsonJob job = cacheJobs.get(node.getUrl());

                if (job != null) {
                    // mapping additional attributes
                    mappingJob(job, folder.getName(), nodeName);
                    // add to returned list
                    jobs.add(job);
                }
            }
        }

        folderForView.put("sub_folders", folders);
        folderForView.put("jobs", jobs);

        return folderForView;
    }

    //-------------------------------------------

    public List<HudsonJob> getJobsRecursiveModeFrom(String folderPath) throws ExecutionException {
        return getJobsRecursiveModeFromUrl(configuration.getUrl() + folderPath);
    }

    private List<HudsonJob> getJobsRecursiveModeFromUrl(String fullUrl) throws ExecutionException {

        ArrayList<HudsonJob> foundedJobs = Lists.newArrayList();

        HudsonFolder folder = cacheFolders.get(fullUrl);

        for (HudsonNode node : folder.getJobs()) {
            String nodeUrl = node.getUrl();
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, call the method again to scan it
                List<HudsonJob> jobsFromUrl = getJobsRecursiveModeFromUrl(nodeUrl);
                // add to returned list
                foundedJobs.addAll(jobsFromUrl);
            } else {
                // get full data of the job
                HudsonJob job = cacheJobs.get(nodeUrl);
                if (job != null) {
                    // mapping additional attributes
                    mappingJob(job, folder.getName(), node.getName());
                    // add to returned list
                    foundedJobs.add(job);
                }
            }
        }

        return foundedJobs;
    }

    //-------------------------------------------

    public Map<String, List<HudsonJob>> getJobsByEnvRecursiveModeForMatrixViewFrom(String folderPath) throws ExecutionException {
        return getJobsByEnvRecursiveModeForMatrixViewFromUrl(configuration.getUrl() + folderPath);
    }

    private Map<String, List<HudsonJob>> getJobsByEnvRecursiveModeForMatrixViewFromUrl(String fullUrl) throws ExecutionException {

        HashMap<String, List<HudsonJob>> jobsByEnvironment = Maps.newHashMap();


        HudsonFolder folder = cacheFolders.get(fullUrl);

        for (HudsonNode node : folder.getJobs()) {
            String nodeUrl = StringUtils.urlDecode(node.getUrl());
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, call the method again to scan it
                Map<String, List<HudsonJob>> subfolderJobs = getJobsByEnvRecursiveModeForMatrixViewFromUrl(nodeUrl);
                // add founded jobs in the subfolder to the returned HashMap
                for (String key : subfolderJobs.keySet()) {
                    if (!jobsByEnvironment.containsKey(key)) {
                        jobsByEnvironment.put(key, Lists.newArrayList());
                    }
                    jobsByEnvironment.get(key).addAll(subfolderJobs.get(key));
                }
            } else {
                // get full data of the job
                HudsonJob job = cacheJobs.get(nodeUrl);
                if (job != null) {
                    // mapping additional attributes
                    String env = extractEnvFrom(node.getName());
                    mappingJob(job, folder.getName(), node.getName());
                    // add to returned list
                    if (!jobsByEnvironment.containsKey(env)) {
                        jobsByEnvironment.put(env, Lists.newArrayList());
                    }
                    jobsByEnvironment.get(env).add(job);
                }
            }
        }

        return jobsByEnvironment;
    }

    //-------------------------------------------

    public Map<String, Object> getJobsRecursiveModeForMatrixViewFrom(String folderPath) throws ExecutionException {
        return getJobsRecursiveModeForMatrixViewFromUrl(configuration.getUrl() + folderPath);
    }

    private Map<String, Object> getJobsRecursiveModeForMatrixViewFromUrl(String fullUrl) throws ExecutionException {

        // mappingJob format de reponse avec une clé pour chaque environnement
        // chaque clé contient un tableau de jobs dont l'attribut "env" correspond à la clé
        // => structure de réponse => JSON ( == HashMap en Java)
        // exemple :
        // {
        //   "envs" : ["dev0", dev2", dev3"],
        //   "jobs" : {
        //      "tempest (name du Job)" : {
        //          "dev0" : {HudsonJob}
        //          "dev2" : {HudsonJob}
        //      },
        //      "tempest (name du Job)" : {
        //          "dev3" : {HudsonJob}
        //      },
        // }
        SortedSet<String> envsForMatrix = Sets.newTreeSet();
        HashMap<String, Object> jobsForMatrix = Maps.newHashMap();

        HudsonFolder folder = cacheFolders.get(fullUrl);

        for (HudsonNode node : folder.getJobs()) {
            String nodeUrl = StringUtils.urlDecode(node.getUrl());
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, call the method again to scan it
                Map<String, Object> subfolderMatrixView = getJobsRecursiveModeForMatrixViewFromUrl(nodeUrl);

                // add envs to current response
                envsForMatrix.addAll((Set) subfolderMatrixView.get("envs"));

                // add jobs to current response
                Map<String, Map> subfolderJobs = (HashMap<String, Map>) subfolderMatrixView.get("jobs");

                for (String jobName : subfolderJobs.keySet()) {
                    if (!jobsForMatrix.containsKey(jobName)) {
                        // this job does not exist in response yet..
                        jobsForMatrix.put(jobName, subfolderMatrixView.get(jobName));
                    } else {
                        Map<String, Object> jobEnvs = subfolderJobs.get(jobName);
                        for (String envName : jobEnvs.keySet()) {

                            Map<String, Object> o = (Map<String, Object>) jobsForMatrix.get(jobName);

                            if (!((Map<String, Object>) jobsForMatrix.get(jobName)).containsKey(envName)) {
                                ((Map<String, Object>) jobsForMatrix.get(jobName)).put(envName,subfolderJobs.get(jobName).get(envName));
                            }
                        }

                    }
                }
            } else {
                // get full data of the job
                HudsonJob job = cacheJobs.get(nodeUrl);
                if (job != null) {
                    // mapping additional attributes
                    mappingJob(job, folder.getName(), node.getName());
                    // env of the job to the envs returned list
                    envsForMatrix.add(job.getEnv());
                    // add job into job list (if not exists for this jobName/env)
                    if (!jobsForMatrix.containsKey(job.getViewName())) {
                        HashMap<String, HudsonJob> jobForEnv = Maps.newHashMap();
                        jobForEnv.put(job.getEnv(), job);
                        jobsForMatrix.put(job.getViewName(), jobForEnv);
                    }
                }
            }
        }

        HashMap<String, Object> forView = Maps.newHashMap();
        forView.put("envs", envsForMatrix);
        forView.put("jobs", jobsForMatrix);

        return forView;
    }

    //-------------------------------------------

    private void mappingJob(HudsonJob job, String folderName, String nodeName) {
        job.setFolderName(folderName);
        job.setViewName(extractNameFrom(nodeName));
        job.setEnv(extractEnvFrom(nodeName));
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



