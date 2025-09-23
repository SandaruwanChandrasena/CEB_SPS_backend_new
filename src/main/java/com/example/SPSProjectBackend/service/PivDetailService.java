package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.dto.PivDetailDto;
import com.example.SPSProjectBackend.model.PivDetail;
import com.example.SPSProjectBackend.repository.PivDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PivDetailService {

    private final PivDetailRepository pivDetailRepository;

    @Autowired
    public PivDetailService(PivDetailRepository pivDetailRepository) {
        this.pivDetailRepository = pivDetailRepository;
    }

    public List<PivDetailDto> getPivDetailsForEstimate(String estimateNo) {
        return pivDetailRepository.findByEstReferenceNo(estimateNo)
                .stream()
                .map(piv -> new PivDetailDto(
                        piv.getId().getPivNo(),
                        piv.getId().getDeptId(),
                        piv.getPivAmount(),
                        piv.getPaymentMode(),
                        piv.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
