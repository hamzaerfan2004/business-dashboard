package com.example.dashboard.dto;

public class DashboardSummaryResponse {
	
	private long totalUploads;
	private long processedUploads;
	private long pendingUploads;
	private long totalRecords;
	private double totalValue;
	
	public DashboardSummaryResponse() {	
	}
	
	public DashboardSummaryResponse(long totalUploads, long processedUploads, long pendingUploads, long totalRecords, double totalValue) {
		this.totalUploads = totalUploads;
		this.processedUploads = processedUploads;
		this.pendingUploads = pendingUploads;
		this.totalRecords = totalRecords;
		this.totalValue = totalValue;
	}
	
	public long getTotalUploads() {
		return totalUploads;
	}

	public long getProcessedUploads() {
		return processedUploads;
	}

	public void setProcessedUploads(long processedUploads) {
		this.processedUploads = processedUploads;
	}

	public long getPendingUploads() {
		return pendingUploads;
	}

	public void setPendingUploads(long pendingUploads) {
		this.pendingUploads = pendingUploads;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public void setTotalUploads(long totalUploads) {
		this.totalUploads = totalUploads;
	}
	
	
}
