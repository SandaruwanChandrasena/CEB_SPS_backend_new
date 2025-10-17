package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Applicant;
import com.example.SPSProjectBackend.model.Spstdesthmt;
import com.example.SPSProjectBackend.model.SpstdesthmtId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpstdesthmtRepository extends JpaRepository<Spstdesthmt, SpstdesthmtId> {

    // ✅ FIXED: Native query version (does not depend on ApplicationModel)
    @Query(value = """
            SELECT a.*
            FROM APPLICATIONS ap
            JOIN APPLICANT a ON a.ID_NO = ap.ID_NO
            WHERE ap.APPLICATION_NO = :appNo
            """, nativeQuery = true)
    Optional<Applicant> findApplicantByAppNo(@Param("appNo") String appNo);

    // ✅ Safe JPQL query (works if SpstdesthmtId contains appNo)
    @Query("select s from Spstdesthmt s where s.id.appNo = :appNo")
    Optional<Spstdesthmt> findFirstByAppNo(@Param("appNo") String appNo);

    // ✅ Count status (native)
    @Query(value = "SELECT STATUS, COUNT(*) AS CNT FROM SPSTDESTHMT GROUP BY STATUS", nativeQuery = true)
    List<Object[]> findStatusCountsNative();

    // ✅ Estimate details query
    @Query(value = """
            SELECT s.SECDEPOSIT, s.CEBCOST, s.REBATE_COST, s.VATCOST,
                   s.NBTCOST, s.SCTCOST, s.TOCONPAY
            FROM SPSTDESTHMT s
            WHERE s.DEPT_ID = :deptId
            """, nativeQuery = true)
    List<Object[]> findEstimateDetailsByDeptId(@Param("deptId") String deptId);

    // ✅ Estimate items query (⚠️ check if your table name is SPSTDESTDMT or SPSTDESTHMT)
    @Query(value = """
            SELECT s.LINE_TYPE, s.UOM, s.LINEDES, s.LENGTH, s.EST_COST, s.LINE_COST
            FROM SPSTDESTDMT s
            WHERE s.DEPT_ID = :deptId
            """, nativeQuery = true)
    List<Object[]> findEstimateItemsByDeptId(@Param("deptId") String deptId);
}
