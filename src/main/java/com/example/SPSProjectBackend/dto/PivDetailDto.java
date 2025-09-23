// PivDetailDto.java
package com.example.SPSProjectBackend.dto;

import java.math.BigDecimal;

public class PivDetailDto {
    private String pivNo;
    private String deptId;
    private BigDecimal pivAmount;
    private String paymentMode;
    private String status;

    // Constructor
    public PivDetailDto(String pivNo, String deptId, BigDecimal pivAmount, String paymentMode, String status) {
        this.pivNo = pivNo;
        this.deptId = deptId;
        this.pivAmount = pivAmount;
        this.paymentMode = paymentMode;
        this.status = status;
    }

    // Getters and Setters
    public String getPivNo() { return pivNo; }
    public void setPivNo(String pivNo) { this.pivNo = pivNo; }

    public String getDeptId() { return deptId; }
    public void setDeptId(String deptId) { this.deptId = deptId; }

    public BigDecimal getPivAmount() { return pivAmount; }
    public void setPivAmount(BigDecimal pivAmount) { this.pivAmount = pivAmount; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}