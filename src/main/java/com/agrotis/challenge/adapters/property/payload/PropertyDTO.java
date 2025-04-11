package com.agrotis.challenge.adapters.property.payload;
import com.agrotis.challenge.adapters.person.payload.PersonSummaryDTO;
import com.agrotis.challenge.domain.person.Person;
import com.agrotis.challenge.domain.property.Property;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PropertyDTO {
    private Long id;
    private String name;
    private String address;
    private List<PersonSummaryDTO> people;

    public PropertyDTO() {
    }

    public PropertyDTO(Property property) {
        this.id = property.getId();
        this.name = property.getName();
        this.address = property.getAddress();
        this.people = property.getPeople() != null
                ? property.getPeople().stream()
                .map(person -> new PersonSummaryDTO(person.getId(), person.getName()))
                .toList() : List.of();
    }
}
