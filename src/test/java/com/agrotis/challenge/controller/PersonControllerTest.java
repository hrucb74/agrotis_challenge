package com.agrotis.challenge.controller;

import com.agrotis.challenge.adapters.person.PersonController;
import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.application.person.PersonService;
import com.agrotis.challenge.common.messageerror.MessageError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonControllerTest {
    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePerson() {
        PersonForm form = new PersonForm();
        PersonDTO personDTO = new PersonDTO();
        when(personService.createPerson(form)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personController.createPerson(form);

        assertEquals(personDTO, response.getBody());
        verify(personService, times(1)).createPerson(form);
    }

    @Test
    void testFindPersonById() {
        Long id = 1L;
        PersonDTO personDTO = new PersonDTO();
        when(personService.findPersonById(id)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personController.findPersonById(id);

        assertEquals(personDTO, response.getBody());
        verify(personService, times(1)).findPersonById(id);
    }

    @Test
    void testFindAllPeople() {
        Pageable pageable = mock(Pageable.class);
        Page<PersonDTO> page = new PageImpl<>(Collections.emptyList());
        when(personService.findAllPeople(pageable)).thenReturn(page);

        ResponseEntity<Page<PersonDTO>> response = personController.findAllPeople(pageable);

        assertEquals(page, response.getBody());
        verify(personService, times(1)).findAllPeople(pageable);
    }

    @Test
    void testUpdatePerson() {
        Long id = 1L;
        PersonForm form = new PersonForm();
        PersonDTO personDTO = new PersonDTO();
        when(personService.updatePerson(id, form)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personController.updatePerson(id, form);

        assertEquals(personDTO, response.getBody());
        verify(personService, times(1)).updatePerson(id, form);
    }

    @Test
    void testDeletePerson() {
        Long id = 1L;
        MessageError messageError = MessageError.PERSON_DELETED_SUCCESSFULLY;
        when(personService.deletePerson(id)).thenReturn(messageError);

        ResponseEntity<MessageError> response = personController.deletePerson(id);

        assertEquals(messageError, response.getBody());
        verify(personService, times(1)).deletePerson(id);
    }

}
