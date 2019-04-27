package com.cloudwatt.example.domain.jenkins;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HudsonNode {


    //Common fields

    @JsonProperty("_class")
    private String _class;
    @JsonProperty("actions")
    private List<Object> actions;
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
    @JsonProperty("jobs")
    private List<HudsonNode> jobs;


    //Job fields

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


    // Constructor
    public HudsonNode() {
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public List<Object> getActions() {
        return actions;
    }

    public void setActions(List<Object> actions) {
        this.actions = actions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Object getDisplayNameOrNull() {
        return displayNameOrNull;
    }

    public void setDisplayNameOrNull(Object displayNameOrNull) {
        this.displayNameOrNull = displayNameOrNull;
    }

    public String getFullDisplayName() {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName) {
        this.fullDisplayName = fullDisplayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<HudsonNode> getJobs() {
        return jobs;
    }

    public void setJobs(List<HudsonNode> jobs) {
        this.jobs = jobs;
    }

    public Boolean getBuildable() {
        return buildable;
    }

    public void setBuildable(Boolean buildable) {
        this.buildable = buildable;
    }

    public List<Object> getBuilds() {
        return builds;
    }

    public void setBuilds(List<Object> builds) {
        this.builds = builds;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getFirstBuild() {
        return firstBuild;
    }

    public void setFirstBuild(Object firstBuild) {
        this.firstBuild = firstBuild;
    }

    public List<Object> getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(List<Object> healthReport) {
        this.healthReport = healthReport;
    }

    public Boolean getInQueue() {
        return inQueue;
    }

    public void setInQueue(Boolean inQueue) {
        this.inQueue = inQueue;
    }

    public Boolean getKeepDependencies() {
        return keepDependencies;
    }

    public void setKeepDependencies(Boolean keepDependencies) {
        this.keepDependencies = keepDependencies;
    }

    public Object getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(Object lastBuild) {
        this.lastBuild = lastBuild;
    }

    public Object getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    public void setLastCompletedBuild(Object lastCompletedBuild) {
        this.lastCompletedBuild = lastCompletedBuild;
    }

    public Object getLastFailedBuild() {
        return lastFailedBuild;
    }

    public void setLastFailedBuild(Object lastFailedBuild) {
        this.lastFailedBuild = lastFailedBuild;
    }

    public Object getLastStableBuild() {
        return lastStableBuild;
    }

    public void setLastStableBuild(Object lastStableBuild) {
        this.lastStableBuild = lastStableBuild;
    }

    public Object getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public void setLastSuccessfulBuild(Object lastSuccessfulBuild) {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    public Object getLastUnstableBuild() {
        return lastUnstableBuild;
    }

    public void setLastUnstableBuild(Object lastUnstableBuild) {
        this.lastUnstableBuild = lastUnstableBuild;
    }

    public Object getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

    public void setLastUnsuccessfulBuild(Object lastUnsuccessfulBuild) {
        this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
    }

    public Integer getNextBuildNumber() {
        return nextBuildNumber;
    }

    public void setNextBuildNumber(Integer nextBuildNumber) {
        this.nextBuildNumber = nextBuildNumber;
    }

    public List<Object> getProperty() {
        return property;
    }

    public void setProperty(List<Object> property) {
        this.property = property;
    }

    public Object getQueueItem() {
        return queueItem;
    }

    public void setQueueItem(Object queueItem) {
        this.queueItem = queueItem;
    }

    public Boolean getConcurrentBuild() {
        return concurrentBuild;
    }

    public void setConcurrentBuild(Boolean concurrentBuild) {
        this.concurrentBuild = concurrentBuild;
    }

    public List<Object> getDownstreamProjects() {
        return downstreamProjects;
    }

    public void setDownstreamProjects(List<Object> downstreamProjects) {
        this.downstreamProjects = downstreamProjects;
    }

    public String getLabelExpression() {
        return labelExpression;
    }

    public void setLabelExpression(String labelExpression) {
        this.labelExpression = labelExpression;
    }

    public Object getScm() {
        return scm;
    }

    public void setScm(Object scm) {
        this.scm = scm;
    }

    public List<Object> getUpstreamProjects() {
        return upstreamProjects;
    }

    public void setUpstreamProjects(List<Object> upstreamProjects) {
        this.upstreamProjects = upstreamProjects;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
