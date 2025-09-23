package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.PivDetail;
import com.example.SPSProjectBackend.model.PivDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PivDetailRepository extends JpaRepository<PivDetail, PivDetailId> {
    // Add custom queries if needed
    @Query("SELECT p FROM PivDetail p WHERE p.estReferenceNo = :estimateNo")
    List<PivDetail> findByEstReferenceNo(@Param("estimateNo") String estimateNo);
}