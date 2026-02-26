package com.example.dashboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dashboard.service.CSVService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class UploadController {
	
	private final CSVService csvService;
	
	public UploadController(CSVService csvService) {
		this.csvService = csvService;
	}
	
	@PostMapping
	public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		csvService.processCSV(file);
		return "File processed successfully";
	}
}
