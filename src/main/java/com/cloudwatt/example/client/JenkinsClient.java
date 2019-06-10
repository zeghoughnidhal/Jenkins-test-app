package com.cloudwatt.example.client;

import com.cloudwatt.example.domain.jenkins.HudsonFolder;
import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component("jenkinsClient")
public class JenkinsClient {

    @Autowired
    public RestTemplate restTemplate;

    private Logger logger = Logger.getLogger("");

    public LoadingCache<String, HudsonFolder> cacheFolders;
    public LoadingCache<String, HudsonJob> cacheJobs;

    public JenkinsClient() {

        // disable https check
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        // cache for folders
        cacheFolders = CacheBuilder.newBuilder()
                .expireAfterWrite(25, TimeUnit.SECONDS)
                .build(
                        new CacheLoader<String, HudsonFolder>() {
                            public HudsonFolder load(String key) {
                                logger.info("Call Jenkins on : " + key);
                                UriComponentsBuilder builder = UriComponentsBuilder
                                        .fromHttpUrl(key + "/api/json");
                                return restTemplate.getForObject(builder.build().toString(), HudsonFolder.class);
                            }
                        });

        // cache for jobs
        cacheJobs = CacheBuilder.newBuilder()
                // .maximumSize(1000)
                .expireAfterWrite(120, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, HudsonJob>() {
                            public HudsonJob load(String key) {
                                logger.info("Call Jenkins on : " + key);
                                UriComponentsBuilder builder = UriComponentsBuilder
                                        .fromHttpUrl(key)
                                        .path("/api/json")
                                        .queryParam("depth", 1);
                                return restTemplate.getForObject(builder.build().toString(), HudsonJob.class);
                            }
                        });

    }
}
