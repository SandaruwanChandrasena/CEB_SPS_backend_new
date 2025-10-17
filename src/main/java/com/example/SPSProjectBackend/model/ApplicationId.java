package com.example.SPSProjectBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApplicationId implements Serializable {

    @Column(name = "APPLICATION_ID", length = 24, nullable = false)
    private String applicationId;

    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    public ApplicationId() { }

    public ApplicationId(String applicationId, String deptId) {
        this.applicationId = applicationId;
        this.deptId = deptId;
    }

    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getDeptId() { return deptId; }
    public void setDeptId(String deptId) { this.deptId = deptId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationId that)) return false;
        return Objects.equals(applicationId, that.applicationId) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, deptId);
    }
}
