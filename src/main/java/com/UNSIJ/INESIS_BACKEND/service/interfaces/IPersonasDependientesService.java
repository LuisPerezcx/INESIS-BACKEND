/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.PersonasDependientesModel;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Alumnos
 */
public interface  IPersonasDependientesService {
List<PersonasDependientesModel> findAll();
    PersonasDependientesModel findById(Long id);
    PersonasDependientesModel save(PersonasDependientesModel model) throws Exception;
    PersonasDependientesModel create(Map<String, Object> params) throws Exception;
    PersonasDependientesModel update(PersonasDependientesModel model, Map<String, Object> params) throws Exception;
    PersonasDependientesModel build(Map<String, Object> params, PersonasDependientesModel model);
    void deleteById(Long id);
}
