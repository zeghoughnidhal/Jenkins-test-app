package com.cloudwatt.example.dao;

import com.cloudwatt.example.domain.jenkins.HudsonFolder;
import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.cloudwatt.example.entity.BuildEntity;
import com.cloudwatt.example.entity.JobEntity;
import com.cloudwatt.example.respository.JobRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JenkinsDao {

  @Autowired
  private JobRepository jobRepository;

  @Transactional
  public void saveJobs(List<HudsonJob> jobs) {
    jobRepository.saveAll(mapJobs(jobs));
  }

//  @Deprecated
//  private FolderEntity mapFolder(HudsonFolder result) {
//
//    FolderEntity folderEntity = new FolderEntity();
//    folderEntity.setColor(result.getColor());
//    folderEntity.setDescription(result.getDescription());
//    folderEntity.setDisplayName(result.getDisplayName());
//    folderEntity.setFullName(result.getFullName());
//    folderEntity.setFullDisplayName(result.getFullDisplayName());
//    folderEntity.setName(result.getName());
//    folderEntity.setUrl(result.getUrl());
//
////    folderEntity.setJobs(mapJobs(result.getJobs()));
//    return folderEntity;
//  }

  private List<JobEntity> mapJobs(List<HudsonJob> jobs) {

    return jobs.stream().map(e -> {
      JobEntity entity = new JobEntity();
      // TODO set values

      entity.setFirstBuild(mapBuild(e.getFirstBuild()));
      entity.setBuilds(mapBuilds(e.getBuilds()));
      entity.setLastBuild(mapBuild(e.getLastBuild()));
      entity.setLastCompletedBuild(mapBuild(e.getLastCompletedBuild()));
      entity.setLastFailedBuild(mapBuild(e.getLastFailedBuild()));
      entity.setLastStableBuild(mapBuild(e.getLastStableBuild()));
      entity.setLastSuccessfulBuild(mapBuild(e.getLastSuccessfulBuild()));
      entity.setLastUnstableBuild(mapBuild(e.getLastUnstableBuild()));
      entity.setLastUnsuccessfulBuild(mapBuild(e.getLastUnsuccessfulBuild()));
      entity.setNextBuildNumber(e.getNextBuildNumber());

      return entity;
    })
    .collect(Collectors.toList());
  }

  private List<BuildEntity> mapBuilds(List<Object> build) {
    return build.stream().map(this::mapBuild).collect(Collectors.toList());
  }

  private BuildEntity mapBuild(Object build) {

    BuildEntity buildEntity = new BuildEntity();

    if (build != null) {
      Map<String, Object> values = (Map<String, Object>) build;
      buildEntity.setBuildId((String) values.get(("id")));
      buildEntity.setFullDisplayName(values.get("fullDisplayName") != null ? (String) values.get(("fullDisplayName")) : null);
    }
    // TODO set values
    return buildEntity;
  }
}
