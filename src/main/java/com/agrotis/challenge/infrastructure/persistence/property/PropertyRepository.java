package com.agrotis.challenge.infrastructure.persistence.property;

import com.agrotis.challenge.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByNameIgnoreCase(String name);
}
