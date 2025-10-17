package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.dto.ApplicationDto;
import com.example.SPSProjectBackend.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8095") // your nginx; adjust if needed
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    // Create (Save)
    @PostMapping
    public ResponseEntity<ApplicationDto> create(@RequestBody ApplicationDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // Read by composite key
    // NOTE: if applicationId has slashes, client must URL-encode it
    @GetMapping("/{applicationId}/{deptId}")
    public ResponseEntity<ApplicationDto> read(@PathVariable String applicationId,
                                               @PathVariable String deptId) {
        return ResponseEntity.ok(service.read(applicationId, deptId));
    }

    // Update (Edit)
    @PutMapping("/{applicationId}/{deptId}")
    public ResponseEntity<ApplicationDto> update(@PathVariable String applicationId,
                                                 @PathVariable String deptId,
                                                 @RequestBody ApplicationDto dto) {
        return ResponseEntity.ok(service.update(applicationId, deptId, dto));
    }

    // Search latest by applicationId (IDs like 423.10/ANC/2011/0818)
    @GetMapping("/search")
    public ResponseEntity<ApplicationDto> searchLatest(@RequestParam String applicationId) {
        return ResponseEntity.ok(service.findLatest(applicationId));
    }

    // List all by applicationId
    @GetMapping("/by-appid")
    public ResponseEntity<List<ApplicationDto>> listByAppId(@RequestParam String applicationId) {
        return ResponseEntity.ok(service.listByApplicationId(applicationId));
    }

    // List all for an applicant idNo
    @GetMapping("/by-applicant")
    public ResponseEntity<List<ApplicationDto>> listByApplicant(@RequestParam String idNo) {
        return ResponseEntity.ok(service.listByApplicant(idNo));
    }
}
