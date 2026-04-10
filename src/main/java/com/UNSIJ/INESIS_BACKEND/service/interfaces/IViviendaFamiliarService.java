/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliar;

import java.util.List;
import java.util.Map;

public interface IViviendaFamiliarService {
    List<ViviendaFamiliar> findAll();

    ViviendaFamiliar findById(Long id);

    ViviendaFamiliar save(ViviendaFamiliar model) throws Exception;

    ViviendaFamiliar create(Map<String, Object> params, MiFamilia miFamilia) throws Exception;

    ViviendaFamiliar update(ViviendaFamiliar model, Map<String, Object> params) throws Exception;

    ViviendaFamiliar build(Map<String, Object> params, ViviendaFamiliar model, MiFamilia miFamilia);

    ViviendaFamiliar updateInstance(ViviendaFamiliar instance) throws Exception;

    void deleteById(Long id);


}
