package com.agrotis.challenge.adapters.person.payload;

import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PersonForm {
    @NotNull
    private String name;
    @NotNull
    private LocalDate initialDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private List<PropertyDTO> propertyInfos;
    @NotNull
    private LaboratoryDTO laboratory;
    private String description;
}

