package com.cloudwatt.example.dao;

import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.cloudwatt.example.entity.JobEntity;
import com.cloudwatt.example.respository.JobRepository;
import java.util.List;
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
    jobRepository.saveAll(map(jobs));
  }

  private static List<JobEntity> map(List<HudsonJob> jobs) {
    return jobs.stream().map(JobEntity::build).collect(Collectors.toList());
  }
}
