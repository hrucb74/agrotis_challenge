package com.agrotis.challenge.adapters.laboratory;

import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryForm;
import com.agrotis.challenge.application.laboratory.LaboratoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LaboratoryController implements LaboratoryAPI{
    private final LaboratoryService laboratoryService;

    @Override
    public ResponseEntity<List<LaboratoryDTO>> listAll(){
        return ResponseEntity.ok(laboratoryService.findAll());
    }

    @Override
    public ResponseEntity<LaboratoryDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(laboratoryService.findById(id));
    }

    @Override
    public ResponseEntity<LaboratoryDTO> createLab(@Valid @RequestBody LaboratoryForm laboratoryForm){
        return ResponseEntity.ok(laboratoryService.createLaboratory(laboratoryForm));
    }

    @Override
    public ResponseEntity<LaboratoryDTO> updateLab(@PathVariable Long id, @Valid @RequestBody LaboratoryForm laboratoryForm){
        return ResponseEntity.ok(laboratoryService.updateLaboratory(id, laboratoryForm));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return ResponseEntity.ok(laboratoryService.deleteLaboratory(id));
    }
}
