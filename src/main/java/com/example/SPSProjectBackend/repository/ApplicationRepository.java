package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Application;
import com.example.SPSProjectBackend.model.ApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {

    // All records for an applicationId (ordered)
    List<Application> findById_ApplicationIdOrderBySubmitDateDesc(String applicationId);

    // All records for an applicant (ordered)
    List<Application> findByIdNoOrderBySubmitDateDesc(String idNo);

    // Oracle-safe "latest by applicationId" (works with slashes/dots)
    @Query(value = """
        SELECT *
        FROM (
          SELECT a.*
          FROM DACONS16.APPLICATIONS a
          WHERE a.APPLICATION_ID = :applicationId
          ORDER BY a.SUBMIT_DATE DESC
        )
        WHERE ROWNUM = 1
        """, nativeQuery = true)
    Optional<Application> findLatestByApplicationId(@Param("applicationId") String applicationId);
}
