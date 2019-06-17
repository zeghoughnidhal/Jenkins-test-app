package com.cloudwatt.example.entity;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @ManyToOne
    @JoinColumn(name = "job", referencedColumnName = "id")
    private JobEntity job;

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
