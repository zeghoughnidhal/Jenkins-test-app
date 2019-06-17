package com.cloudwatt.example.respository;

import com.cloudwatt.example.entity.JobEntity;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobEntity, Long> {
}
