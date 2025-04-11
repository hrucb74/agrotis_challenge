package com.agrotis.challenge.adapters.laboratory.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LaboratoryForm {
    @NotNull
    private String code;
    @NotNull
    private String name;
}
