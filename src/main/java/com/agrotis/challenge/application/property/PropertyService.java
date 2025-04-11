package com.agrotis.challenge.application.property;

import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyForm;
import com.agrotis.challenge.common.messageerror.MessageError;
import com.agrotis.challenge.domain.property.Property;

import java.util.List;

public interface PropertyService {
    PropertyDTO createProperty(PropertyForm form);

    PropertyDTO findPropertyById(Long id);

    List<PropertyDTO> findAllProperties();

    PropertyDTO updateProperty(Long id, PropertyForm form);

    MessageError deleteProperty(Long id);

    Property getPropertyEntityById(Long id);
}
