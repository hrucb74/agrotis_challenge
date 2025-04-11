package com.agrotis.challenge.adapters.laboratory.payload;

import com.agrotis.challenge.domain.laboratory.Laboratory;
import com.agrotis.challenge.domain.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class LaboratoryDTO {
    private Long id;
    private String code;
    private String name;
    private List<Person> people;

    public LaboratoryDTO() {
    }

    public LaboratoryDTO(Laboratory lab) {
        this.id = lab.getId();
        this.code = lab.getCode();
        this.name = lab.getName();
        this.people = lab.getPeople();
    }
}