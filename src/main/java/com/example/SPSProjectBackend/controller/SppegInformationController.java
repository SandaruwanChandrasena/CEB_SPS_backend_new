package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.SppegInformation;
import com.example.SPSProjectBackend.model.SppegInformationId;
import com.example.SPSProjectBackend.service.SppegInformationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/sppeg")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class SppegInformationController {

    private static final Logger logger = LoggerFactory.getLogger(SppegInformationController.class);

    @Autowired
    private SppegInformationService service;

    @GetMapping
    public List<SppegInformation> getAll() {
        logger.info("Fetching all SppegInformation records");
        return service.getAll();
    }

    @GetMapping("/{sppeg_id}/{parent_id}/{department_id}")
    public Optional<SppegInformation> getById(@PathVariable("sppeg_id") String sppegId,
                                              @PathVariable("parent_id") String parentId,
                                              @PathVariable("department_id") String departmentId) {
        SppegInformationId key = new SppegInformationId(sppegId, parentId, departmentId);
        return service.getById(key);
    }

    @PostMapping
    public SppegInformation create(@RequestBody SppegInformation info) {
        return service.save(info);
    }

    @PutMapping
    public SppegInformation update(@RequestBody SppegInformation info) {
        return service.save(info);
    }

    @DeleteMapping("/{sppeg_id}/{parent_id}/{department_id}")
    public void delete(@PathVariable("sppeg_id") String sppegId,
                       @PathVariable("parent_id") String parentId,
                       @PathVariable("department_id") String departmentId) {
        SppegInformationId key = new SppegInformationId(sppegId, parentId, departmentId);
        service.deleteById(key);
    }

    @GetMapping("/filter")
    public List<SppegInformation> getPeggingTreeNodes(@RequestParam("department_id") String departmentId,
                                                      @RequestParam("parent_id") String parentId) {
        return service.getByParentId(departmentId, parentId);
    }

}
