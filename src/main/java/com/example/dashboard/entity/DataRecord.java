package com.example.dashboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="data_records")
public class DataRecord {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Upload upload;
	
	private String category;
	private String date;
	private Double numericValue; // to be extended as needed
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Upload getUpload() {
		return upload;
	}
	public void setUpload(Upload upload) {
		this.upload = upload;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getNumericValue() {
		return numericValue;
	}
	public void setNumericValue(Double numericValue) {
		this.numericValue = numericValue;
	}
}
