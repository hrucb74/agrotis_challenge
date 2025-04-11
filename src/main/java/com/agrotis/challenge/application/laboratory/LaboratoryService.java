package com.agrotis.challenge.application.laboratory;

import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryCustomDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryDTO;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryFilterForm;
import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryForm;
import com.agrotis.challenge.common.messageerror.MessageError;
import com.agrotis.challenge.domain.laboratory.Laboratory;

import java.util.List;

public interface LaboratoryService {
    List<LaboratoryDTO> findAll();

    LaboratoryDTO findById(Long id);

    LaboratoryDTO createLaboratory(LaboratoryForm laboratoryForm);

    LaboratoryDTO updateLaboratory(Long id, LaboratoryForm laboratoryForm);

    MessageError deleteLaboratory(Long id);

    List<LaboratoryCustomDTO> findLaboratoriesByCustomFilters(LaboratoryFilterForm filter);

    Laboratory getLaboratoryEntityById(Long id);
}
