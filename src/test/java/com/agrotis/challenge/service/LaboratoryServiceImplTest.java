package com.agrotis.challenge.service;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryForm;
import com.agrotis.challenge.application.laboratory.LaboratoryServiceImpl;
import com.agrotis.challenge.common.exception.ResourceNotFoundException;
import com.agrotis.challenge.common.messageerror.MessageError;
import com.agrotis.challenge.domain.laboratory.Laboratory;
import com.agrotis.challenge.domain.person.Person;
import com.agrotis.challenge.infrastructure.persistence.laboratory.LaboratoryRepository;
import com.agrotis.challenge.service.factory.LaboratoryFormFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratoryServiceImplTest {

    @Mock
    private LaboratoryRepository laboratoryRepository;

    @InjectMocks
    private LaboratoryServiceImpl laboratoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Laboratory> laboratories = Collections.emptyList();
        when(laboratoryRepository.findAll()).thenReturn(laboratories);

        List<LaboratoryDTO> result = laboratoryService.findAll();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(laboratoryRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Laboratory laboratory = new Laboratory("Code","Lab Name");
        when(laboratoryRepository.findById(id)).thenReturn(Optional.of(laboratory));

        LaboratoryDTO result = laboratoryService.findById(id);

        assertNotNull(result);
        assertEquals("Lab Name", result.getName());
        verify(laboratoryRepository, times(1)).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(laboratoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> laboratoryService.findById(id));
        verify(laboratoryRepository, times(1)).findById(id);
    }

    @Test
    void testCreateLaboratory() {

        LaboratoryForm form = LaboratoryFormFactory.createLaboratoryFormWithCustomValues("Lab Name", "Code");
        Laboratory laboratory = new Laboratory( "Code", "Lab Name");
        when(laboratoryRepository.existsByCodeIgnoreCase("Code")).thenReturn(false);
        when(laboratoryRepository.save(any(Laboratory.class))).thenReturn(laboratory);

        LaboratoryDTO result = laboratoryService.createLaboratory(form);

        assertNotNull(result);
        assertEquals("Lab Name", result.getName());
        verify(laboratoryRepository, times(1)).existsByCodeIgnoreCase("Code");
        verify(laboratoryRepository, times(1)).save(any(Laboratory.class));
    }

    @Test
    void testUpdateLaboratory() {
        Long id = 1L;
        LaboratoryForm form = LaboratoryFormFactory.createLaboratoryFormWithCustomValues("Updated Name", "Updated Code");
        Laboratory laboratory = new Laboratory("Old Name", "Old Code");
        when(laboratoryRepository.findById(id)).thenReturn(Optional.of(laboratory));
        when(laboratoryRepository.existsByCodeIgnoreCase("Updated Code")).thenReturn(false);
        when(laboratoryRepository.save(any(Laboratory.class))).thenReturn(laboratory);

        LaboratoryDTO result = laboratoryService.updateLaboratory(id, form);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(laboratoryRepository, times(1)).findById(id);
        verify(laboratoryRepository, times(1)).existsByCodeIgnoreCase("Updated Code");
        verify(laboratoryRepository, times(1)).save(any(Laboratory.class));
    }

    @Test
    void testDeleteLaboratory() {
        Long id = 1L;
        Laboratory laboratory = new Laboratory("Lab Name", "Code");
        when(laboratoryRepository.findById(id)).thenReturn(Optional.of(laboratory));

        MessageError result = laboratoryService.deleteLaboratory(id);

        assertEquals(MessageError.LABORATORY_DELETED_SUCCESSFULLY, result);
        verify(laboratoryRepository, times(1)).findById(id);
        verify(laboratoryRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteLaboratory_HasPeople() {
        Long id = 1L;
        Laboratory laboratory = new Laboratory("Lab Name", "Code");
        laboratory.setPeople(List.of(new Person()));
        when(laboratoryRepository.findById(id)).thenReturn(Optional.of(laboratory));

        assertThrows(com.agrotis.challenge.common.exception.IllegalArgumentException.class,
                () -> laboratoryService.deleteLaboratory(id));
        verify(laboratoryRepository, times(1)).findById(id);
        verify(laboratoryRepository, never()).deleteById(id);
    }
}
