package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.ApplicantDTO;
import com.example.SPSProjectBackend.model.Applicant;
import com.example.SPSProjectBackend.model.Pcesthmt;
import com.example.SPSProjectBackend.repository.ApplicantRepository;
import com.example.SPSProjectBackend.repository.PcesthmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.util.Date;



// ApplicantService.java
import java.math.RoundingMode;
import java.util.Date;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private PcesthmtRepository pcesthmtRepository;

    // Convert Entity to DTO
    private ApplicantDTO convertToDTO(Applicant applicant) {
        ApplicantDTO dto = new ApplicantDTO();
        dto.setIdNo(applicant.getIdNo());
        dto.setIdType(applicant.getIdType());
        dto.setFirstName(applicant.getFirstName());
        dto.setLastName(applicant.getLastName());
        dto.setStreetAddress(applicant.getStreetAddress());
        dto.setSuburb(applicant.getSuburb());
        dto.setCity(applicant.getCity());
        dto.setPostalCode(applicant.getPostalCode());
        dto.setEmail(applicant.getEmail());
        dto.setTelephoneNo(applicant.getTelephoneNo());
        dto.setMobileNo(applicant.getMobileNo());
        dto.setCebEmployee(applicant.getCebEmployee());
        dto.setPreferredLanguage(applicant.getPreferredLanguage());
        dto.setStatus(applicant.getStatus());
        dto.setAddUser(applicant.getAddUser());
        dto.setAddDate(applicant.getAddDate());
        dto.setAddTime(applicant.getAddTime());
        dto.setUpdUser(applicant.getUpdUser());
        dto.setUpdDate(applicant.getUpdDate());
        dto.setUpdTime(applicant.getUpdTime());
        dto.setEntitledForLoan(applicant.getEntitledForLoan());
        dto.setMemberOfSamurdhi(applicant.getMemberOfSamurdhi());
        dto.setSamurdhiId(applicant.getSamurdhiId());
        dto.setSharePrice(applicant.getSharePrice());
        dto.setNoOfShares(applicant.getNoOfShares());
        dto.setLoanReference(applicant.getLoanReference());
        dto.setLoanAmount(applicant.getLoanAmount());
        dto.setCompanyName(applicant.getCompanyName());
        dto.setDeptId(applicant.getDeptId());
        dto.setFullName(applicant.getFullName());
        dto.setPersonalCorporate(applicant.getPersonalCorporate());
        return dto;
    }

    // Convert DTO to Entity
    private Applicant convertToEntity(ApplicantDTO dto) {
        Applicant applicant = new Applicant();
        applicant.setIdNo(dto.getIdNo());
        applicant.setIdType(dto.getIdType());
        applicant.setFirstName(dto.getFirstName());
        applicant.setLastName(dto.getLastName());
        applicant.setStreetAddress(dto.getStreetAddress());
        applicant.setSuburb(dto.getSuburb());
        applicant.setCity(dto.getCity());
        applicant.setPostalCode(dto.getPostalCode());
        applicant.setEmail(dto.getEmail());
        applicant.setTelephoneNo(dto.getTelephoneNo());
        applicant.setMobileNo(dto.getMobileNo());
        applicant.setCebEmployee(dto.getCebEmployee());
        applicant.setPreferredLanguage(dto.getPreferredLanguage());
        applicant.setStatus(dto.getStatus());
        applicant.setAddUser(dto.getAddUser());
        applicant.setAddDate(dto.getAddDate());
        applicant.setAddTime(dto.getAddTime());
        applicant.setUpdUser(dto.getUpdUser());
        applicant.setUpdDate(dto.getUpdDate());
        applicant.setUpdTime(dto.getUpdTime());
        applicant.setEntitledForLoan(dto.getEntitledForLoan());
        applicant.setMemberOfSamurdhi(dto.getMemberOfSamurdhi());
        applicant.setSamurdhiId(dto.getSamurdhiId());
        applicant.setSharePrice(dto.getSharePrice());
        applicant.setNoOfShares(dto.getNoOfShares());
        applicant.setLoanReference(dto.getLoanReference());
        applicant.setLoanAmount(dto.getLoanAmount());
        applicant.setCompanyName(dto.getCompanyName());
        applicant.setDeptId(dto.getDeptId());
        applicant.setFullName(dto.getFullName());
        applicant.setPersonalCorporate(dto.getPersonalCorporate());
        return applicant;
    }

    public List<ApplicantDTO> getAllApplicants() {
        return applicantRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ApplicantDTO> getApplicantById(String idNo) {
        return applicantRepository.findById(idNo)
                .map(this::convertToDTO);
    }

    //-------------------------------------------------------------------------------------

    public ApplicantDTO saveApplicant(ApplicantDTO dto) {
        Applicant applicant = convertToEntity(dto);
        return convertToDTO(applicantRepository.save(applicant));
    }

//-----------------------------------------------------------------------------------------
    @Transactional
    public ApplicantDTO updateApplicant(String idNo, ApplicantDTO dto) {
        Applicant applicant = applicantRepository.findById(idNo)
                .orElseThrow(() -> new RuntimeException("Applicant not found with ID: " + idNo));

        // === DO NOT change PK (idNo) ===
        // Only copy non-null fields from DTO (true PATCH)
        if (dto.getIdType() != null) applicant.setIdType(dto.getIdType());
        if (dto.getFirstName() != null) applicant.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) applicant.setLastName(dto.getLastName());
        if (dto.getStreetAddress() != null) applicant.setStreetAddress(dto.getStreetAddress());
        if (dto.getSuburb() != null) applicant.setSuburb(dto.getSuburb());
        if (dto.getCity() != null) applicant.setCity(dto.getCity());
        if (dto.getPostalCode() != null) applicant.setPostalCode(dto.getPostalCode());
        if (dto.getEmail() != null) applicant.setEmail(dto.getEmail());
        if (dto.getTelephoneNo() != null) applicant.setTelephoneNo(dto.getTelephoneNo());
        if (dto.getMobileNo() != null) applicant.setMobileNo(dto.getMobileNo());
        if (dto.getCebEmployee() != null) applicant.setCebEmployee(dto.getCebEmployee());
        if (dto.getPreferredLanguage() != null) applicant.setPreferredLanguage(dto.getPreferredLanguage());
        if (dto.getStatus() != null) applicant.setStatus(dto.getStatus());
        if (dto.getAddUser() != null) applicant.setAddUser(dto.getAddUser());
        if (dto.getAddDate() != null) applicant.setAddDate(dto.getAddDate());
        if (dto.getAddTime() != null) applicant.setAddTime(dto.getAddTime());
        if (dto.getUpdUser() != null) applicant.setUpdUser(dto.getUpdUser());
        if (dto.getUpdDate() != null) applicant.setUpdDate(dto.getUpdDate());
        if (dto.getUpdTime() != null) applicant.setUpdTime(dto.getUpdTime());
        if (dto.getEntitledForLoan() != null) applicant.setEntitledForLoan(dto.getEntitledForLoan());
        if (dto.getMemberOfSamurdhi() != null) applicant.setMemberOfSamurdhi(dto.getMemberOfSamurdhi());
        if (dto.getSamurdhiId() != null) applicant.setSamurdhiId(dto.getSamurdhiId());
        if (dto.getCompanyName() != null) applicant.setCompanyName(dto.getCompanyName());
        if (dto.getDeptId() != null) applicant.setDeptId(dto.getDeptId());
        if (dto.getFullName() != null) applicant.setFullName(dto.getFullName());
        if (dto.getPersonalCorporate() != null) applicant.setPersonalCorporate(dto.getPersonalCorporate());

        // Oracle NUMBER(6)/(10) have no scale -> enforce whole numbers
        if (dto.getSharePrice() != null) {
            applicant.setSharePrice(dto.getSharePrice().setScale(0, RoundingMode.DOWN));
        }
        if (dto.getNoOfShares() != null) {
            applicant.setNoOfShares(dto.getNoOfShares().setScale(0, RoundingMode.DOWN));
        }
        if (dto.getLoanAmount() != null) {
            applicant.setLoanAmount(dto.getLoanAmount().setScale(0, RoundingMode.DOWN));
        }

        // Auto-maintain update timestamp (DB column is TIMESTAMP)
        applicant.setUpdDate(new Date());

        // Final guard: required DB NOT NULLs must remain filled
        if (applicant.getIdType() == null ||
                applicant.getFirstName() == null ||
                applicant.getLastName() == null ||
                applicant.getStreetAddress() == null ||
                applicant.getPreferredLanguage() == null) {
            throw new IllegalArgumentException(
                    "Required fields cannot be null: idType, firstName, lastName, streetAddress, preferredLanguage");
        }

        return convertToDTO(applicantRepository.save(applicant));
    }

    //--------------------------------------------------------------------------------------------


    public void deleteApplicant(String idNo) {
        applicantRepository.deleteById(idNo);
    }

    public List<ApplicantDTO> getApplicantsByEstimateNo(String estimateNo) {
        Optional<Pcesthmt> estimate = pcesthmtRepository.findById(estimateNo);

        if (estimate.isPresent()) {
            String deptId = estimate.get().getId().getDeptId();
            return applicantRepository.findByDeptId(deptId)
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}