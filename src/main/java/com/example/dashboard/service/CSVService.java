package com.example.dashboard.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
	
	public void processCSV(MultipartFile file) throws IOException {
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream()));
		
		String line;
		
		while ((line = reader.readLine()) != null) {
			String[] data = line.split(",");
			// parse and save to database
		}
	}
}
