package com.agrotis.challenge.adapters.laboratory.payload;

import com.agrotis.challenge.adapters.person.payload.PersonSummaryDTO;
import com.agrotis.challenge.domain.laboratory.Laboratory;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class LaboratoryDTO {
    private Long id;
    private String code;
    private String name;
    private List<PersonSummaryDTO> people;

    public LaboratoryDTO() {
    }

    public LaboratoryDTO(Laboratory lab) {
        this.id = lab.getId();
        this.code = lab.getCode();
        this.name = lab.getName();
        this.people = lab.getPeople() != null
                ? lab.getPeople().stream()
                .map(person -> new PersonSummaryDTO(person.getId(), person.getName()))
                .toList() : List.of();
    }
}