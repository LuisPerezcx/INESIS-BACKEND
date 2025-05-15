/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMaterialViviendaModel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 24mda
 */
public interface ICatMaterialViviendaService {

    List<CatMaterialViviendaModel> findAll();

    CatMaterialViviendaModel findById(Long id);

    CatMaterialViviendaModel save(CatMaterialViviendaModel model) throws Exception;

    CatMaterialViviendaModel create(Map<String, Object> params) throws Exception;

    CatMaterialViviendaModel update(CatMaterialViviendaModel model, Map<String, Object> params) throws Exception;

    CatMaterialViviendaModel build(Map<String, Object> params, CatMaterialViviendaModel model);

    CatMaterialViviendaModel updateInstance(CatMaterialViviendaModel instance) throws Exception;

    void deleteById(Long id);
}
