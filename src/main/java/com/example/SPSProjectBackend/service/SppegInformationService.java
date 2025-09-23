package com.example.SPSProjectBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SPSProjectBackend.model.SppegInformation;
import com.example.SPSProjectBackend.model.SppegInformationId;
import com.example.SPSProjectBackend.repository.SppegInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class SppegInformationService {

    @Autowired
    private SppegInformationRepository repository;
    public List<SppegInformation> getAll() {
        return repository.findAll();
    }
    public Optional<SppegInformation> getById(SppegInformationId id) {
        return repository.findById(id);
    }
    public SppegInformation save(SppegInformation info) {
        return repository.save(info);
    }
    public void deleteById(SppegInformationId id) {
        repository.deleteById(id);
    }
    public List<SppegInformation> getByParentId(String deptId, String parentId) {
        return repository.findByParentId(deptId, parentId);
    }
}
