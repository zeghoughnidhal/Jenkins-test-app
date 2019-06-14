package com.cloudwatt.example.repository;

import com.cloudwatt.example.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

}
