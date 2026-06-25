package com.example.dashboard.service;

import org.springframework.stereotype.Service;

import com.example.dashboard.dto.DashboardSummaryResponse;
import com.example.dashboard.repository.DataRecordRepository;
import com.example.dashboard.repository.UploadRepository;

@Service
public class DashboardService {

    private final UploadRepository uploadRepository;
    private final DataRecordRepository dataRecordRepository;

    public DashboardService(UploadRepository uploadRepository,
                            DataRecordRepository dataRecordRepository) {

        this.uploadRepository = uploadRepository;
        this.dataRecordRepository = dataRecordRepository;
    }

    public DashboardSummaryResponse getSummary() {

        long totalUploads = uploadRepository.count();

        long processedUploads = uploadRepository.countByStatus("processed");

        long pendingUploads = uploadRepository.countByStatus("pending");

        long totalRecords = dataRecordRepository.count();

        Double totalValue = dataRecordRepository.getTotalValue();

        if (totalValue == null) {
            totalValue = 0.0;
        }

        return new DashboardSummaryResponse(
                totalUploads,
                processedUploads,
                pendingUploads,
                totalRecords,
                totalValue
        );
    }
}