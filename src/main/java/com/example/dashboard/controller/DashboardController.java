package com.example.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.dto.DashboardSummaryResponse;
import java.util.List;
import com.example.dashboard.dto.CategorySummaryResponse;
import com.example.dashboard.service.DashboardService;

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
}