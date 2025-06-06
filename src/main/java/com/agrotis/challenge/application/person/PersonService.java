package com.agrotis.challenge.application.person;

import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.common.messageerror.MessageError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
    PersonDTO createPerson(PersonForm form);

    PersonDTO findPersonById(Long id);

    Page<PersonDTO> findAllPeople(Pageable pageable);

    PersonDTO updatePerson(Long id, PersonForm form);

    MessageError deletePerson(Long id);
}
