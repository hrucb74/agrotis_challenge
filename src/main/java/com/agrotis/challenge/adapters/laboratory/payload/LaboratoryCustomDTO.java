package com.agrotis.challenge.adapters.laboratory.payload;

import lombok.Data;

@Data
public class LaboratoryCustomDTO {
    private Long id;
    private String code;
    private String name;
    private Integer qtdPeople;

    public LaboratoryCustomDTO(Long id, String code, String name, Integer qtdPeople) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.qtdPeople = qtdPeople;
    }
}
