package com.agrotis.challenge.application.property;

import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyForm;
import com.agrotis.challenge.common.exception.ResourceNotFoundException;
import com.agrotis.challenge.common.messageerror.MessageError;
import com.agrotis.challenge.domain.property.Property;
import com.agrotis.challenge.infrastructure.persistence.property.PropertyRepository;
import com.agrotis.challenge.common.exception.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;

    @Override
    public PropertyDTO createProperty(PropertyForm form) {
        validatePropertyExists(form.getName());
        Property property = new Property(form.getName());

        if (form.getAddress() != null) property.setAddress(form.getAddress());

        return new PropertyDTO(propertyRepository.save(property));
    }

    @Override
    public PropertyDTO findPropertyById(Long id) {
        return new PropertyDTO(getPropertyEntityById(id));
    }

    @Override
    public List<PropertyDTO> findAllProperties() {
        return propertyRepository.findAll().stream()
                .map(PropertyDTO::new)
                .toList();
    }

    @Override
    public PropertyDTO updateProperty(Long id, PropertyForm form) {
        Property property = getPropertyEntityById(id);
        property.setName(form.getName());
        if (form.getAddress() != null) property.setAddress(form.getAddress());

        return new PropertyDTO(propertyRepository.save(property));
    }

    @Override
    public MessageError deleteProperty(Long id) {
        Property property = getPropertyEntityById(id);

        if (property.getPeople() != null && !property.getPeople().isEmpty()) {
            throw new IllegalArgumentException(MessageError.CANNOT_DELETE_PROPERTY_HAS_PEOPLE);
        }

        propertyRepository.deleteById(id);
        return MessageError.PROPERTY_DELETED_SUCCESSFULLY;
    }

    @Override
    public Property getPropertyEntityById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageError.PROPERTY_NOT_FOUND));
    }

    private void validatePropertyExists(String name) {
        if (propertyRepository.existsByNameIgnoreCase(name)) {
            throw new IllegalArgumentException(MessageError.PROPERTY_ALREADY_EXISTS);
        }
    }
}
