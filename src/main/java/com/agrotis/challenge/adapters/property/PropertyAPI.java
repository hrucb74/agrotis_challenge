package com.agrotis.challenge.adapters.property;

import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyForm;
import com.agrotis.challenge.common.messageerror.MessageError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${spring.agrotis.base.path}/${spring.agrotis.base.version}/property")
public interface PropertyAPI {
    @PostMapping
    ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyForm form);

    @GetMapping("/{id}")
    ResponseEntity<PropertyDTO> findPropertyById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<PropertyDTO>>  findAllProperties();

    @PutMapping("/{id}")
    ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyForm form);

    @DeleteMapping("/{id}")
    ResponseEntity<MessageError> deleteProperty(@PathVariable Long id);
}
