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
public class UploadController {
	
	private final CSVService csvService;
	
	public UploadController(CSVService csvService) {
		this.csvService = csvService;
	}
	
	@PostMapping
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws Exception {
		csvService.uploadCsv(file, userId);
		return "File processed successfully";
	}
}
