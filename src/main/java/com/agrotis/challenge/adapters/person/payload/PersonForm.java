package com.agrotis.challenge.adapters.person.payload;

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
    private List<Long> propertyIds;
    @NotNull
    private Long laboratoryId;
    private String description;
}

