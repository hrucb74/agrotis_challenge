package com.agrotis.challenge.domain.property;

import com.agrotis.challenge.domain.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    @ManyToMany(mappedBy = "properties")
    private List<Person> people;

    public Property() {
    }

    public Property(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(id, property.id) && Objects.equals(name, property.name) && Objects.equals(address, property.address) && Objects.equals(people, property.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, people);
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", people=" + people +
                '}';
    }
}
