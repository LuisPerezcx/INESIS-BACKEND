/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.PersonasDependientes;

import java.util.List;
import java.util.Map;

/**
 * @author Alumnos
 */
public interface IPersonasDependientesService {
    List<PersonasDependientes> findAll();

    PersonasDependientes findById(Long id);

    PersonasDependientes save(PersonasDependientes model) throws Exception;

    PersonasDependientes create(Map<String, Object> params) throws Exception;

    PersonasDependientes update(PersonasDependientes model, Map<String, Object> params) throws Exception;

    PersonasDependientes build(Map<String, Object> params, PersonasDependientes model);

    void deleteById(Long id);
}
