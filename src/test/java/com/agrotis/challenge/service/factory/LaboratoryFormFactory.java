package com.agrotis.challenge.service.factory;

import com.agrotis.challenge.adapters.laboratory.payload.LaboratoryForm;

public class LaboratoryFormFactory {

    public static LaboratoryForm createValidLaboratoryForm() {
        LaboratoryForm form = new LaboratoryForm();
        form.setName("Lab Name");
        form.setCode("Code123");
        return form;
    }

    public static LaboratoryForm createLaboratoryFormWithCustomValues(String name, String code) {
        LaboratoryForm form = new LaboratoryForm();
        form.setName(name);
        form.setCode(code);
        return form;
    }
}

