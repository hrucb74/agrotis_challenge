package com.agrotis.challenge.adapters.laboratory.payload;

import com.agrotis.challenge.domain.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LaboratoryForm {
    @NotNull
    private String code;
    @NotNull
    private String name;
    private List<Person> people;
}
