package com.agrotis.challenge.adapters.person;

import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.common.messageerror.MessageError;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${spring.agrotis.base.path}/${spring.agrotis.base.version}/person")
public interface PersonAPI {

    @PostMapping
    ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonForm form);

    @GetMapping("/{id}")
    ResponseEntity<PersonDTO> findPersonById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<PersonDTO>> findAllPeople(Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonForm form);

    @DeleteMapping("/{id}")
    ResponseEntity<MessageError> deletePerson(@PathVariable Long id);
}
