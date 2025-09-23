package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.PcestdttId;
import com.example.SPSProjectBackend.repository.PcestdttRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PcestdttService {

    private final PcestdttRepository repository;

    public PcestdttService(PcestdttRepository repository) {
        this.repository = repository;
    }

    public List<Pcestdtt> getAll() {
        return repository.findAll();
    }

    public Page<Pcestdtt> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Pcestdtt> getById(PcestdttId id) {
        return repository.findById(id);
    }

    public Pcestdtt save(Pcestdtt pcestdtt) {
        return repository.save(pcestdtt);
    }

    public void deleteById(PcestdttId id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<Pcestdtt> saveBulk(List<Pcestdtt> details) {
        return repository.saveAll(details);
    }
}
