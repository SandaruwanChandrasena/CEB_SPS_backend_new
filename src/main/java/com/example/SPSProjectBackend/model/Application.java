package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "APPLICATIONS", schema = "DACONS16") // drop schema if default
public class Application {

    @EmbeddedId
    private ApplicationId id;

    @Column(name = "APPLICATION_NO", length = 21, unique = true)
    private String applicationNo;

    @Column(name = "APPLICATION_TYPE", length = 2, nullable = false)
    private String applicationType;

    @Column(name = "APPLICATION_SUB_TYPE", length = 2)
    private String applicationSubType;

    // Use java.util.Date for Oracle TIMESTAMP/DATETIME to avoid ORA-18716
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SUBMIT_DATE", nullable = false)
    private Date submitDate;

    @Column(name = "ID_NO", length = 12, nullable = false)
    private String idNo;

    @Column(name = "PREPARED_BY", length = 50, nullable = false)
    private String preparedBy;

    @Column(name = "STATUS", length = 2, nullable = false)
    private String status;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    // Optional fields you may populate later from UI
    @Column(name = "DURATION_TYPE")
    private String durationType;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "IS_LOAN_APP", length = 3)
    private String isLoanApp; // "Y"/"N"

    // --- audit ---
    @Column(name = "ADD_USER", length = 50)
    private String addUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ADD_DATE")
    private Date addDate;

    @Column(name = "ADD_TIME", length = 11)
    private String addTime;

    @Column(name = "UPD_USER", length = 50)
    private String updUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPD_DATE")
    private Date updDate;

    @Column(name = "UPD_TIME", length = 11)
    private String updTime;

    // --- getters/setters ---
    public ApplicationId getId() { return id; }
    public void setId(ApplicationId id) { this.id = id; }

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

    public String getAddUser() { return addUser; }
    public void setAddUser(String addUser) { this.addUser = addUser; }

    public Date getAddDate() { return addDate; }
    public void setAddDate(Date addDate) { this.addDate = addDate; }

    public String getAddTime() { return addTime; }
    public void setAddTime(String addTime) { this.addTime = addTime; }

    public String getUpdUser() { return updUser; }
    public void setUpdUser(String updUser) { this.updUser = updUser; }

    public Date getUpdDate() { return updDate; }
    public void setUpdDate(Date updDate) { this.updDate = updDate; }

    public String getUpdTime() { return updTime; }
    public void setUpdTime(String updTime) { this.updTime = updTime; }
}
