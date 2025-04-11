package com.agrotis.challenge.service;
import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.application.laboratory.LaboratoryService;
import com.agrotis.challenge.application.person.PersonServiceImpl;
import com.agrotis.challenge.application.property.PropertyService;
import com.agrotis.challenge.common.exception.ResourceNotFoundException;
import com.agrotis.challenge.common.messageerror.MessageError;
import com.agrotis.challenge.domain.laboratory.Laboratory;
import com.agrotis.challenge.domain.person.Person;
import com.agrotis.challenge.domain.property.Property;
import com.agrotis.challenge.infrastructure.persistence.person.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private LaboratoryService laboratoryService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePerson() {
        PersonForm form = new PersonForm();
        form.setName("John Doe");
        form.setLaboratoryId(1L);
        form.setPropertyIds(List.of(1L, 2L));

        Laboratory laboratory = new Laboratory();
        Property property1 = new Property();
        Property property2 = new Property();
        Person person = new Person("John Doe", null, null, laboratory);

        when(laboratoryService.getLaboratoryEntityById(1L)).thenReturn(laboratory);
        when(propertyService.getPropertyEntityById(1L)).thenReturn(property1);
        when(propertyService.getPropertyEntityById(2L)).thenReturn(property2);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonDTO result = personService.createPerson(form);

        assertNotNull(result);
        verify(laboratoryService, times(1)).getLaboratoryEntityById(1L);
        verify(propertyService, times(2)).getPropertyEntityById(anyLong());
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void testFindPersonById() {
        Long id = 1L;
        Person person = new Person("John Doe", LocalDate.now(), LocalDate.now().plusDays(1), new Laboratory());
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        PersonDTO result = personService.findPersonById(id);

        assertNotNull(result);
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    void testFindPersonById_NotFound() {
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.findPersonById(id));
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    void testFindAllPeople() {
        Pageable pageable = mock(Pageable.class);
        Page<Person> page = new PageImpl<>(Collections.emptyList());
        when(personRepository.findAll(pageable)).thenReturn(page);

        Page<PersonDTO> result = personService.findAllPeople(pageable);

        assertNotNull(result);
        verify(personRepository, times(1)).findAll(pageable);
    }

    @Test
    void testUpdatePerson() {
        Long id = 1L;
        PersonForm form = new PersonForm();
        form.setName("Updated Name");
        form.setLaboratoryId(1L);
        form.setPropertyIds(List.of(1L, 2L));

        Person person = new Person();
        person.setName("Original Name");
        Laboratory laboratory = new Laboratory();
        Property property1 = new Property();
        Property property2 = new Property();

        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        when(laboratoryService.getLaboratoryEntityById(1L)).thenReturn(laboratory);
        when(propertyService.getPropertyEntityById(1L)).thenReturn(property1);
        when(propertyService.getPropertyEntityById(2L)).thenReturn(property2);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonDTO result = personService.updatePerson(id, form);

        assertNotNull(result);
        verify(personRepository, times(1)).findById(id);
        verify(laboratoryService, times(1)).getLaboratoryEntityById(1L);
        verify(propertyService, times(2)).getPropertyEntityById(anyLong());
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void testDeletePerson() {
        Long id = 1L;
        Person person = new Person();
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        MessageError result = personService.deletePerson(id);

        assertEquals(MessageError.PERSON_DELETED_SUCCESSFULLY, result);
        verify(personRepository, times(1)).findById(id);
        verify(personRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeletePerson_NotFound() {
        Long id = 1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.deletePerson(id));
        verify(personRepository, times(1)).findById(id);
        verify(personRepository, never()).deleteById(anyLong());
    }
}