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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<HudsonNode> getJobs() {
        return jobs;
    }

    public void setJobs(List<HudsonNode> jobs) {
        this.jobs = jobs;
    }

}
