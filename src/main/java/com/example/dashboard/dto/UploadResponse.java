package com.example.dashboard.dto;

import java.time.LocalDateTime;

public class UploadResponse {

    private Long id;
    private String filename;
    private String status;
    private LocalDateTime uploadDate;

    public UploadResponse(
            Long id,
            String filename,
            String status,
            LocalDateTime uploadDate) {

        this.id = id;
        this.filename = filename;
        this.status = status;
        this.uploadDate = uploadDate;
    }

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

}