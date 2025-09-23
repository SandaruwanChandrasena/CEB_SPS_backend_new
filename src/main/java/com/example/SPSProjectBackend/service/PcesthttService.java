package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pcesthtt;
import com.example.SPSProjectBackend.model.PcesthttId;
import com.example.SPSProjectBackend.repository.PcesthttRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PcesthttService {

    @Autowired
    private PcesthttRepository repository;

    public List<Pcesthtt> findAll() {
        return repository.findAll();
    }

    public Optional<Pcesthtt> findById(PcesthttId id) {
        return repository.findById(id);
    }

    public Pcesthtt save(Pcesthtt entity) {
        return repository.save(entity);
    }

    public void deleteById(PcesthttId id) {
        repository.deleteById(id);
    }

    public Optional<Pcesthtt> findByEstimateNo(String estimateNo) {
        return repository.findByIdEstimateNo(estimateNo);
    }

    public List<String> findAllEstimateNos() {
        return repository.findAll().stream()
                .map(pcesthtt -> pcesthtt.getId().getEstimateNo())
                .distinct()
                .collect(Collectors.toList());
    }

    // --- Status counts ---
    public Map<Short, Long> getRowCountByStatus() {
        List<Object[]> results = repository.getRowCountByStatus();
        Map<Short, Long> rowCountByStatus = new HashMap<>();
        for (Object[] result : results) {
            Short status = (Short) result[0];
            Long totalRows = (Long) result[1];
            rowCountByStatus.put(status, totalRows);
        }
        return rowCountByStatus;
    }
}
