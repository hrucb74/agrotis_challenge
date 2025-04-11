package com.agrotis.challenge.domain.person;

import com.agrotis.challenge.domain.laboratory.Laboratory;
import com.agrotis.challenge.domain.property.Property;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "person")
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
    @ManyToMany
    @JoinTable(
            name = "person_property",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private List<Property> properties;

    public Person() {
    }

    public Person(String name, LocalDate initialDate, LocalDate endDate, Laboratory laboratory) {
        this.name = name;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.laboratory = laboratory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(initialDate, person.initialDate) && Objects.equals(endDate, person.endDate) && Objects.equals(description, person.description) && Objects.equals(laboratory, person.laboratory) && Objects.equals(properties, person.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, initialDate, endDate, description, laboratory, properties);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialDate=" + initialDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", laboratory=" + laboratory +
                ", properties=" + properties +
                '}';
    }
}
