package com.example.SPSProjectBackend.dto;

public class CommissionDTO {
    private String estimateNo;
    private Long totalCost;
    private String deptId;
    private String description;
    private Short status;

    public CommissionDTO() {}

    public CommissionDTO(
            String estimateNo,
            Number totalCost,
            String deptId,
            String description,
            Number status) {
        this.estimateNo = estimateNo;
        this.totalCost = (totalCost == null) ? null : totalCost.longValue();
        this.deptId = deptId;
        this.description = description;
        this.status = (status == null) ? null : status.shortValue();
    }

    // Getters and Setters
    public String getEstimateNo() { return estimateNo; }
    public void setEstimateNo(String estimateNo) { this.estimateNo = estimateNo; }

    public Long getTotalCost() { return totalCost; }
    public void setTotalCost(Long totalCost) { this.totalCost = totalCost; }

    public String getDeptId() { return deptId; }
    public void setDeptId(String deptId) { this.deptId = deptId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Short getStatus() { return status; }
    public void setStatus(Short status) { this.status = status; }
}