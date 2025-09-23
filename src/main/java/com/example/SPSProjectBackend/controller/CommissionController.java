package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.CommissionDTO;
import com.example.SPSProjectBackend.service.PcesthmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commission")
public class CommissionController {

    private final PcesthmtService pcesthmtService;

    @Autowired
    public CommissionController(PcesthmtService pcesthmtService) {
        this.pcesthmtService = pcesthmtService;
    }

    @GetMapping("/details")
    public ResponseEntity<List<CommissionDTO>> getCommissionDetails() {
        List<CommissionDTO> commissionDetails = pcesthmtService.getCommissionDetails();
        return ResponseEntity.ok(commissionDetails);
    }
}