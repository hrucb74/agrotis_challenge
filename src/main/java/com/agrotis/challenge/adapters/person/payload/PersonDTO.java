package com.agrotis.challenge.adapters.person.payload;

import com.agrotis.challenge.domain.person.Person;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PersonDTO {
    private Long id;
    private String name;
    private LocalDate initialDate;
    private LocalDate endDate;
    private String description;
    private String laboratoryName;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.initialDate = person.getInitialDate();
        this.endDate = person.getEndDate();
        this.description = person.getDescription();
        this.laboratoryName = person.getLaboratory().getName();
    }
}
