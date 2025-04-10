package com.agrotis.challenge.application.laboratory;

import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryCustomDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryFilterForm;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryForm;
import com.agrotis.challenge.common.exception.ResourceNotFoundException;
import com.agrotis.challenge.common.exception.IllegalArgumentException;
import com.agrotis.challenge.domain.laboratory.Laboratory;
import com.agrotis.challenge.domain.person.Person;
import com.agrotis.challenge.infrastructure.persistence.laboratory.LaboratoryRepository;
import com.agrotis.challenge.common.messageerror.MessageError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LaboratoryServiceImpl implements LaboratoryService{
    private final LaboratoryRepository laboratoryRepository;

    @Override
    public List<LaboratoryDTO> findAll() {
        return laboratoryRepository.findAll()
                .stream().map(LaboratoryDTO::new)
                .toList();
    }

    @Override
    public LaboratoryDTO findById(Long id) {
        Laboratory laboratory = getLaboratoryEntityById(id);
        return new LaboratoryDTO(
                laboratory.getId(),
                laboratory.getName(),
                laboratory.getCode(),
                laboratory.getPeople()
        );
    }

    @Override
    public LaboratoryDTO createLaboratory(LaboratoryForm laboratoryForm) {
        validateLaboratoryExists(laboratoryForm.getCode());

        Laboratory newLaboratory = new Laboratory(laboratoryForm.getName(), laboratoryForm.getCode());
        if (laboratoryForm.getPeople() != null) newLaboratory.setPeople(laboratoryForm.getPeople());

        return new LaboratoryDTO(laboratoryRepository.save(newLaboratory));
    }

    @Override
    public LaboratoryDTO updateLaboratory(Long id, LaboratoryForm laboratoryForm) {
        Laboratory existingLaboratory = getLaboratoryEntityById(id);

        if (!existingLaboratory.getCode().equals(laboratoryForm.getCode())) validateLaboratoryExists(laboratoryForm.getCode());

        existingLaboratory.setName(laboratoryForm.getName());
        existingLaboratory.setCode(laboratoryForm.getCode());
        if (laboratoryForm.getPeople() != null) existingLaboratory.setPeople(laboratoryForm.getPeople());

        return new LaboratoryDTO(laboratoryRepository.save(existingLaboratory));
    }

    @Override
    public MessageError deleteLaboratory(Long id) {
        Laboratory existingLaboratory = getLaboratoryEntityById(id);
        if (existingLaboratory.getPeople() != null && !existingLaboratory.getPeople().isEmpty()) {
            throw new IllegalArgumentException(MessageError.CANNOT_DELETE_LABORATORY_HAS_PEOPLE);
        }

        laboratoryRepository.deleteById(id);
        return MessageError.LABORATORY_DELETED;
    }

    @Override
    public List<LaboratoryCustomDTO> findLaboratoriesByCustomFilters(LaboratoryFilterForm filter) {
        List<Laboratory> laboratories = laboratoryRepository.findAll();

        List<Laboratory> filteredLabs = laboratories.stream()
                .filter(lab -> isValidLab(lab, filter))
                .sorted((lab1, lab2) -> compareLabs(lab1, lab2, filter))
                .toList();

        return filteredLabs.stream()
                .map(this::mapToCustomDTO)
                .toList();
    }

    @Override
    public Laboratory getLaboratoryEntityById(Long id) {
        return laboratoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageError.LABORATORY_NOT_FOUND));
    }

    private void validateLaboratoryExists(String code) {
        if (laboratoryRepository.existsByCodeIgnoreCase(code)) {
            throw new IllegalArgumentException(MessageError.LABORATORY_ALREADY_EXISTS);
        }
    }

    private boolean isValidLab(Laboratory lab, LaboratoryFilterForm filter) {
        if (!hasMinPeople(lab, filter.getMinPeopleCount())) return false;
        if (!matchesInitialDateRange(lab, filter)) return false;
        if (!matchesEndDateRange(lab, filter)) return false;
        if (!matchesObservationKeyword(lab, filter)) return false;
        return true;
    }

    private boolean hasMinPeople(Laboratory lab, Integer minPeople) {
        return lab.getPeople() != null && lab.getPeople().size() >= minPeople;
    }

    private boolean matchesInitialDateRange(Laboratory lab, LaboratoryFilterForm filter) {
        if (filter.getPersonInitialDateStart() == null && filter.getPersonInitialDateEnd() == null) return true;

        return lab.getPeople().stream().anyMatch(person -> {
            LocalDate date = person.getInitialDate();
            return (filter.getPersonInitialDateStart() == null || !date.isBefore(filter.getPersonInitialDateStart())) &&
                    (filter.getPersonInitialDateEnd() == null || !date.isAfter(filter.getPersonInitialDateEnd()));
        });
    }

    private boolean matchesEndDateRange(Laboratory lab, LaboratoryFilterForm filter) {
        if (filter.getPersonEndDateStart() == null && filter.getPersonEndDateEnd() == null) return true;

        return lab.getPeople().stream().anyMatch(person -> {
            LocalDate endDate = person.getEndDate();
            return endDate != null &&
                    (filter.getPersonEndDateStart() == null || !endDate.isBefore(filter.getPersonEndDateStart())) &&
                    (filter.getPersonEndDateEnd() == null || !endDate.isAfter(filter.getPersonEndDateEnd()));
        });
    }

    private boolean matchesObservationKeyword(Laboratory lab, LaboratoryFilterForm filter) {
        String keyword = filter.getObservationKeyword();
        if (keyword == null || keyword.isBlank()) return true;

        return lab.getPeople().stream().anyMatch(person ->
                person.getDescription() != null &&
                        person.getDescription().toLowerCase().contains(keyword.toLowerCase())
        );
    }

    private Integer compareLabs(Laboratory lab1, Laboratory lab2, LaboratoryFilterForm filter) {
        int peopleComparison = Integer.compare(
                lab2.getPeople() != null ? lab2.getPeople().size() : 0,
                lab1.getPeople() != null ? lab1.getPeople().size() : 0
        );

        if (peopleComparison != 0) return peopleComparison;

        if (filter.getPersonInitialDateStart() != null || filter.getPersonInitialDateEnd() != null) {
            LocalDate oldestDate1 = lab1.getPeople().stream()
                    .map(Person::getInitialDate)
                    .min(LocalDate::compareTo).orElse(null);
            LocalDate oldestDate2 = lab2.getPeople().stream()
                    .map(Person::getInitialDate)
                    .min(LocalDate::compareTo).orElse(null);

            if (oldestDate1 != null && oldestDate2 != null) {
                return oldestDate1.compareTo(oldestDate2);
            }
        }

        return 0;
    }

    private LaboratoryCustomDTO mapToCustomDTO(Laboratory lab) {
        return new LaboratoryCustomDTO(
                lab.getId(),
                lab.getCode(),
                lab.getName().toUpperCase(),
                lab.getPeople() != null ? lab.getPeople().size() : 0
        );
    }
}
