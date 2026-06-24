package com.example.dashboard.controller;

import com.example.dashboard.repository.DataRecordRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final DataRecordRepository repository;

    public AnalyticsController(DataRecordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/count")
    public long totalRecords() {
        return repository.count();
    }
}