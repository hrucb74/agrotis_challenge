package com.agrotis.challenge.service;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.adapters.property.payload.PropertyDTO;
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
        form.setInitialDate(LocalDate.of(2023, 1, 1));
        form.setEndDate(LocalDate.of(2023, 12, 31));

        PropertyDTO property1 = new PropertyDTO();
        property1.setId(1L);
        property1.setName("Property 1");

        PropertyDTO property2 = new PropertyDTO();
        property2.setId(2L);
        property2.setName("Property 2");

        form.setPropertyInfos(List.of(property1, property2));

        LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
        laboratoryDTO.setId(1L);
        laboratoryDTO.setName("Laboratory 1");
        form.setLaboratory(laboratoryDTO);

        Laboratory laboratory = new Laboratory();
        laboratory.setId(1L);
        laboratory.setName("Laboratory 1");

        Property propertyEntity1 = new Property();
        propertyEntity1.setId(1L);
        propertyEntity1.setName("Property 1");

        Property propertyEntity2 = new Property();
        propertyEntity2.setId(2L);
        propertyEntity2.setName("Property 2");

        when(laboratoryService.getLaboratoryEntityById(1L)).thenReturn(laboratory);
        when(propertyService.getPropertyEntityById(1L)).thenReturn(propertyEntity1);
        when(propertyService.getPropertyEntityById(2L)).thenReturn(propertyEntity2);

        Person person = new Person("John Doe", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), laboratory);
        person.setProperties(List.of(propertyEntity1, propertyEntity2));
        when(personRepository.save(any(Person.class))).thenReturn(person);
        PersonDTO result = personService.createPerson(form);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("Laboratory 1", result.getLaboratoryName());
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
        form.setInitialDate(LocalDate.of(2023, 1, 1));
        form.setEndDate(LocalDate.of(2023, 12, 31));

        PropertyDTO property1 = new PropertyDTO();
        property1.setId(1L);
        property1.setName("Property 1");

        PropertyDTO property2 = new PropertyDTO();
        property2.setId(2L);
        property2.setName("Property 2");

        form.setPropertyInfos(List.of(property1, property2));

        LaboratoryDTO laboratory = new LaboratoryDTO();
        laboratory.setId(1L);
        laboratory.setName("Laboratory 1");
        form.setLaboratory(laboratory);

        Person person = new Person();
        person.setName("Original Name");
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        when(laboratoryService.getLaboratoryEntityById(1L)).thenReturn(new Laboratory());
        when(propertyService.getPropertyEntityById(1L)).thenReturn(new Property());
        when(propertyService.getPropertyEntityById(2L)).thenReturn(new Property());
        when(personRepository.save(any(Person.class))).thenReturn(person);
        PersonDTO result = personService.updatePerson(id, form);

        assertNotNull(result);
        assertEquals("Updated Name", person.getName());
        assertEquals(LocalDate.of(2023, 1, 1), person.getInitialDate());
        assertEquals(LocalDate.of(2023, 12, 31), person.getEndDate());
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