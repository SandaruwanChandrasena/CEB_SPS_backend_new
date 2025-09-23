package com.example.SPSProjectBackend.repository;

import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.PcestdttId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcestdttRepository extends JpaRepository<Pcestdtt, PcestdttId> {
}
