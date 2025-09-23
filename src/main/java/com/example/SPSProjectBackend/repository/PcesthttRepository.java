package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pcesthtt;
import com.example.SPSProjectBackend.model.PcesthttId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PcesthttRepository extends JpaRepository<Pcesthtt, PcesthttId> {

    @Query("SELECT p.status AS status, COUNT(p) AS totalRows " +
            "FROM Pcesthtt p " +
            "GROUP BY p.status")
    List<Object[]> getRowCountByStatus();

    Optional<Pcesthtt> findByIdEstimateNo(String estimateNo);
}
