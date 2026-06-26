package com.example.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.dashboard.dto.CategorySummaryResponse;
import com.example.dashboard.entity.DataRecord;
import com.example.dashboard.entity.User;

public interface DataRecordRepository extends JpaRepository<DataRecord, Long> {

    List<DataRecord> findByUploadId(Long uploadId);

    @Query("""
        SELECT COALESCE(SUM(d.numericValue), 0)
        FROM DataRecord d
        WHERE d.upload.user = :user
    """)
    Double getTotalValue(User user);

    @Query("""
        SELECT new com.example.dashboard.dto.CategorySummaryResponse(
            d.category,
            SUM(d.numericValue)
        )
        FROM DataRecord d
        WHERE d.upload.user = :user
        GROUP BY d.category
        ORDER BY SUM(d.numericValue) DESC
    """)
    List<CategorySummaryResponse> getCategorySummary(User user);
    
    @Query("""
    SELECT COUNT(d)
    FROM DataRecord d
    WHERE d.upload.user = :user
    """)
    long countByUser(User user);
}