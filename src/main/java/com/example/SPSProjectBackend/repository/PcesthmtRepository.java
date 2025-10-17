package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.dto.CommissionDTO;
import com.example.SPSProjectBackend.model.Pcesthmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcesthmtRepository extends JpaRepository<Pcesthmt, String> {
    // If Pcesthmt has an @EmbeddedId, change the 2nd generic type to that ID class, e.g. <Pcesthmt, PcesthmtId>

    // --- Existing: list all estimate numbers (via JPQL on your entity) ---
    @Query("SELECT p.id.estimateNo FROM Pcesthmt p")
    List<String> findAllEstimateNumbers();

    // --- Existing: estimate & project dates (native; uses SPSNEW schema in your code) ---
    @Query(value = """
            SELECT
              TO_CHAR(ETIMATE_DT, 'YYYY-MM-DD') AS ETIMATE_DT,
              TO_CHAR(PRJ_ASS_DT, 'YYYY-MM-DD') AS PRJ_ASS_DT
            FROM SPSNEW.PCESTHMT
            WHERE ESTIMATE_NO = :estimateNo
            """, nativeQuery = true)
    Object[] findEstimateAndProjectDates(@Param("estimateNo") String estimateNo);

    // --- Existing: commission details (native; your code points to dacons12 schema) ---
    // NOTE: Make sure the schema is correct in your DB (DACONS12 vs SPSNEW). If both schemas exist,
    // pick the right one and keep it consistent.
    @Query(value = """
            SELECT
              p.ESTIMATE_NO AS estimateNo,
              p.STD_COST    AS totalCost,
              p.DEPT_ID     AS deptId,
              p.DESCR       AS description,
              p.STATUS      AS status
            FROM DACONS12.PCESTHMT p
            WHERE p.STATUS = 4
            ORDER BY p.ESTIMATE_NO, p.DEPT_ID
            """, nativeQuery = true)
    List<CommissionDTO> findCommissionDetails();

    // --- FIXED: Dept IDs by estimate (NATIVE, TRIM to remove CHAR padding) ---
    @Query(value = """
            SELECT TRIM(DEPT_ID)
            FROM PCESTHMT
            WHERE ESTIMATE_NO = :estimateNo
            """, nativeQuery = true)
    List<String> findDeptIdsByEstimateNo(@Param("estimateNo") String estimateNo);

    /*
    // Optional JPQL version (works if your provider supports JPQL TRIM)
    // If your Pcesthmt entity has fields p.id.deptId and p.id.estimateNo:
    @Query("SELECT DISTINCT TRIM(p.id.deptId) FROM Pcesthmt p WHERE p.id.estimateNo = :estimateNo")
    List<String> findDeptIdsByEstimateNo(@Param("estimateNo") String estimateNo);
    */
}
