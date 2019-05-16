package com.cloudwatt.example.domain.jenkins;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class HudsonJob extends HudsonNode implements Serializable, Comparable {

    private static final long serialVersionUID = -1l;

    @JsonProperty("buildable")
    private Boolean buildable;
    @JsonProperty("builds")
    private List<Object> builds = null;
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


    public HudsonJob() {
        super();
    }

    public HudsonJob(HudsonNode fromNode) {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    @Override
    public int compareTo(Object o) {
        return this.getViewName().compareTo(((HudsonJob) o).getViewName());
    }
}
