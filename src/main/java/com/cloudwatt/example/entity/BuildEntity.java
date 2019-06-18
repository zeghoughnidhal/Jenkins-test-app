package com.cloudwatt.example.entity;

import java.util.Map;
import javax.persistence.*;

@Entity
@Table(name = "BUILD")
public class BuildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String buildId;

    private String fullDisplayName;

    // A voir le format du message de build
    private String detail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private JobEntity job;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "firstBuild")
    private JobEntity buildJob;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lastBuild")
    private JobEntity lastBuildJob;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lastCompletedBuild")
    private JobEntity lastCompletedBuildJob;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lastFailedBuild")
    private JobEntity lastFailedBuildJob;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lastStableBuild")
    private JobEntity lastStableBuildJob;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lastSuccessfulBuild")
    private JobEntity lastSuccessfulBuildJob;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lastUnstableBuild")
    private JobEntity lastUnstableBuildJob;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lastUnsuccessfulBuild")
    private JobEntity lastUnsuccessfulBuildJob;


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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFullDisplayName() {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName) {
        this.fullDisplayName = fullDisplayName;
    }

    public JobEntity getJob() {
        return job;
    }

    public BuildEntity setJob(JobEntity job) {
        this.job = job;
        return this;
    }

    public static BuildEntity build(Map<String, Object> values) {
        BuildEntity buildEntity = new BuildEntity();
        buildEntity.setBuildId((String) values.get(("id")));
        buildEntity.setFullDisplayName(values.get("fullDisplayName") != null ? (String) values.get(("fullDisplayName")) : null);
        return buildEntity;
    }
}
