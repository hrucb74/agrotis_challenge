package com.agrotis.challenge.adapters.property.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropertyForm {
    @NotNull
    private String name;
    private String address;
}
