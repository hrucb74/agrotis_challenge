package com.agrotis.challenge.controller;
import com.agrotis.challenge.adapters.laboratory.LaboratoryController;
import com.agrotis.challenge.adapters.laboratory.payload.*;
import com.agrotis.challenge.application.laboratory.LaboratoryService;
import com.agrotis.challenge.common.messageerror.MessageError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LaboratoryControllerTest {

    @Mock
    private LaboratoryService laboratoryService;

    @InjectMocks
    private LaboratoryController laboratoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        List<LaboratoryDTO> laboratories = Collections.emptyList();
        when(laboratoryService.findAll()).thenReturn(laboratories);

        ResponseEntity<List<LaboratoryDTO>> response = laboratoryController.listAll();

        assertEquals(laboratories, response.getBody());
        verify(laboratoryService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
        when(laboratoryService.findById(id)).thenReturn(laboratoryDTO);

        ResponseEntity<LaboratoryDTO> response = laboratoryController.findById(id);

        assertEquals(laboratoryDTO, response.getBody());
        verify(laboratoryService, times(1)).findById(id);
    }

    @Test
    void testCreateLab() {
        LaboratoryForm form = new LaboratoryForm();
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
        when(laboratoryService.createLaboratory(form)).thenReturn(laboratoryDTO);

        ResponseEntity<LaboratoryDTO> response = laboratoryController.createLab(form);

        assertEquals(laboratoryDTO, response.getBody());
        verify(laboratoryService, times(1)).createLaboratory(form);
    }

    @Test
    void testUpdateLab() {
        Long id = 1L;
        LaboratoryForm form = new LaboratoryForm();
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
        when(laboratoryService.updateLaboratory(id, form)).thenReturn(laboratoryDTO);

        ResponseEntity<LaboratoryDTO> response = laboratoryController.updateLab(id, form);

        assertEquals(laboratoryDTO, response.getBody());
        verify(laboratoryService, times(1)).updateLaboratory(id, form);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        MessageError messageError = MessageError.LABORATORY_DELETED_SUCCESSFULLY;
        when(laboratoryService.deleteLaboratory(id)).thenReturn(messageError);

        ResponseEntity<MessageError> response = laboratoryController.delete(id);

        assertEquals(messageError, response.getBody());
        verify(laboratoryService, times(1)).deleteLaboratory(id);
    }

    @Test
    void testFindCustom() {
        LaboratoryFilterForm filter = new LaboratoryFilterForm();
        List<LaboratoryCustomDTO> customDTOs = Collections.emptyList();
        when(laboratoryService.findLaboratoriesByCustomFilters(filter)).thenReturn(customDTOs);

        ResponseEntity<List<LaboratoryCustomDTO>> response = laboratoryController.findCustom(filter);

        assertEquals(customDTOs, response.getBody());
        verify(laboratoryService, times(1)).findLaboratoriesByCustomFilters(filter);
    }
}
