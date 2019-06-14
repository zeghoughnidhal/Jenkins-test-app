package com.cloudwatt.example.respository;

import com.cloudwatt.example.entity.BuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BuildRepository extends JpaRepository<BuildEntity, Long> {
}
