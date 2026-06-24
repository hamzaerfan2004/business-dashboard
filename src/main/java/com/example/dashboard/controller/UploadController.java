package com.example.dashboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dashboard.service.CSVService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/uploads")
public class UploadController {
	
	private final CSVService csvService;
	
	public UploadController(CSVService csvService) {
		this.csvService = csvService;
	}
	
	@PostMapping
	public String uploadFile(
	        @RequestParam("file") MultipartFile file,
	        Authentication authentication) throws Exception {

	    csvService.uploadCsv(file, authentication.getName());

	    return "File processed successfully";
	}
}
