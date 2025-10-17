package com.example.SPSProjectBackend.service.mapper;

import com.example.SPSProjectBackend.dto.ApplicationDto;
import com.example.SPSProjectBackend.model.Application;
import com.example.SPSProjectBackend.model.ApplicationId;

import java.util.Date;

public class ApplicationMapper {

    public static Application toEntity(ApplicationDto dto) {
        Application e = new Application();
        e.setId(new ApplicationId(dto.getApplicationId(), dto.getDeptId()));
        e.setApplicationNo(dto.getApplicationNo());
        e.setApplicationType(dto.getApplicationType());
        e.setApplicationSubType(dto.getApplicationSubType());

        // Date -> Date (safe for Oracle)
        e.setSubmitDate(dto.getSubmitDate() != null ? dto.getSubmitDate() : new Date());

        e.setIdNo(dto.getIdNo());
        e.setPreparedBy(dto.getPreparedBy() != null ? dto.getPreparedBy() : "WEB");
        e.setStatus(dto.getStatus() != null ? dto.getStatus() : "N");
        e.setDescription(dto.getDescription());

        e.setDurationType(dto.getDurationType());
        e.setDuration(dto.getDuration());
        e.setIsLoanApp(normalizeYesNo(dto.getIsLoanApp()));

        // audit create
        e.setAddDate(new Date());
        return e;
    }

    public static void copyToEntity(ApplicationDto dto, Application e) {
        if (dto.getApplicationNo() != null)      e.setApplicationNo(dto.getApplicationNo());
        if (dto.getApplicationType() != null)    e.setApplicationType(dto.getApplicationType());
        if (dto.getApplicationSubType() != null) e.setApplicationSubType(dto.getApplicationSubType());
        if (dto.getSubmitDate() != null)         e.setSubmitDate(dto.getSubmitDate());
        if (dto.getIdNo() != null)               e.setIdNo(dto.getIdNo());
        if (dto.getPreparedBy() != null)         e.setPreparedBy(dto.getPreparedBy());
        if (dto.getStatus() != null)             e.setStatus(dto.getStatus());
        if (dto.getDescription() != null)        e.setDescription(dto.getDescription());
        if (dto.getDurationType() != null)       e.setDurationType(dto.getDurationType());
        if (dto.getDuration() != null)           e.setDuration(dto.getDuration());
        if (dto.getIsLoanApp() != null)          e.setIsLoanApp(normalizeYesNo(dto.getIsLoanApp()));

        // audit update
        e.setUpdDate(new Date());
    }

    public static ApplicationDto toDto(Application e) {
        ApplicationDto dto = new ApplicationDto();
        dto.setApplicationId(e.getId().getApplicationId());
        dto.setDeptId(e.getId().getDeptId());
        dto.setApplicationNo(e.getApplicationNo());
        dto.setApplicationType(e.getApplicationType());
        dto.setApplicationSubType(e.getApplicationSubType());
        dto.setSubmitDate(e.getSubmitDate());
        dto.setIdNo(e.getIdNo());
        dto.setPreparedBy(e.getPreparedBy());
        dto.setStatus(e.getStatus());
        dto.setDescription(e.getDescription());
        dto.setDurationType(e.getDurationType());
        dto.setDuration(e.getDuration());
        dto.setIsLoanApp(e.getIsLoanApp());
        return dto;
    }

    private static String normalizeYesNo(String v) {
        if (v == null) return null;
        String s = v.trim().toUpperCase();
        if (s.equals("Y") || s.equals("YES") || s.equals("1") || s.equals("TRUE")) return "Y";
        if (s.equals("N") || s.equals("NO")  || s.equals("0") || s.equals("FALSE")) return "N";
        return s;
    }
}
