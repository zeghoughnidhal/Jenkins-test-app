package com.cloudwatt.example.respository;

import com.cloudwatt.example.entity.FolderEntity;
import com.cloudwatt.example.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
}
