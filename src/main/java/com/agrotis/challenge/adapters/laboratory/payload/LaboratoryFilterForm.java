package com.agrotis.challenge.adapters.laboratory.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LaboratoryFilterForm {
    private LocalDate personInitialDateStart;
    private LocalDate personInitialDateEnd;
    private LocalDate personEndDateStart;
    private LocalDate personEndDateEnd;
    private String observationKeyword;
    @NotNull
    private Integer minPeopleCount;
}
