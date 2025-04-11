package com.agrotis.challenge.adapters.property;

import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyForm;
import com.agrotis.challenge.application.property.PropertyService;
import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PropertyController implements PropertyAPI{
    private final PropertyService propertyService;

    @Override
    public ResponseEntity<PropertyDTO> createProperty(PropertyForm form) {
        return ResponseEntity.ok(propertyService.createProperty(form));
    }

    @Override
    public ResponseEntity<PropertyDTO> findPropertyById(Long id) {
        return ResponseEntity.ok(propertyService.findPropertyById(id));
    }

    @Override
    public ResponseEntity<List<PropertyDTO>> findAllProperties() {
        return ResponseEntity.ok(propertyService.findAllProperties());
    }

    @Override
    public ResponseEntity<PropertyDTO> updateProperty(Long id, PropertyForm form) {
        return ResponseEntity.ok(propertyService.updateProperty(id, form));
    }

    @Override
    public ResponseEntity<MessageError> deleteProperty(Long id) {
        return ResponseEntity.ok(propertyService.deleteProperty(id));
    }
}
