package com.cloudwatt.example.service;

import com.cloudwatt.example.ApplicationConfiguration;
import com.cloudwatt.example.domain.jenkins.Folder;
import com.cloudwatt.example.domain.jenkins.Job;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.Map;

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

    @Cacheable
    public Folder getFolder(String projectName, Integer depth) {

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });


        String fooResourceUrl = configuration.getUrl();

        Map<String, Integer> queryParams = Maps.newHashMap();
        queryParams.put("depth", depth);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(fooResourceUrl + projectName + "/api/json")
                .queryParam("depth", depth);

        Folder response = restTemplate.getForObject(builder.build().toString(), Folder.class);

        for (Job j : response.getJobs()) {
            j.setEnv(extractEnvFrom(j.getName()));
        }

        return response;
    }

    public String extractEnvFrom(String name) {

        if (name == null || name.equals("")) {
            return "NO_ENV";
        }

        String[] elements = name.split("-");
        if (elements.length == 1) {
            return "NO_ENV";
        }


        return elements[elements.length - 1];
    }
}



