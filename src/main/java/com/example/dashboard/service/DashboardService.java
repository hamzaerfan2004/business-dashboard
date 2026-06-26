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
import com.example.dashboard.entity.User;
import com.example.dashboard.repository.UserRepository;

@Service
public class DashboardService {

    private final UploadRepository uploadRepository;
    private final DataRecordRepository dataRecordRepository;
    private final UserRepository userRepository;

    public DashboardService(UploadRepository uploadRepository,
                            DataRecordRepository dataRecordRepository,
                            UserRepository userRepository) {

        this.uploadRepository = uploadRepository;
        this.dataRecordRepository = dataRecordRepository;
        this.userRepository = userRepository;
    }

    public DashboardSummaryResponse getSummary(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    	
    	long totalUploads = uploadRepository.countByUser(user);

        long processedUploads = uploadRepository.countByUserAndStatus(user, "processed");

        long pendingUploads = uploadRepository.countByUserAndStatus(user, "pending");

        long totalRecords = dataRecordRepository.countByUser(user);

        Double totalValue = dataRecordRepository.getTotalValue(user);

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
    
    public List<UploadResponse> getRecentUploads(String email) {
    	User user = userRepository.findByEmail(email)
    	        .orElseThrow(() -> new RuntimeException("User not found"));

    	List<Upload> uploads =
    	        uploadRepository.findAllByUserOrderByUploadDateDesc(user);

        return uploads.stream()

                .map(upload -> new UploadResponse(

                        upload.getId(),
                        upload.getFilename(),
                        upload.getStatus(),
                        upload.getUploadDate()

                ))

                .collect(Collectors.toList());

    }
    
    public List<UploadResponse> searchUploads(String filename, String email) {
    	
    	User user = userRepository.findByEmail(email)
    	        .orElseThrow(() -> new RuntimeException("User not found"));
        
    	return uploadRepository
    	        .findByUserAndFilenameContainingIgnoreCaseOrderByUploadDateDesc(
    	                user,
    	                filename
    	        )
                .stream()
                .map(upload -> new UploadResponse(
                        upload.getId(),
                        upload.getFilename(),
                        upload.getStatus(),
                        upload.getUploadDate()
                ))
                .collect(Collectors.toList());

    }
    
    public java.util.List<CategorySummaryResponse> getCategorySummary(String email) {
    	User user = userRepository.findByEmail(email)
    	        .orElseThrow(() -> new RuntimeException("User not found"));
        return dataRecordRepository.getCategorySummary(user);

    }
    
    public void deleteUpload(Long id, String email) {
    	User user = userRepository.findByEmail(email)
    	        .orElseThrow(() -> new RuntimeException("User not found"));
    	Upload upload = uploadRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Upload not found"));

    	if (!upload.getUser().getId().equals(user.getId())) {
    	    throw new RuntimeException("You cannot delete another user's upload.");
    	}

    	uploadRepository.delete(upload);
    }
}