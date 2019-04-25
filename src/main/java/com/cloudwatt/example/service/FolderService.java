package com.cloudwatt.example.service;

import com.cloudwatt.example.domain.jenkins.Folder;
import com.cloudwatt.example.domain.jenkins.Job;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

@Service
@PropertySource("classpath:application.yml")
public class FolderService {

    @Value("${jenkins_base_url}")
    private String url;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceProperties serviceProperties;

    @Cacheable
    public Folder getFolder(String projectName) {
        //Depth 1

        // URL dataFolder = Resources.getResource(projectName);

        Folder folder = new Folder();


        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        // convert JSON string to Map

        // serviceProperties.getName();

        // TODO récupérer l'url de la conf
        String fooResourceUrl = "https://ci.int0.aub.cloudwatt.net";
//        projectName = "/job/Functional-tests/job/Tempest/job/ScheduleTempest";


        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("i...", "");


        Folder response = restTemplate.getForObject(fooResourceUrl + projectName + "/api/json", Folder.class);
        // assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

//         Job job = new Job();
        // response.getJobs().stream().forEach(j -> extractEnvFrom(j));
        for (Job j : response.getJobs()) {
            String env = extractEnvFrom(j.getName());

        }

        // extractEnvFrom(job, "test-int4");
        // System.out.println(job.envJob);

//        try {
//            folder = objectMapper.readValue(dataFolder, Folder.class);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return folder;
    }

    public String extractEnvFrom(String name) {


        if (name == null || name.equals("")) {
            return "NO_ENV";
        }

        String[] elements = name.split("-");
        if (elements.length==1) {
            return "NO_ENV";
        }



        return elements[elements.length-1];


    }
     }



