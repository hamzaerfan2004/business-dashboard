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
import com.example.dashboard.repository.DataRecordRepository;
import com.example.dashboard.repository.UploadRepository;

@Service
public class CSVService {
	
	private final UploadRepository uploadRepository;
    private final DataRecordRepository dataRecordRepository;

    public CSVService(UploadRepository uploadRepository, DataRecordRepository dataRecordRepository) {
        this.uploadRepository = uploadRepository;
        this.dataRecordRepository = dataRecordRepository;
    }

    public Upload uploadCsv(MultipartFile file, Long userId) throws Exception {
        Upload upload = new Upload();
        upload.setFilename(file.getOriginalFilename());
        upload.setStatus("pending");
        // assign user via userRepository if needed
        uploadRepository.save(upload);

        List<DataRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                DataRecord record = new DataRecord();
                record.setUpload(upload);
                record.setCategory(values[0]);
                record.setDate(values[1]);
                record.setNumericValue(Double.parseDouble(values[2]));
                records.add(record);
            }
        }
        dataRecordRepository.saveAll(records);
        upload.setStatus("processed");
        return uploadRepository.save(upload);
    }
}
