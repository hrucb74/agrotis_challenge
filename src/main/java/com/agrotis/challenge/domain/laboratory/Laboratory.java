package com.agrotis.challenge.domain.laboratory;

import jakarta.persistence.*;
import com.agrotis.challenge.domain.person.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL)
    private List<Person> people;
}
