package com.cloudwatt.example.domain.jenkins;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {

    private static final long serialVersionUID = -1l;

    @JsonProperty("_class")
    private String _class;
    @JsonProperty("actions")
    private List<Object> actions = null;
    @JsonProperty("description")
    private String description;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("displayNameOrNull")
    private Object displayNameOrNull;
    @JsonProperty("fullDisplayName")
    private String fullDisplayName;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("buildable")
    private Boolean buildable;
    @JsonProperty("builds")
    private List<Object> builds = null;
    @JsonProperty("color")
    private String color;
    @JsonProperty("firstBuild")
    private Object firstBuild;
    @JsonProperty("healthReport")
    private List<Object> healthReport = null;
    @JsonProperty("inQueue")
    private Boolean inQueue;
    @JsonProperty("keepDependencies")
    private Boolean keepDependencies;
    @JsonProperty("lastBuild")
    private Object lastBuild;
    @JsonProperty("lastCompletedBuild")
    private Object lastCompletedBuild;
    @JsonProperty("lastFailedBuild")
    private Object lastFailedBuild;
    @JsonProperty("lastStableBuild")
    private Object lastStableBuild;
    @JsonProperty("lastSuccessfulBuild")
    private Object lastSuccessfulBuild;
    @JsonProperty("lastUnstableBuild")
    private Object lastUnstableBuild;
    @JsonProperty("lastUnsuccessfulBuild")
    private Object lastUnsuccessfulBuild;
    @JsonProperty("nextBuildNumber")
    private Integer nextBuildNumber;
    @JsonProperty("property")
    private List<Object> property = null;
    @JsonProperty("queueItem")
    private Object queueItem;
    @JsonProperty("concurrentBuild")
    private Boolean concurrentBuild;
    @JsonProperty("downstreamProjects")
    private List<Object> downstreamProjects = null;
    @JsonProperty("labelExpression")
    private String labelExpression;
    @JsonProperty("scm")
    private Object scm;
    @JsonProperty("upstreamProjects")
    private List<Object> upstreamProjects = null;
    @JsonProperty("env")
    private String env;


    public Job() {
    }

    public String getName() {
        return name;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
