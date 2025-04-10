package com.agrotis.challenge.adapters.person;

import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.application.person.PersonService;
import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController implements PersonAPI {
    private final PersonService personService;

    @Override
    public ResponseEntity<PersonDTO> createPerson(PersonForm form) {
        return ResponseEntity.ok(personService.createPerson(form));
    }

    @Override
    public ResponseEntity<PersonDTO> findPersonById(Long id) {
        return ResponseEntity.ok(personService.findPersonById(id));
    }

    @Override
    public ResponseEntity<List<PersonDTO>> findAllPeople() {
        return ResponseEntity.ok(personService.findAllPeople());
    }

    @Override
    public ResponseEntity<PersonDTO> updatePerson(Long id, PersonForm form) {
        return ResponseEntity.ok(personService.updatePerson(id, form));
    }

    @Override
    public ResponseEntity<MessageError> deletePerson(Long id) {
        return ResponseEntity.ok(personService.deletePerson(id));
    }
}
