package com.cloudwatt.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Clob;

@Entity
@Table(name = "BUILD")
public class BuildEntity implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String buildId;

    private  JobEntity buildJob;

    public JobEntity getBuildJob() {
        return buildJob;
    }

    public void setBuildJob(JobEntity buildJob) {
        this.buildJob = buildJob;
    }

    public BuildEntity() {
    }

    public BuildEntity(String buildId, Clob detail) {
        this.buildId = buildId;
        this.detail = detail;
    }


    // A voir le format du message de build ? Blob ?
    private Clob detail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public Clob getDetail() {
        return detail;
    }

    public void setDetail(Clob detail) {
        this.detail = detail;
    }
}
