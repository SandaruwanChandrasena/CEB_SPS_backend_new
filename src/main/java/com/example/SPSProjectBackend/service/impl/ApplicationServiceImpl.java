package com.example.SPSProjectBackend.service.impl;

import com.example.SPSProjectBackend.dto.ApplicationDto;
import com.example.SPSProjectBackend.exception.ResourceNotFoundException;
import com.example.SPSProjectBackend.model.Application;
import com.example.SPSProjectBackend.model.ApplicationId;
import com.example.SPSProjectBackend.repository.ApplicationRepository;
import com.example.SPSProjectBackend.service.ApplicationService;
import com.example.SPSProjectBackend.service.mapper.ApplicationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repo;

    public ApplicationServiceImpl(ApplicationRepository repo) {
        this.repo = repo;
    }

    @Override
    public ApplicationDto create(ApplicationDto dto) {
        Application entity = ApplicationMapper.toEntity(dto);
        Application saved = repo.save(entity);
        return ApplicationMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicationDto read(String applicationId, String deptId) {
        ApplicationId id = new ApplicationId(applicationId, deptId);
        Application e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found for ID %s and Dept %s".formatted(applicationId, deptId)));
        return ApplicationMapper.toDto(e);
    }

    @Override
    public ApplicationDto update(String applicationId, String deptId, ApplicationDto dto) {
        ApplicationId id = new ApplicationId(applicationId, deptId);
        Application e = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found for ID %s and Dept %s".formatted(applicationId, deptId)));
        ApplicationMapper.copyToEntity(dto, e);
        return ApplicationMapper.toDto(repo.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicationDto findLatest(String applicationId) {
        Application e = repo.findLatestByApplicationId(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No application found for applicationId: " + applicationId));
        return ApplicationMapper.toDto(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationDto> listByApplicationId(String applicationId) {
        return repo.findById_ApplicationIdOrderBySubmitDateDesc(applicationId)
                .stream().map(ApplicationMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationDto> listByApplicant(String idNo) {
        return repo.findByIdNoOrderBySubmitDateDesc(idNo)
                .stream().map(ApplicationMapper::toDto).toList();
    }
}
