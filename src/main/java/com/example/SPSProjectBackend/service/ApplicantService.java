package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.ApplicantDTO;
import com.example.SPSProjectBackend.model.Applicant;
import com.example.SPSProjectBackend.model.Pcesthmt;
import com.example.SPSProjectBackend.repository.ApplicantRepository;
import com.example.SPSProjectBackend.repository.PcesthmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public ApplicantDTO saveApplicant(ApplicantDTO dto) {
        Applicant applicant = convertToEntity(dto);
        return convertToDTO(applicantRepository.save(applicant));
    }

    public ApplicantDTO updateApplicant(String idNo, ApplicantDTO dto) {
        Optional<Applicant> existing = applicantRepository.findById(idNo);

        if (existing.isPresent()) {
            Applicant applicant = existing.get();

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

            return convertToDTO(applicantRepository.save(applicant));
        } else {
            throw new RuntimeException("Applicant not found with ID: " + idNo);
        }
    }

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
