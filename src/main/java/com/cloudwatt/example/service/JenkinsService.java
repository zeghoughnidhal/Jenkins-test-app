package com.cloudwatt.example.service;

import com.cloudwatt.example.ApplicationConfiguration;
import com.cloudwatt.example.client.JenkinsClient;
import com.cloudwatt.example.domain.jenkins.HudsonFolder;
import com.cloudwatt.example.domain.jenkins.HudsonNode;
import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import org.springframework.web.client.HttpClientErrorException;
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
public class JenkinsService {

    @Autowired
    private ApplicationConfiguration configuration;

    @Autowired
    private JenkinsClient jenkinsClient;

    private Logger logger = Logger.getLogger("");


    public JenkinsService() {

    }

    //-------------------------------------------
    // Folders
    //-------------------------------------------

    @Cacheable
    public HudsonFolder getFolder(String projectName, Integer depth) throws ExecutionException {

        String jenkinsBaseUrl = configuration.getJenkinsBaseUrl();

        Map<String, Integer> queryParams = Maps.newHashMap();
        queryParams.put("depth", depth);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(jenkinsBaseUrl + projectName + "/api/json")
                .queryParam("depth", depth);

        return jenkinsClient.cacheFolders.get(builder.build().toString());
    }

    //-------------------------------------------
    // Sub-folders
    //-------------------------------------------

    public List<String> getSubFolders(String folderPath) throws ExecutionException {

        List<String> subFolders = Lists.newArrayList();

        HudsonFolder folder = null;
        folder = jenkinsClient.cacheFolders.get(configuration.getJenkinsBaseUrl() + folderPath);

        for (HudsonNode node : folder.getJobs()) {
            String nodeName = node.getName();
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, add it to the list of folders
                subFolders.add(nodeName);
            }
        }

        return subFolders;
    }

    //-------------------------------------------
    // Jobs
    //-------------------------------------------

    public List<HudsonJob> getJobsFrom(String folderPath) throws ExecutionException {

        // jenkins url from folder path
        String fullUrl = configuration.getJenkinsBaseUrl() + folderPath;

        // get folder
        HudsonFolder folder = jenkinsClient.cacheFolders.get(fullUrl);

        // find jobs
        ArrayList<HudsonJob> jobs = Lists.newArrayList();
        for (HudsonNode node : folder.getJobs()) {
            String nodeUrl = node.getUrl();
            if (!node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // get detail of the job
                HudsonJob job = jenkinsClient.cacheJobs.get(nodeUrl);
                if (job != null) {
                    // mapping additional attributes
                    mappingJob(job, folder.getName(), node.getName());
                    // add to returned list
                    jobs.add(job);
                }
            }
        }

        return jobs;
    }

    public List<HudsonJob> getJobsRecursiveModeFrom(String folderPath) throws ExecutionException {
        return getJobsRecursiveModeFromUrl(configuration.getJenkinsBaseUrl() + folderPath);
    }

    private List<HudsonJob> getJobsRecursiveModeFromUrl(String fullUrl) throws ExecutionException {

        ArrayList<HudsonJob> foundedJobs = Lists.newArrayList();

        HudsonFolder folder = jenkinsClient.cacheFolders.get(fullUrl);

        for (HudsonNode node : folder.getJobs()) {
            String nodeUrl = node.getUrl();
            if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                // in case of Folder, call the method again to scan it
                List<HudsonJob> jobsFromUrl = getJobsRecursiveModeFromUrl(nodeUrl);
                // add to returned list
                foundedJobs.addAll(jobsFromUrl);
            } else {
                // get full data of the job
                HudsonJob job = jenkinsClient.cacheJobs.get(nodeUrl);
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
    // Metrics
    //-------------------------------------------

    public Map<String, Object> getJobsForMetrics(String folderPath) throws ExecutionException {

        String fullUrl = configuration.getJenkinsBaseUrl() + folderPath;

        SortedSet<String> envsForMatrix = Sets.newTreeSet();
        HashMap<String, Object> jobsForMatrix = Maps.newHashMap();

            HudsonFolder folder = jenkinsClient.cacheFolders.get(fullUrl);

            for (HudsonNode node : folder.getJobs()) {
                String nodeUrl = StringUtils.urlDecode(node.getUrl());
                if (!node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                    // get full data of the job
                    HudsonJob job = jenkinsClient.cacheJobs.get(nodeUrl);
                    if (job != null) {

                        // map specific for view
                        mappingJob(job, folder.getName(), node.getName());

                        HashMap<String, HudsonJob> j = Maps.newHashMap();
                        j.put(job.getEnv(), job);

                        String viewName = job.getViewName();
                        if (!jobsForMatrix.containsKey(viewName)) {
                            jobsForMatrix.put(viewName, j);
                        } else {
                            Map jobsForMatrixForViewName = (Map) jobsForMatrix.get(viewName);
                            jobsForMatrixForViewName.put(job.getEnv(), job);
                        }
                        envsForMatrix.add(job.getEnv());
                    }
                }
            }


        HashMap<String, Object> forView = Maps.newHashMap();
        forView.put("envs", envsForMatrix);
        forView.put("jobs", jobsForMatrix);

        return forView;
    }


    public Map<String, Object> getJobsForMetricsRecursiveMode(String folderPath) throws ExecutionException {
        return getJobsForMetricsRecursiveModeFromUrl(configuration.getJenkinsBaseUrl() + folderPath);
    }

    private Map<String, Object> getJobsForMetricsRecursiveModeFromUrl(String fullUrl) throws ExecutionException {

        boolean handle = true;

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

        if (handle) {
            HudsonFolder folder = jenkinsClient.cacheFolders.get(fullUrl);

            for (HudsonNode node : folder.getJobs()) {
                String nodeUrl = StringUtils.urlDecode(node.getUrl());
                if (node.get_class().equals("com.cloudbees.hudson.plugins.folder.Folder")) {
                    // in case of Folder, call the method again to scan it
                    Map<String, Object> subfolderMatrixView = getJobsForMetricsRecursiveModeFromUrl(nodeUrl);

                    // add envs to current response
                    envsForMatrix.addAll((Set) subfolderMatrixView.get("envs"));

                    // add jobs to current response
                    Map<String, Map> subfolderJobs = (HashMap<String, Map>) subfolderMatrixView.get("jobs");

                    for (String jobName : subfolderJobs.keySet()) {
                        if (!jobsForMatrix.containsKey(jobName)) {
                            // this job does not exist in response yet..
                            jobsForMatrix.put(jobName, subfolderJobs.get(jobName));
                        } else {
                            Map<String, Object> jobEnvs = subfolderJobs.get(jobName);
                            for (String envName : jobEnvs.keySet()) {

                                Map<String, Object> o = (Map<String, Object>) jobsForMatrix.get(jobName);

                                if (!((Map<String, Object>) jobsForMatrix.get(jobName)).containsKey(envName)) {
                                    ((Map<String, Object>) jobsForMatrix.get(jobName)).put(envName, subfolderJobs.get(jobName).get(envName));
                                }
                            }

                        }
                    }
                } else {
                    // get full data of the job
                    HudsonJob job = jenkinsClient.cacheJobs.get(nodeUrl);


                    if (job != null) {
                        // mapping additional attributes
                        mappingJob(job, folder.getName(), node.getName());
                        // env of the job to the envs returned list
                        envsForMatrix.add(job.getEnv());
                        // we need to add the job in the returned object
                        // but first, create an object the key "env" and the job as value
                        // example :
                        // {
                        //   "int" : {
                        //     ... the job here ...
                        //   }
                        // }
                        HashMap<String, HudsonJob> jobForEnv = Maps.newHashMap();
                        jobForEnv.put(job.getEnv(), job);
                        // then put it in the returned object at the "viewName" key
                        String viewName = job.getViewName();
                        if (!jobsForMatrix.containsKey(viewName)) {
                            jobsForMatrix.put(viewName, jobForEnv);
                        } else {
                            Map jobsForMatrixForViewName = (Map) jobsForMatrix.get(viewName);
                            jobsForMatrixForViewName.put(job.getEnv(), job);
                        }
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
    // Business Service (specific cloudawatt)
    //-------------------------------------------

    private void mappingJob(HudsonJob job, String folderName, String nodeName) {
        job.setFolderName(folderName);
        job.setViewName(folderName + "-" + extractNameFrom(nodeName));
        job.setEnv(extractEnvFrom(nodeName));
    }

    String extractEnvFrom(String name) {

        if (name == null || name.equals("")) {
            return "NO_ENV";
        }

        String[] elements = name.split("-");
        if (elements.length == 1) {
            return "NO_ENV";
        }


        return elements[elements.length - 1];
    }

    private String extractNameFrom(String name) {

        if (name == null || name.equals("")) {
            return "NO_NAME";
        }

        String[] elements = name.split("-");
        if (elements.length == 1) {
            return "NO_NAME";
        }


        return elements[elements.length - 2];
    }

    //-------------------------------------------
    // Tests Reports
    //-------------------------------------------

    String extractLastLine(String allLines) {
        String[] lines = allLines.split("\n");
        for (int i = lines.length - 1; i >= 0; i--) {
            if (!lines[i].trim().equals("") && !lines[i].trim().equals("null")) {
                return lines[i].trim();
            }
        }
        return "";
    }

    String generateReportTestDetailUri(String className, String name) {

        StringBuilder detailUrl = new StringBuilder();

        if (className.length() == 0) {
            detailUrl.append("(root)/(empty)");
        } else {
            detailUrl.append(className);
            detailUrl.setCharAt(className.lastIndexOf("."), '/');
        }

        String modifiedName = name
                .replaceAll("\\[", "_")
                .replaceAll("\\]", "_")
                .replaceAll(",", "_")
                .replaceAll("\\(", "_")
                .replaceAll("\\)", "_")
                .replaceAll("\\.", "_")
                .replaceAll(" ", "_")
                .replaceAll("-", "_");

        detailUrl.append("/").append(modifiedName);

        return detailUrl.toString();
    }

    public ObjectNode getBuildTestsReportFromUrl(String buildPath) {

        // try get test report from jenkins...
        String buildTestsReportUrl = configuration.getJenkinsBaseUrl() + "/" + buildPath + "/testReport/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(buildTestsReportUrl + "/api/json");

        JsonNode testResults = JsonNodeFactory.instance.objectNode();
        try {
            testResults = jenkinsClient.restTemplate.getForObject(builder.build().toString(), JsonNode.class);
        } catch (HttpClientErrorException e) {
            // ... there are no Test Results ...
            logger.info("HTTP Code : " + e.getStatusCode());
            logger.info("Could not found : " + builder.build().toString());

            // ... we can eventually capture the output of the console for
            // tests which are not using the "test" feature of jenkins
            String buildTestsConsoleUrl = configuration.getJenkinsBaseUrl() + "/" + buildPath + "/console";
            UriComponentsBuilder builderConsole = UriComponentsBuilder.fromHttpUrl(buildTestsConsoleUrl);
            String consoleOutput;
            try {
                consoleOutput = jenkinsClient.restTemplate.getForObject(builderConsole.build().toString(), String.class);
                // for now this console output is not used, but it could be returned has a report
                logger.info(consoleOutput);
            } catch (HttpClientErrorException e1) {
                logger.info("HTTP Code : " + e1.getStatusCode());
                logger.info("Could not found : " + builderConsole.build().toString());
            }
        }

        // test results has been found
        /// => test cases
        ArrayNode cases = JsonNodeFactory.instance.arrayNode();
        /// => statistics
        int nbPassed = 0;
        int nbFixed = 0;
        int nbRegression = 0;
        int nbFailed = 0;
        int nbSkipped = 0;
        int nbTotal = 0;
        boolean bypassDetail;

        if (testResults.has("suites")) {
            for (JsonNode suite : testResults.get("suites")) {
                for (JsonNode suiteCase : suite.get("cases")) {

                    ObjectNode subCaseAsObjectNode = (ObjectNode) suiteCase;
                    bypassDetail = false;

                    if (suiteCase.get("status").asText().equals("PASSED")) {
                        nbPassed += 1;
                        bypassDetail = true;
                    } else
                    if (suiteCase.get("status").asText().equals("FIXED")) {
                        nbFixed += 1;
                        bypassDetail = true;
                    } else
                    if (suiteCase.get("status").asText().equals("FAILED")) {
                        nbFailed += 1;
                    } else
                    if (suiteCase.get("status").asText().equals("REGRESSION")) {
                        nbRegression += 1;
                    } else
                    if (suiteCase.get("status").asText().equals("SKIPPED")) {
                        nbSkipped += 1;
                    }
                    nbTotal += 1;

                    if (!bypassDetail) {

                        // build test detail url (from the case ClassName/name attributes)
                        String detailOnErrorUrl = buildTestsReportUrl +
                                generateReportTestDetailUri(suiteCase.get("className").asText(), suiteCase.get("name").asText());

                        // get case detail from jenkins
                        ObjectNode caseDetail;
                        try {
                            caseDetail = jenkinsClient.restTemplate.getForObject(detailOnErrorUrl + "/api/json", ObjectNode.class);
                        } catch (HttpClientErrorException e) {
                            logger.info("HTTP Code : " + e.getStatusCode());
                            logger.info("Could not found : " + detailOnErrorUrl);
                            // go to next case if an error occurred
                            continue;
                        }

                        // extract last line from case full stack trace
                        String errorStackTrace = "";
                        if (caseDetail.has("errorStackTrace")) {
                            errorStackTrace = extractLastLine(caseDetail.get("errorStackTrace").asText());
                        }
                        subCaseAsObjectNode.put("errorStackTrace", errorStackTrace);
                        // add the case to the list
                        cases.add(subCaseAsObjectNode);
                    }
                }
            }
        }

        // return statistics & tests reports with details
        ObjectNode res = JsonNodeFactory.instance.objectNode();
        res.put("nbPassed", nbPassed);
        res.put("nbFixed", nbFixed);
        res.put("nbRegression", nbRegression);
        res.put("nbFailed", nbFailed);
        res.put("nbSkipped", nbSkipped);
        res.put("nbTotal", nbTotal);
        res.put("cases", cases);


        return res;
    }

    //-------------------------------------------
}

