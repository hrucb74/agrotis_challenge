package com.agrotis.challenge.controller;
import com.agrotis.challenge.adapters.property.PropertyController;
import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyForm;
import com.agrotis.challenge.application.property.PropertyService;
import com.agrotis.challenge.common.messageerror.MessageError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProperty() {
        PropertyForm form = new PropertyForm();
        PropertyDTO propertyDTO = new PropertyDTO();
        when(propertyService.createProperty(form)).thenReturn(propertyDTO);

        ResponseEntity<PropertyDTO> response = propertyController.createProperty(form);

        assertEquals(propertyDTO, response.getBody());
        verify(propertyService, times(1)).createProperty(form);
    }

    @Test
    void testFindPropertyById() {
        Long id = 1L;
        PropertyDTO propertyDTO = new PropertyDTO();
        when(propertyService.findPropertyById(id)).thenReturn(propertyDTO);

        ResponseEntity<PropertyDTO> response = propertyController.findPropertyById(id);

        assertEquals(propertyDTO, response.getBody());
        verify(propertyService, times(1)).findPropertyById(id);
    }

    @Test
    void testFindAllProperties() {
        List<PropertyDTO> properties = Collections.emptyList();
        when(propertyService.findAllProperties()).thenReturn(properties);

        ResponseEntity<List<PropertyDTO>> response = propertyController.findAllProperties();

        assertEquals(properties, response.getBody());
        verify(propertyService, times(1)).findAllProperties();
    }

    @Test
    void testUpdateProperty() {
        Long id = 1L;
        PropertyForm form = new PropertyForm();
        PropertyDTO propertyDTO = new PropertyDTO();
        when(propertyService.updateProperty(id, form)).thenReturn(propertyDTO);

        ResponseEntity<PropertyDTO> response = propertyController.updateProperty(id, form);

        assertEquals(propertyDTO, response.getBody());
        verify(propertyService, times(1)).updateProperty(id, form);
    }

    @Test
    void testDeleteProperty() {
        Long id = 1L;
        MessageError messageError = MessageError.PROPERTY_DELETED_SUCCESSFULLY;
        when(propertyService.deleteProperty(id)).thenReturn(messageError);

        ResponseEntity<MessageError> response = propertyController.deleteProperty(id);

        assertEquals(messageError, response.getBody());
        verify(propertyService, times(1)).deleteProperty(id);
    }
}
