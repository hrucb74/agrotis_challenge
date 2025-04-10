package com.agrotis.challenge.adapters.person.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PersonForm {
    @NotNull
    private String name;
    @NotNull
    private LocalDate initialDate;
    @NotNull
    private LocalDate endDate;
    private String description;
    @NotNull
    private Long laboratoryId;
}

