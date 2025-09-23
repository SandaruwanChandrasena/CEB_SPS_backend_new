package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.dto.CommissionDTO;
import com.example.SPSProjectBackend.model.Pcesthmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PcesthmtRepository extends JpaRepository<Pcesthmt, String> {

    @Query("SELECT p.id.estimateNo FROM Pcesthmt p")
    List<String> findAllEstimateNumbers();

    @Query(value = "SELECT TO_CHAR(ETIMATE_DT, 'YYYY-MM-DD') AS ETIMATE_DT, " +
            "TO_CHAR(PRJ_ASS_DT, 'YYYY-MM-DD') AS PRJ_ASS_DT " +
            "FROM SPSNEW.PCESTHMT " +
            "WHERE ESTIMATE_NO = :estimateNo",
            nativeQuery = true)
    Object[] findEstimateAndProjectDates(@Param("estimateNo") String estimateNo);

    // Add this method to your existing repository
    @Query(value =
            "SELECT " +
                    "p.ESTIMATE_NO AS estimateNo, " +
                    "p.STD_COST AS totalCost, " +
                    "p.DEPT_ID AS deptId, " +
                    "p.DESCR AS description, " +
                    "p.STATUS AS status " +
                    "FROM dacons12.PCESTHMT p " +
                    "WHERE p.STATUS = 4 " +
                    "ORDER BY p.ESTIMATE_NO, p.DEPT_ID",
            nativeQuery = true)
    List<CommissionDTO> findCommissionDetails();
    //this is for testing purpose of commission applicant
    // PcesthmtRepository.java

    @Query("SELECT DISTINCT CAST(p.id.deptId AS string) FROM Pcesthmt p WHERE p.id.estimateNo = :estimateNo")
    List<String> findDeptIdsByEstimateNo(@Param("estimateNo") String estimateNo);
}