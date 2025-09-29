package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApplicantDTO;
import com.example.SPSProjectBackend.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping
    public List<ApplicantDTO> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchApplicantByIdNo(@RequestParam String idNo) {
        Optional<ApplicantDTO> applicantDTO = applicantService.getApplicantById(idNo);

        if (applicantDTO.isPresent()) {
            return ResponseEntity.ok(applicantDTO.get());
        } else {
            return ResponseEntity.status(404)
                    .body("Applicant with ID " + idNo + " not found.");
        }
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("hello");
        return "ok";
    }

    @PatchMapping("/{idNo}")
    public ResponseEntity<ApplicantDTO> updateApplicant(@PathVariable String idNo,
                                                        @RequestBody ApplicantDTO updatedApplicantDTO) {
        try {
            ApplicantDTO updated = applicantService.updateApplicant(idNo, updatedApplicantDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ApplicantDTO createApplicant(@RequestBody ApplicantDTO applicantDTO) {
        return applicantService.saveApplicant(applicantDTO);
    }

    @DeleteMapping("/{idNo}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable String idNo) {
        if (applicantService.getApplicantById(idNo).isPresent()) {
            applicantService.deleteApplicant(idNo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idNo}")
    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable String idNo) {
        Optional<ApplicantDTO> applicant = applicantService.getApplicantById(idNo);
        return applicant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-estimate/{estimateNo}")
    public ResponseEntity<List<ApplicantDTO>> getApplicantsByEstimateNo(@PathVariable String estimateNo) {
        List<ApplicantDTO> applicants = applicantService.getApplicantsByEstimateNo(estimateNo);
        return ResponseEntity.ok(applicants);
    }
}
