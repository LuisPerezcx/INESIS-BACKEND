/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionVivienda;

import java.util.List;
import java.util.Map;

public interface ICatSituacionViviendaService {
    List<CatSituacionVivienda> findAll();

    CatSituacionVivienda findById(Long id);

    CatSituacionVivienda save(CatSituacionVivienda model) throws Exception;

    CatSituacionVivienda create(Map<String, Object> params) throws Exception;

    CatSituacionVivienda update(CatSituacionVivienda model, Map<String, Object> params) throws Exception;

    CatSituacionVivienda build(Map<String, Object> params, CatSituacionVivienda model);

    CatSituacionVivienda updateInstance(CatSituacionVivienda instance) throws Exception;

    void deleteById(Long id);
}
