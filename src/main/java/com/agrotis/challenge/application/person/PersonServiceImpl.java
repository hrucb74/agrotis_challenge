package com.agrotis.challenge.application.person;

import com.agrotis.challenge.adapters.person.payload.PersonDTO;
import com.agrotis.challenge.adapters.person.payload.PersonForm;
import com.agrotis.challenge.application.laboratory.LaboratoryService;
import com.agrotis.challenge.application.property.PropertyService;
import com.agrotis.challenge.common.exception.ResourceNotFoundException;
import com.agrotis.challenge.common.messageerror.MessageError;
import com.agrotis.challenge.domain.laboratory.Laboratory;
import com.agrotis.challenge.domain.person.Person;
import com.agrotis.challenge.domain.property.Property;
import com.agrotis.challenge.infrastructure.persistence.person.PersonRepository;
import com.agrotis.challenge.common.exception.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final LaboratoryService laboratoryService;
    private final PropertyService propertyService;

    @Override
    public PersonDTO createPerson(PersonForm Personform) {
        Laboratory laboratory = laboratoryService.getLaboratoryEntityById(Personform.getLaboratory().getId());
        validatePersonExists(Personform.getName(), laboratory.getId());

        Person person = new Person(Personform.getName(), Personform.getInitialDate(), Personform.getEndDate(), laboratory);
        if (Personform.getDescription() != null) person.setDescription(Personform.getDescription());
        List<Property> properties = Personform.getPropertyInfos().stream()
                .map(propertyDTO -> propertyService.getPropertyEntityById(propertyDTO.getId()))
                .toList();
        person.setProperties(properties);

        return new PersonDTO(personRepository.save(person));
    }

    @Override
    public PersonDTO findPersonById(Long id) {
        return new PersonDTO(getPersonEntityById(id));
    }

    @Override
    public Page<PersonDTO> findAllPeople(Pageable pageable) {
        return personRepository.findAll(pageable)
                .map(PersonDTO::new);
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonForm Personform) {
        Person person = getPersonEntityById(id);
        Laboratory laboratory = laboratoryService.getLaboratoryEntityById(Personform.getLaboratory().getId());

        if (!person.getName().equals(Personform.getName()) ||
                !person.getLaboratory().getId().equals(laboratory.getId())) {
            validatePersonExists(Personform.getName(), laboratory.getId());
        }

        person.setName(Personform.getName());
        person.setInitialDate(Personform.getInitialDate());
        person.setEndDate(Personform.getEndDate());
        person.setLaboratory(laboratory);
        if (Personform.getDescription() != null) person.setDescription(Personform.getDescription());

        List<Property> properties = Personform.getPropertyInfos().stream()
                .map(propertyDTO -> propertyService.getPropertyEntityById(propertyDTO.getId()))
                .toList();
        person.setProperties(properties);

        return new PersonDTO(personRepository.save(person));
    }

    @Override
    public MessageError deletePerson(Long id) {
        getPersonEntityById(id);
        personRepository.deleteById(id);
        return MessageError.PERSON_DELETED_SUCCESSFULLY;
    }

    private Person getPersonEntityById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageError.PERSON_NOT_FOUND));
    }

    private void validatePersonExists(String name, Long laboratoryId) {
        if (personRepository.existsByNameIgnoreCaseAndLaboratoryId(name, laboratoryId)) {
            throw new IllegalArgumentException(MessageError.PERSON_ALREADY_EXISTS);
        }
    }
}
