//package com.cloudwatt.example.entity;
//
//import com.cloudwatt.example.domain.jenkins.HudsonNode;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "FOLDER")
//public class FolderEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    protected Long id;
//
//    private String description;
//
//    private String displayName;
//
//    private String fullDisplayName;
//
//    private String fullName;
//
//    private String name;
//
//    private String url;
//
//    private String color;
//
//    private String env;
//
//    private String folderName;
//
//    @OneToMany
//    private List<JobEntity> jobs;
//
//    @OneToMany
//    private List<SubFolderEntity> folders;
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getDisplayName() {
//        return displayName;
//    }
//
//    public void setDisplayName(String displayName) {
//        this.displayName = displayName;
//    }
//
//    public String getFullDisplayName() {
//        return fullDisplayName;
//    }
//
//    public void setFullDisplayName(String fullDisplayName) {
//        this.fullDisplayName = fullDisplayName;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public List<JobEntity> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(List<JobEntity> jobs) {
//        this.jobs = jobs;
//    }
//
//    public String getEnv() {
//        return env;
//    }
//
//    public void setEnv(String env) {
//        this.env = env;
//    }
//
//    public String getFolderName() {
//        return folderName;
//    }
//
//    public void setFolderName(String folderName) {
//        this.folderName = folderName;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<SubFolderEntity> getFolders() {
//        return folders;
//    }
//
//    public void setFolders(List<SubFolderEntity> folders) {
//        this.folders = folders;
//    }
//}
