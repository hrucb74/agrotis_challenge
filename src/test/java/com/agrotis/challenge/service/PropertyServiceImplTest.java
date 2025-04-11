package com.agrotis.challenge.service;
import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyForm;
import com.agrotis.challenge.application.property.PropertyServiceImpl;
import com.agrotis.challenge.common.exception.ResourceNotFoundException;
import com.agrotis.challenge.common.messageerror.MessageError;
import com.agrotis.challenge.domain.person.Person;
import com.agrotis.challenge.domain.property.Property;
import com.agrotis.challenge.infrastructure.persistence.property.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProperty() {
        PropertyForm form = new PropertyForm();
        form.setName("Property Name");
        form.setAddress("Property Address");

        Property property = new Property("Property Name");
        property.setAddress("Property Address");

        when(propertyRepository.existsByNameIgnoreCase("Property Name")).thenReturn(false);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyDTO result = propertyService.createProperty(form);

        assertNotNull(result);
        assertEquals("Property Name", result.getName());
        verify(propertyRepository, times(1)).existsByNameIgnoreCase("Property Name");
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    void testCreateProperty_AlreadyExists() {
        PropertyForm form = new PropertyForm();
        form.setName("Property Name");

        when(propertyRepository.existsByNameIgnoreCase("Property Name")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> propertyService.createProperty(form));
        verify(propertyRepository, times(1)).existsByNameIgnoreCase("Property Name");
        verify(propertyRepository, never()).save(any(Property.class));
    }

    @Test
    void testFindPropertyById() {
        Long id = 1L;
        Property property = new Property("Property Name");
        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));

        PropertyDTO result = propertyService.findPropertyById(id);

        assertNotNull(result);
        assertEquals("Property Name", result.getName());
        verify(propertyRepository, times(1)).findById(id);
    }

    @Test
    void testFindPropertyById_NotFound() {
        Long id = 1L;
        when(propertyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> propertyService.findPropertyById(id));
        verify(propertyRepository, times(1)).findById(id);
    }

    @Test
    void testFindAllProperties() {
        List<Property> properties = Collections.emptyList();
        when(propertyRepository.findAll()).thenReturn(properties);

        List<PropertyDTO> result = propertyService.findAllProperties();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(propertyRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProperty() {
        Long id = 1L;
        PropertyForm form = new PropertyForm();
        form.setName("Updated Name");
        form.setAddress("Updated Address");

        Property property = new Property("Old Name");
        property.setAddress("Old Address");

        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyDTO result = propertyService.updateProperty(id, form);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(propertyRepository, times(1)).findById(id);
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    void testDeleteProperty() {
        Long id = 1L;
        Property property = new Property("Property Name");
        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));

        MessageError result = propertyService.deleteProperty(id);

        assertEquals(MessageError.PROPERTY_DELETED_SUCCESSFULLY, result);
        verify(propertyRepository, times(1)).findById(id);
        verify(propertyRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteProperty_HasPeople() {
        Long id = 1L;
        Property property = new Property("Property Name");
        property.setPeople(List.of(new Person()));
        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));

        assertThrows(IllegalArgumentException.class, () -> propertyService.deleteProperty(id));
        verify(propertyRepository, times(1)).findById(id);
        verify(propertyRepository, never()).deleteById(anyLong());
    }
}
