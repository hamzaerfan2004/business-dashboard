package com.example.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.dashboard.dto.CategorySummaryResponse;
import com.example.dashboard.entity.DataRecord;

public interface DataRecordRepository extends JpaRepository<DataRecord, Long> {

    List<DataRecord> findByUploadId(Long uploadId);

    @Query("SELECT COALESCE(SUM(d.numericValue), 0) FROM DataRecord d")
    Double getTotalValue();

    @Query("""
        SELECT new com.example.dashboard.dto.CategorySummaryResponse(
            d.category,
            SUM(d.numericValue)
        )
        FROM DataRecord d
        GROUP BY d.category
        ORDER BY SUM(d.numericValue) DESC
    """)
    List<CategorySummaryResponse> getCategorySummary();

}