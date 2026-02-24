package com.example.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dashboard.entity.Upload;

public interface UploadRepository extends JpaRepository<Upload, Long> {
}
