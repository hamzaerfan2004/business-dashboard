package com.example.dashboard.repository;

import java.util.List;
import com.example.dashboard.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dashboard.entity.Upload;

public interface UploadRepository extends JpaRepository<Upload, Long> {

	long countByUser(User user);

	long countByUserAndStatus(User user, String status);

	List<Upload> findAllByUserOrderByUploadDateDesc(User user);

	List<Upload> findByUserAndFilenameContainingIgnoreCaseOrderByUploadDateDesc(
	        User user,
	        String filename
	);    
    void deleteById(Long id);
}