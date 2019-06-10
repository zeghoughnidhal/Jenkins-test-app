package com.cloudwatt.example.domain.jenkins;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HudsonCore {

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
    @JsonProperty("color")
    private String color;
    @JsonProperty("jobs")
    private List<HudsonNode> jobs;

    // Constructor

    public HudsonCore() {
    }

    public String get_class() {
        return _class;
    }


    public String getName() {
        return name;
    }


    public String getUrl() {
        return url;
    }


    public List<HudsonNode> getJobs() {
        return jobs;
    }

    public void setJobs(List<HudsonNode> jobs) {
        this.jobs = jobs;
    }

}
