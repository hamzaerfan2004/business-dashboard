package com.example.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.dashboard.dto.DashboardSummaryResponse;
import java.util.List;
import com.example.dashboard.dto.CategorySummaryResponse;
import com.example.dashboard.service.DashboardService;
import java.util.List;
import com.example.dashboard.dto.UploadResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary() {
        return dashboardService.getSummary();
    }
    
    @GetMapping("/category-summary")
    public List<CategorySummaryResponse> getCategorySummary() {
        return dashboardService.getCategorySummary();
    }
    
    @GetMapping("/uploads")
    public List<UploadResponse> getUploads() {

        return dashboardService.getRecentUploads();

    }
    
    @GetMapping("/uploads/search")
    public List<UploadResponse> searchUploads(
            @RequestParam String filename) {

        return dashboardService.searchUploads(filename);

    }
    
    @DeleteMapping("/uploads/{id}")
    public void deleteUpload(@PathVariable Long id) {

        dashboardService.deleteUpload(id);

    }
}