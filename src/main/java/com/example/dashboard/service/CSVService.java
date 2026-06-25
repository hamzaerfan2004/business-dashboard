package com.example.dashboard.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dashboard.entity.DataRecord;
import com.example.dashboard.entity.Upload;
import com.example.dashboard.entity.User;
import com.example.dashboard.repository.DataRecordRepository;
import com.example.dashboard.repository.UploadRepository;
import com.example.dashboard.repository.UserRepository;

@Service
public class CSVService {
	
	private final UploadRepository uploadRepository;
    private final DataRecordRepository dataRecordRepository;
    private final UserRepository userRepository;

    public CSVService(UploadRepository uploadRepository, DataRecordRepository dataRecordRepository, UserRepository userRepository) {
        this.uploadRepository = uploadRepository;
        this.dataRecordRepository = dataRecordRepository;
        this.userRepository = userRepository;
    }

    public Upload uploadCsv(MultipartFile file, String email) throws Exception {
    	if (file.isEmpty()) {
    		throw new RuntimeException("Uploaded file is empty.");
    	}
    	String filename = file.getOriginalFilename();
    	if (filename == null || !filename.toLowerCase().endsWith(".csv")) {
    		throw new RuntimeException("Only CSV files are allowed.");
    	}
    	User user = userRepository.findByEmail(email)
    			.orElseThrow(() -> new RuntimeException("User not found"));
        Upload upload = new Upload();
        upload.setFilename(file.getOriginalFilename());
        upload.setStatus("pending");
        upload.setUser(user);
        // assign user via userRepository if needed
        uploadRepository.save(upload);

        List<DataRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] values = line.split(",");

                if (values.length != 3) {
                    throw new RuntimeException("Invalid CSV format.");
                }

                DataRecord record = new DataRecord();

                record.setUpload(upload);
                record.setCategory(values[0].trim());
                record.setDate(values[1].trim());

                try {

                    record.setNumericValue(
                            Double.parseDouble(values[2].trim()));

                } catch (NumberFormatException e) {

                    throw new RuntimeException(
                            "Invalid numeric value: " + values[2]);

                }

                records.add(record);
            }

        } catch (Exception e) {

            upload.setStatus("failed");
            uploadRepository.save(upload);

            throw e;
        }
        dataRecordRepository.saveAll(records);
        upload.setStatus("processed");
        return uploadRepository.save(upload);
    }
}
