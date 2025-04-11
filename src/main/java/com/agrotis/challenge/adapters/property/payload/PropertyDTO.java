package com.agrotis.challenge.adapters.property.payload;
import com.agrotis.challenge.domain.person.Person;
import com.agrotis.challenge.domain.property.Property;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PropertyDTO {
    private Long id;
    private String name;
    private String address;
    private List<Person> people;

    public PropertyDTO() {
    }

    public PropertyDTO(Property property) {
        this.id = property.getId();
        this.name = property.getName();
        this.address = property.getAddress();
        this.people = property.getPeople() != null ? property.getPeople() : Collections.emptyList();
    }
}
