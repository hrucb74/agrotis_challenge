package com.agrotis.challenge.domain.person;

import com.agrotis.challenge.domain.laboratory.Laboratory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate initialDate;
    private LocalDate endDate;
    private String description;
    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

    public Person() {
    }

    public Person(String name, LocalDate initialDate, LocalDate endDate, Laboratory laboratory) {
        this.name = name;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.laboratory = laboratory;
    }
}
