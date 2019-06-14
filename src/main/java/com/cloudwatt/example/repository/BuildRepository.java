package com.cloudwatt.example.repository;

import com.cloudwatt.example.entity.BuildEntity;
import com.cloudwatt.example.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildRepository extends JpaRepository<BuildEntity, Long> {

}
