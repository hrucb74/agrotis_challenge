package com.agrotis.challenge.adapters.laboratory;

import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryCustomDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryFilterForm;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryForm;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.common.messageerror.MessageError;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${spring.agrotis.base.path}/${spring.agrotis.base.version}/laboratory")
public interface LaboratoryAPI {

    @GetMapping
    ResponseEntity<List<LaboratoryDTO>> listAll();

    @GetMapping("/{id}")
    ResponseEntity<LaboratoryDTO> findById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<LaboratoryDTO> createLab(@Valid @RequestBody LaboratoryForm laboratoryForm);

    @PutMapping("/{id}")
    ResponseEntity<LaboratoryDTO> updateLab(@PathVariable Long id, @Valid @RequestBody LaboratoryForm laboratoryForm);

    @DeleteMapping("/{id}")
    ResponseEntity<MessageError> delete(@PathVariable Long id);

    @GetMapping("/allcustom")
    ResponseEntity<List<LaboratoryCustomDTO>> findCustom(@Valid @RequestBody LaboratoryFilterForm filter);


}
