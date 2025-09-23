package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.ApprovalHistoryDTO;
import com.example.SPSProjectBackend.model.Approval;
import com.example.SPSProjectBackend.repository.ApprovalRepository;
import com.example.SPSProjectBackend.repository.PcesthmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    @Autowired
    private PcesthmtRepository pcesthmtRepository;

    public List<ApprovalHistoryDTO> getApprovalHistoryByEstimateNo(String estimateNo) {
        List<Approval> approvals = approvalRepository.findByEstimateNo(estimateNo);

        // Handle case where approvals is empty
        if (approvals == null || approvals.isEmpty()) {
            return Collections.emptyList();
        }

        return approvals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ApprovalHistoryDTO convertToDTO(Approval approval) {
        return new ApprovalHistoryDTO(
                approval.getApprovedBy(),
                approval.getApprovedDate(),
                approval.getApprovedTime() != null ? approval.getApprovedTime() : "",
                approval.getApprovedLevel(),
                approval.getReason() != null ? approval.getReason() : "",
                approval.getFromStatus(),
                approval.getToStatus()
        );
    }
}