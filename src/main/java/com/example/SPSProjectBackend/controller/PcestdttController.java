package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.PcestdttId;
import com.example.SPSProjectBackend.service.PcestdttService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pcestdtt")
public class PcestdttController {

    private final PcestdttService service;

    public PcestdttController(PcestdttService service) {
        this.service = service;
    }

    // Get all records (without pagination)
    @GetMapping("/all")
    public ResponseEntity<List<Pcestdtt>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Get all records with pagination
    @GetMapping
    public ResponseEntity<Page<Pcestdtt>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllPaged(pageable));
    }

    // Get record by ID
    @GetMapping("/{estimateNo}/{revNo}/{deptId}/{resCd}")
    public ResponseEntity<?> getById(
            @PathVariable String estimateNo,
            @PathVariable BigDecimal revNo,
            @PathVariable String deptId,
            @PathVariable String resCd
    ) {
        PcestdttId id = new PcestdttId(estimateNo, revNo, deptId, resCd);
        Optional<Pcestdtt> existing = service.getById(id);
        if (existing.isPresent()) {
            return ResponseEntity.ok(existing.get());
        } else {
            return ResponseEntity.status(404).body("Record not found");
        }
    }

    // Create a new record
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pcestdtt pcestdtt) {
        try {
            Pcestdtt saved = service.save(pcestdtt);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Create failed: " + e.getMessage());
        }
    }

    // Update an existing record
    @PutMapping("/{estimateNo}/{revNo}/{deptId}/{resCd}")
    public ResponseEntity<?> update(
            @PathVariable String estimateNo,
            @PathVariable BigDecimal revNo,
            @PathVariable String deptId,
            @PathVariable String resCd,
            @RequestBody Map<String, String> updates
    ) {
        try {
            PcestdttId id = new PcestdttId(estimateNo, revNo, deptId, resCd);
            Optional<Pcestdtt> existing = service.getById(id);

            if (!existing.isPresent()) {
                return ResponseEntity.status(404).body("Record not found");
            }

            Pcestdtt pcestdtt = existing.get();

            // Apply updates dynamically
            updates.forEach((key, value) -> {
                switch (key) {
                    case "resType": pcestdtt.setResType(value); break;
                    case "uom": pcestdtt.setUom(value); break;
                    case "unitPrice": pcestdtt.setUnitPrice(new BigDecimal(value)); break;
                    case "estimateQty": pcestdtt.setEstimateQty(new BigDecimal(value)); break;
                    case "estimateCost": pcestdtt.setEstimateCost(new BigDecimal(value)); break;
                    case "approvedQty": pcestdtt.setApprovedQty(new BigDecimal(value)); break;
                    case "approvedCost": pcestdtt.setApprovedCost(new BigDecimal(value)); break;
                    case "returnedQty": pcestdtt.setReturnedQty(new BigDecimal(value)); break;
                    case "returnedCost": pcestdtt.setReturnedCost(new BigDecimal(value)); break;
                    case "damageQty": pcestdtt.setDamageQty(new BigDecimal(value)); break;
                    default: throw new IllegalArgumentException("Invalid field: " + key);
                }
            });

            Pcestdtt updated = service.save(pcestdtt);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Update failed: " + e.getMessage());
        }
    }

    // Delete a record
    @DeleteMapping("/{estimateNo}/{revNo}/{deptId}/{resCd}")
    public ResponseEntity<?> delete(
            @PathVariable String estimateNo,
            @PathVariable BigDecimal revNo,
            @PathVariable String deptId,
            @PathVariable String resCd
    ) {
        PcestdttId id = new PcestdttId(estimateNo, revNo, deptId, resCd);
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Delete failed: " + e.getMessage());
        }
    }
}