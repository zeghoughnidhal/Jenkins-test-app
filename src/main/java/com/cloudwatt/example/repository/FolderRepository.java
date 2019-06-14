package com.cloudwatt.example.repository;

import com.cloudwatt.example.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<FolderEntity, Long> {

}
