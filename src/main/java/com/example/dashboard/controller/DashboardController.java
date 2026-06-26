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
import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary( Principal principal ) {
        return dashboardService.getSummary(principal.getName());
    }
    
    @GetMapping("/category-summary")
    public List<CategorySummaryResponse> getCategorySummary( Principal principal ) {
        return dashboardService.getCategorySummary(principal.getName());
    }
    
    @GetMapping("/uploads")
    public List<UploadResponse> getUploads( Principal principal ) {

        return dashboardService.getRecentUploads(principal.getName());

    }
    
    @GetMapping("/uploads/search")
    public List<UploadResponse> searchUploads(
            @RequestParam String filename, Principal principal) {

        return dashboardService.searchUploads(filename, principal.getName());

    }
    
    @DeleteMapping("/uploads/{id}")
    public void deleteUpload(@PathVariable Long id, Principal principal) {

        dashboardService.deleteUpload(id, principal.getName());

    }
}