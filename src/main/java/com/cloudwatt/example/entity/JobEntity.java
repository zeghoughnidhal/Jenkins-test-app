//package com.cloudwatt.example.entity;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "JOB")
//public class JobEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    protected Long id;
//
//    @OneToMany
//    private List<BuildEntity> builds = null;
//
//    @OneToOne
//    private BuildEntity firstBuild;
//
//    @OneToOne
//    private BuildEntity lastBuild;
//
//    @OneToOne
//    private BuildEntity lastCompletedBuild;
//
//    @OneToOne
//    private BuildEntity lastFailedBuild;
//
//    @OneToOne
//    private BuildEntity lastStableBuild;
//
//    @OneToOne
//    private BuildEntity lastSuccessfulBuild;
//
//    @OneToOne
//    private BuildEntity lastUnstableBuild;
//
//    @OneToOne
//    private BuildEntity lastUnsuccessfulBuild;
//
//    private Integer nextBuildNumber;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<BuildEntity> getBuilds() {
//        return builds;
//    }
//
//    public void setBuilds(List<BuildEntity> builds) {
//        this.builds = builds;
//    }
//
//    public BuildEntity getFirstBuild() {
//        return firstBuild;
//    }
//
//    public void setFirstBuild(BuildEntity firstBuild) {
//        this.firstBuild = firstBuild;
//    }
//
//    public BuildEntity getLastBuild() {
//        return lastBuild;
//    }
//
//    public void setLastBuild(BuildEntity lastBuild) {
//        this.lastBuild = lastBuild;
//    }
//
//    public BuildEntity getLastCompletedBuild() {
//        return lastCompletedBuild;
//    }
//
//    public void setLastCompletedBuild(BuildEntity lastCompletedBuild) {
//        this.lastCompletedBuild = lastCompletedBuild;
//    }
//
//    public BuildEntity getLastFailedBuild() {
//        return lastFailedBuild;
//    }
//
//    public void setLastFailedBuild(BuildEntity lastFailedBuild) {
//        this.lastFailedBuild = lastFailedBuild;
//    }
//
//    public BuildEntity getLastStableBuild() {
//        return lastStableBuild;
//    }
//
//    public void setLastStableBuild(BuildEntity lastStableBuild) {
//        this.lastStableBuild = lastStableBuild;
//    }
//
//    public BuildEntity getLastSuccessfulBuild() {
//        return lastSuccessfulBuild;
//    }
//
//    public void setLastSuccessfulBuild(BuildEntity lastSuccessfulBuild) {
//        this.lastSuccessfulBuild = lastSuccessfulBuild;
//    }
//
//    public BuildEntity getLastUnstableBuild() {
//        return lastUnstableBuild;
//    }
//
//    public void setLastUnstableBuild(BuildEntity lastUnstableBuild) {
//        this.lastUnstableBuild = lastUnstableBuild;
//    }
//
//    public BuildEntity getLastUnsuccessfulBuild() {
//        return lastUnsuccessfulBuild;
//    }
//
//    public void setLastUnsuccessfulBuild(BuildEntity lastUnsuccessfulBuild) {
//        this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
//    }
//
//    public Integer getNextBuildNumber() {
//        return nextBuildNumber;
//    }
//
//    public void setNextBuildNumber(Integer nextBuildNumber) {
//        this.nextBuildNumber = nextBuildNumber;
//    }
//}
