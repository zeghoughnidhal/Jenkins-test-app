package com.cloudwatt.example.entity;

import static javax.persistence.CascadeType.ALL;

import com.cloudwatt.example.dao.JenkinsDao;
import com.cloudwatt.example.domain.jenkins.HudsonJob;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JOB")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = ALL, mappedBy = "job")
    private List<BuildEntity> builds;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity firstBuild;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity lastBuild;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity lastCompletedBuild;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity lastFailedBuild;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity lastStableBuild;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity lastSuccessfulBuild;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity lastUnstableBuild;

    @OneToOne(cascade = ALL, mappedBy = "job")
    private BuildEntity lastUnsuccessfulBuild;

    private Integer nextBuildNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BuildEntity> getBuilds() {
        return builds;
    }

    public void setBuilds(List<BuildEntity> builds) {
        this.builds = builds;
    }

    public BuildEntity getFirstBuild() {
        return firstBuild;
    }

    public void setFirstBuild(BuildEntity firstBuild) {
        this.firstBuild = firstBuild;
    }

    public BuildEntity getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(BuildEntity lastBuild) {
        this.lastBuild = lastBuild;
    }

    public BuildEntity getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    public void setLastCompletedBuild(BuildEntity lastCompletedBuild) {
        this.lastCompletedBuild = lastCompletedBuild;
    }

    public BuildEntity getLastFailedBuild() {
        return lastFailedBuild;
    }

    public void setLastFailedBuild(BuildEntity lastFailedBuild) {
        this.lastFailedBuild = lastFailedBuild;
    }

    public BuildEntity getLastStableBuild() {
        return lastStableBuild;
    }

    public void setLastStableBuild(BuildEntity lastStableBuild) {
        this.lastStableBuild = lastStableBuild;
    }

    public BuildEntity getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public void setLastSuccessfulBuild(BuildEntity lastSuccessfulBuild) {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    public BuildEntity getLastUnstableBuild() {
        return lastUnstableBuild;
    }

    public void setLastUnstableBuild(BuildEntity lastUnstableBuild) {
        this.lastUnstableBuild = lastUnstableBuild;
    }

    public BuildEntity getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

    public void setLastUnsuccessfulBuild(BuildEntity lastUnsuccessfulBuild) {
        this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
    }

    public Integer getNextBuildNumber() {
        return nextBuildNumber;
    }

    public void setNextBuildNumber(Integer nextBuildNumber) {
        this.nextBuildNumber = nextBuildNumber;
    }

    public static JobEntity build(HudsonJob e) {

        JobEntity entity = new JobEntity();

        entity.setBuilds(mapBuilds(entity, e.getBuilds()));

        entity.setFirstBuild(mapBuild(entity, e.getFirstBuild()));
        entity.setLastBuild(mapBuild(entity, e.getLastBuild()));
        entity.setLastCompletedBuild(mapBuild(entity, e.getLastCompletedBuild()));
        entity.setLastFailedBuild(mapBuild(entity, e.getLastFailedBuild()));
        entity.setLastStableBuild(mapBuild(entity, e.getLastStableBuild()));
        entity.setLastSuccessfulBuild(mapBuild(entity, e.getLastSuccessfulBuild()));
        entity.setLastUnstableBuild(mapBuild(entity, e.getLastUnstableBuild()));
        entity.setLastUnsuccessfulBuild(mapBuild(entity, e.getLastUnsuccessfulBuild()));

        entity.setNextBuildNumber(e.getNextBuildNumber());

        return entity;
    }

    private static List<BuildEntity> mapBuilds(JobEntity jobEntity, List<Object> build) {
        return build.stream().map(bl -> mapBuild(jobEntity, bl)).collect(Collectors.toList());
    }

    private static BuildEntity mapBuild(JobEntity entity, Object build) {

        if (build != null) {
            BuildEntity buildEntity = BuildEntity.build((Map<String, Object>) build);
            buildEntity.setJob(entity);
            return buildEntity;
        }

        return null;
    }
}
