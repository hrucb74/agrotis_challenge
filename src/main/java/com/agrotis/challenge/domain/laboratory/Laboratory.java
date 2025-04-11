package com.agrotis.challenge.domain.laboratory;

import jakarta.persistence.*;
import com.agrotis.challenge.domain.person.Person;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "laboratory")
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL)
    private List<Person> people;

    public Laboratory() {
    }

    public Laboratory(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laboratory that = (Laboratory) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, people);
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", people=" + people +
                '}';
    }
}
