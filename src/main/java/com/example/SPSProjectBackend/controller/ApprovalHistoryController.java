package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApprovalHistoryDTO;
import com.example.SPSProjectBackend.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/approval-history")
public class ApprovalHistoryController {

    @Autowired
    private ApprovalService approvalService;

    @GetMapping("/{estimateNo}")
    public ResponseEntity<List<ApprovalHistoryDTO>> getApprovalHistory(
            @PathVariable String estimateNo) {
        List<ApprovalHistoryDTO> history = approvalService.getApprovalHistoryByEstimateNo(estimateNo);
        return ResponseEntity.ok(history);
    }
}