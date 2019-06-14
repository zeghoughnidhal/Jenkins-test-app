package com.cloudwatt.example.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FOLDER")
public class FolderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    private String description;

    private String displayName;

    private String fullDisplayName;

    private String fullName;

    private String name;

    private String url;

    private String color;

    private String env;

    private String folderName;

    @OneToMany
    private Collection<FolderEntity> folders;

    @OneToMany
    private Collection<JobEntity> jobs;

    private FolderEntity subFolder;

    public FolderEntity getFolderEntity() {
        return subFolder;
    }

    public void setFolderEntity(FolderEntity folderEntity) {
        this.subFolder = folderEntity;
    }

    public Collection<FolderEntity> getFolders() {
        return folders;
    }

    public void setFolders(Collection<FolderEntity> folders) {
        this.folders = folders;
    }

    public Collection<JobEntity> getJobs() {
        return jobs;
    }

    public void setJobs(Collection<JobEntity> jobs) {
        this.jobs = jobs;
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

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
