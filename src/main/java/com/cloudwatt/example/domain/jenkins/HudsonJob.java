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

    public List<Object> getBuilds() {
        return builds;
    }

    public Object getFirstBuild() {
        return firstBuild;
    }

    public List<Object> getHealthReport() {
        return healthReport;
    }

    public Boolean getInQueue() {
        return inQueue;
    }

    public Boolean getKeepDependencies() {
        return keepDependencies;
    }

    public Object getLastBuild() {
        return lastBuild;
    }

    public Object getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    public Object getLastFailedBuild() {
        return lastFailedBuild;
    }

    public Object getLastStableBuild() {
        return lastStableBuild;
    }

    public Object getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public Object getLastUnstableBuild() {
        return lastUnstableBuild;
    }

    public Object getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

    public Integer getNextBuildNumber() {
        return nextBuildNumber;
    }

    public List<Object> getProperty() {
        return property;
    }

    public Object getQueueItem() {
        return queueItem;
    }

    public Boolean getConcurrentBuild() {
        return concurrentBuild;
    }

    public List<Object> getDownstreamProjects() {
        return downstreamProjects;
    }

    public String getLabelExpression() {
        return labelExpression;
    }

    public Object getScm() {
        return scm;
    }

    public List<Object> getUpstreamProjects() {
        return upstreamProjects;
    }

    @Override
    public int compareTo(Object o) {
        return this.getViewName().compareTo(((HudsonJob) o).getViewName());
    }


}
