package com.example.SPSProjectBackend.repository;


import com.example.SPSProjectBackend.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {
    Optional<Applicant> findByIdNo(String idNo);

    //List<Applicant> findByDeptId(String deptId);

    //this is for testing purpose of commission applicant

    @Query("SELECT a FROM Applicant a WHERE a.deptId = :deptId")
    List<Applicant> findByDeptId(@Param("deptId") String deptId);

    // ApplicantRepository.java
    @Query("SELECT a FROM Applicant a WHERE a.deptId IN :deptIds")
    List<Applicant> findByDeptIdIn(@Param("deptIds") List<String> deptIds);

}