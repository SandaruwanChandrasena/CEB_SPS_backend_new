package com.example.SPSProjectBackend.controller;

import com.example.SPSProjectBackend.model.SpPeggingDmt;
import com.example.SPSProjectBackend.model.SpPeggingDmtId;
import com.example.SPSProjectBackend.service.SpPeggingDmtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spPeggingDmt")
public class SpPeggingDmtController {

    private final SpPeggingDmtService service;

    public SpPeggingDmtController(SpPeggingDmtService service) {
        this.service = service;
    }

    @GetMapping
    public List<SpPeggingDmt> getAll() {
        return service.findAll();
    }

    @GetMapping("/{lineSectionTypeId}/{resCd}/{deptId}")
    public Optional<SpPeggingDmt> getById(
            @PathVariable String lineSectionTypeId,
            @PathVariable String resCd,
            @PathVariable String deptId
    ) {
        SpPeggingDmtId id = new SpPeggingDmtId();
        id.setLineSectionTypeId(lineSectionTypeId);
        id.setResCd(resCd);
        id.setDeptId(deptId);
        return service.findById(id);
    }

    @GetMapping("/lineSectionType/{lineSectionTypeId}")
    public List<SpPeggingDmt> getByLineSectionTypeId(@PathVariable String lineSectionTypeId) {
        return service.findByLineSectionTypeId(lineSectionTypeId);
    }

    @PostMapping
    public SpPeggingDmt create(@RequestBody SpPeggingDmt spPeggingDmt) {
        return service.save(spPeggingDmt);
    }

    @PutMapping
    public SpPeggingDmt update(@RequestBody SpPeggingDmt spPeggingDmt) {
        return service.save(spPeggingDmt);
    }

    @DeleteMapping("/{lineSectionTypeId}/{resCd}/{deptId}")
    public void delete(
            @PathVariable String lineSectionTypeId,
            @PathVariable String resCd,
            @PathVariable String deptId
    ) {
        SpPeggingDmtId id = new SpPeggingDmtId();
        id.setLineSectionTypeId(lineSectionTypeId);
        id.setResCd(resCd);
        id.setDeptId(deptId);
        service.deleteById(id);
    }
}