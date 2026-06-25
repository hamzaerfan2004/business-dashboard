package com.example.dashboard.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.example.dashboard.dto.UploadResponse;
import com.example.dashboard.entity.Upload;
import com.example.dashboard.dto.DashboardSummaryResponse;
import com.example.dashboard.dto.CategorySummaryResponse;
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
    
    public List<UploadResponse> getRecentUploads() {

        List<Upload> uploads =
                uploadRepository.findAllByOrderByUploadDateDesc();

        return uploads.stream()

                .map(upload -> new UploadResponse(

                        upload.getId(),
                        upload.getFilename(),
                        upload.getStatus(),
                        upload.getUploadDate()

                ))

                .collect(Collectors.toList());

    }
    
    public java.util.List<CategorySummaryResponse> getCategorySummary() {

        return dataRecordRepository.getCategorySummary();

    }
}