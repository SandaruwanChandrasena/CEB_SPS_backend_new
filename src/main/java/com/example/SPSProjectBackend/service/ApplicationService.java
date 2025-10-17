package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.ApplicationDto;
import java.util.List;

public interface ApplicationService {
    ApplicationDto create(ApplicationDto dto);
    ApplicationDto read(String applicationId, String deptId);
    ApplicationDto update(String applicationId, String deptId, ApplicationDto dto);

    ApplicationDto findLatest(String applicationId);
    List<ApplicationDto> listByApplicationId(String applicationId);
    List<ApplicationDto> listByApplicant(String idNo);
}
