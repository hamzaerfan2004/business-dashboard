package com.example.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dashboard.entity.DataRecord;

public interface DataRecordRepository extends JpaRepository<DataRecord, Long>{
	List<DataRecord> findByUploadId(Long uploadId);
}
