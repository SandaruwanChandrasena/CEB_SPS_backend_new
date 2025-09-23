package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, String> {
    @Query("SELECT a FROM Approval a WHERE a.referenceNo = :estimateNo") // Changed parameter name
    List<Approval> findByEstimateNo(@Param("estimateNo") String estimateNo);
}