package com.agrotis.challenge.adapters.person.payload;

import lombok.Data;

@Data
public class PersonSummaryDTO {
    private Long id;
    private String name;

    public PersonSummaryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
