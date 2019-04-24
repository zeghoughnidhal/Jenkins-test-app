package com.cloudwatt.example.domain.jenkins;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Folder implements Serializable {

    private static final long serialVersionUID= -1l;

    @JsonProperty("_class")
    public String _class;
    @JsonProperty("actions")
    private List<Object> actions;
    @JsonProperty("description")
    public String description;
    @JsonProperty("displayName")
    public String displayName;
    @JsonProperty("displayNameOrNull")
    public Object displayNameOrNull;
    @JsonProperty("fullDisplayName")
    public String fullDisplayName;
    @JsonProperty("fullName")
    public String fullName;
    @JsonProperty("name")
    public String name;
    @JsonProperty("url")
    public String url;
    @JsonProperty("jobs")
    private List<Job> jobs;

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

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Folder() {
    }
}