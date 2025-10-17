package com.example.SPSProjectBackend.dto;

import java.util.Date;

public class ApplicationDto {
    private String applicationId;
    private String deptId;

    private String applicationNo;
    private String applicationType;
    private String applicationSubType;

    private Date submitDate;

    private String idNo;
    private String preparedBy;
    private String status;
    private String description;

    private String durationType; // UI: Nature of Supply – unit
    private Integer duration;    // UI: Nature of Supply – value
    private String isLoanApp;    // "Y"/"N"

    // getters/setters
    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getDeptId() { return deptId; }
    public void setDeptId(String deptId) { this.deptId = deptId; }

    public String getApplicationNo() { return applicationNo; }
    public void setApplicationNo(String applicationNo) { this.applicationNo = applicationNo; }

    public String getApplicationType() { return applicationType; }
    public void setApplicationType(String applicationType) { this.applicationType = applicationType; }

    public String getApplicationSubType() { return applicationSubType; }
    public void setApplicationSubType(String applicationSubType) { this.applicationSubType = applicationSubType; }

    public Date getSubmitDate() { return submitDate; }
    public void setSubmitDate(Date submitDate) { this.submitDate = submitDate; }

    public String getIdNo() { return idNo; }
    public void setIdNo(String idNo) { this.idNo = idNo; }

    public String getPreparedBy() { return preparedBy; }
    public void setPreparedBy(String preparedBy) { this.preparedBy = preparedBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDurationType() { return durationType; }
    public void setDurationType(String durationType) { this.durationType = durationType; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getIsLoanApp() { return isLoanApp; }
    public void setIsLoanApp(String isLoanApp) { this.isLoanApp = isLoanApp; }
}
