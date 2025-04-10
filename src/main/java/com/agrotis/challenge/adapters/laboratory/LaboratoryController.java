package com.agrotis.challenge.adapters.laboratory;

import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryCustomDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryFilterForm;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryForm;
import com.agrotis.challenge.application.laboratory.LaboratoryService;
import com.agrotis.challenge.common.messageerror.MessageError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<MessageError> delete(@PathVariable Long id){
        return ResponseEntity.ok(laboratoryService.deleteLaboratory(id));
    }

    @Override
    public ResponseEntity<List<LaboratoryCustomDTO>> findCustom(@Valid @RequestParam LaboratoryFilterForm filter) {
        return ResponseEntity.ok(laboratoryService.findLaboratoriesByCustomFilters(filter));
    }
}
