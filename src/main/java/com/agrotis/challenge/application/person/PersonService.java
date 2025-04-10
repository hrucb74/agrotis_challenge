package com.agrotis.challenge.application.person;

import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.common.messageerror.MessageError;

import java.util.List;

public interface PersonService {
    PersonDTO createPerson(PersonForm form);

    PersonDTO findPersonById(Long id);

    List<PersonDTO> findAllPeople();

    PersonDTO updatePerson(Long id, PersonForm form);

    MessageError deletePerson(Long id);
}
