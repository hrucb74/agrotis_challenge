package com.agrotis.challenge.infrastructure.persistence.person;

import com.agrotis.challenge.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByNameIgnoreCaseAndLaboratoryId(String name, Long laboratoryId);
}
