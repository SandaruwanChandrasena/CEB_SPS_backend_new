package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.Pcesthtt;
import com.example.SPSProjectBackend.model.PcesthttId;
import com.example.SPSProjectBackend.service.PcesthttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pcesthtt")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PcesthttController {

    @Autowired
    private PcesthttService service;

    // --- Row count by status ---
    @GetMapping("/row-count-by-status")
    public Map<Short, Long> getRowCountByStatus() {
        return service.getRowCountByStatus();
    }

    @GetMapping
    public List<Pcesthtt> getAll() {
        return service.findAll();
    }

    @GetMapping("/{estimateNo}/{revNo}/{deptId}")
    public ResponseEntity<Pcesthtt> getById(
            @PathVariable String estimateNo,
            @PathVariable Short revNo,
            @PathVariable String deptId) {

        PcesthttId id = new PcesthttId();
        id.setEstimateNo(estimateNo);
        id.setRevNo(revNo);
        id.setDeptId(deptId);

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estimateNos")
    public List<String> getEstimateNumbers() {
        return service.findAllEstimateNos();
    }

    @GetMapping("/{estimateNo}")
    public ResponseEntity<?> getByEstimateNo(@PathVariable String estimateNo) {
        String decodedEstimateNo = URLDecoder.decode(estimateNo, StandardCharsets.UTF_8);
        Optional<Pcesthtt> entity = service.findByEstimateNo(decodedEstimateNo);

        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        } else {
            return ResponseEntity.status(404)
                    .body("Estimate with number " + decodedEstimateNo + " not found");
        }
    }

    @PostMapping
    public Pcesthtt create(@RequestBody Pcesthtt entity) {
        return service.save(entity);
    }

    @PutMapping("/{estimateNo}")
    public ResponseEntity<Pcesthtt> update(
            @PathVariable String estimateNo,
            @RequestBody Pcesthtt entity) {

        String decodedEstimateNo = URLDecoder.decode(estimateNo, StandardCharsets.UTF_8);

        Optional<Pcesthtt> existingEntity = service.findByEstimateNo(decodedEstimateNo);
        if (!existingEntity.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }

        if (!entity.getId().getEstimateNo().equals(decodedEstimateNo)) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{estimateNo}/{revNo}/{deptId}")
    public ResponseEntity<Void> delete(
            @PathVariable String estimateNo,
            @PathVariable Short revNo,
            @PathVariable String deptId) {

        PcesthttId id = new PcesthttId();
        id.setEstimateNo(estimateNo);
        id.setRevNo(revNo);
        id.setDeptId(deptId);

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
