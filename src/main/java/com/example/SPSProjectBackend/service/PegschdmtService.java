package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pegschdmt;
import com.example.SPSProjectBackend.model.PegschdmtId;
import com.example.SPSProjectBackend.model.Pcestdtt;
import com.example.SPSProjectBackend.model.SpPeggingDmt;
import com.example.SPSProjectBackend.repository.PegschdmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PegschdmtService {

    // Dependencies on other services are added
    private final PegschdmtRepository repository;
    private final PcestdttService pcestdttService;
    private final SpPeggingDmtService spPeggingDmtService;

    /**
     * The constructor is updated to accept the new service dependencies.
     * Spring will automatically provide them (Dependency Injection).
     */
    @Autowired
    public PegschdmtService(PegschdmtRepository repository, PcestdttService pcestdttService, SpPeggingDmtService spPeggingDmtService) {
        this.repository = repository;
        this.pcestdttService = pcestdttService;
        this.spPeggingDmtService = spPeggingDmtService;
    }

    public List<Pegschdmt> getAll() {
        return repository.findAll();
    }

    public Optional<Pegschdmt> getById(PegschdmtId id) {
        return repository.findById(id);
    }

    /**
     * This is the updated save method with the new business logic.
     * It is marked as @Transactional to ensure data integrity.
     */
    @Transactional
    public Pegschdmt save(Pegschdmt data) {
        // Step 1: Save the main node record (Pegschdmt) to the database.
        Pegschdmt savedPegschdmt = repository.save(data);

        // Step 2: Get key information from the saved record.
        String estimateNo = savedPegschdmt.getEstimateNo();
        String nodeId = savedPegschdmt.getNodeId(); // This ID is used to find materials
        String deptId = savedPegschdmt.getDeptId();
        double parentItemQuantity = savedPegschdmt.getNoOfItem(); // e.g., number of poles

        // Step 3: Find the list of all materials required for this 'nodeId'.
        // This matches PEGSCHDMT.NODEID with SPPEGGINGDMT.LINESECTIONTYPEID.
        List<SpPeggingDmt> materialTemplates = spPeggingDmtService.findByLineSectionTypeId(nodeId);

        // Step 4: Loop through each material template found.
        for (SpPeggingDmt template : materialTemplates) {

            // Step 5: Create a new Pcestdtt object for each material.
            Pcestdtt newMaterialRecord = new Pcestdtt();

            // Step 6: Fill the new material record with the correct data.
            // --- Set the Primary Key values ---
            newMaterialRecord.setEstimateNo(estimateNo);
            newMaterialRecord.setResCd(template.getId().getResCd()); // Material code from template
            newMaterialRecord.setDeptId(deptId);
            newMaterialRecord.setRevNo(BigDecimal.ONE); // Default revision to 1

            // --- Set other values ---
            newMaterialRecord.setUom(template.getUom());
            if (template.getUnitPrice() != null) {
                newMaterialRecord.setUnitPrice(BigDecimal.valueOf(template.getUnitPrice()));
            }
            newMaterialRecord.setResType(template.getResType());

            // Calculate the total quantity needed
            BigDecimal quantityPerUnit = template.getEstimateQty();
            if (quantityPerUnit != null) {
                BigDecimal totalQuantity = quantityPerUnit.multiply(BigDecimal.valueOf(parentItemQuantity));
                newMaterialRecord.setEstimateQty(totalQuantity);
            }

            // Step 7: Save the new material record to the PCESTDTT table.
            pcestdttService.save(newMaterialRecord);
        }

        // Step 8: Return the original saved record as required by the controller.
        return savedPegschdmt;
    }

    public void delete(PegschdmtId id) {
        // Business logic to delete associated Pcestdtt records could be added here later if needed.
        repository.deleteById(id);
    }
}