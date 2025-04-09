package com.agrotis.challenge.domain.property;

import com.agrotis.challenge.domain.laboratory.Laboratory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Laboratory> laboratories;
}
