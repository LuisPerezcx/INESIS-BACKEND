/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMaterialVivienda;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface ICatMaterialViviendaService {

    List<CatMaterialVivienda> findAll();

    CatMaterialVivienda findById(Long id);

    CatMaterialVivienda save(CatMaterialVivienda model) throws Exception;

    CatMaterialVivienda create(Map<String, Object> params) throws Exception;

    CatMaterialVivienda update(CatMaterialVivienda model, Map<String, Object> params) throws Exception;

    CatMaterialVivienda build(Map<String, Object> params, CatMaterialVivienda model);

    CatMaterialVivienda updateInstance(CatMaterialVivienda instance) throws Exception;

    void deleteById(Long id);
}
