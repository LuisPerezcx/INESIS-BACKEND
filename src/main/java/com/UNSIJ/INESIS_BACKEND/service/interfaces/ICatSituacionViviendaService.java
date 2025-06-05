/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionViviendaModel;

/**
 *
 * @author 24mda
 */
import java.util.List;
import java.util.Map;
public interface ICatSituacionViviendaService {
    List<CatSituacionViviendaModel> findAll();
    CatSituacionViviendaModel findById(Long id);
    CatSituacionViviendaModel save(CatSituacionViviendaModel model) throws Exception;
    CatSituacionViviendaModel create(Map<String, Object> params) throws Exception;
    CatSituacionViviendaModel update(CatSituacionViviendaModel model, Map<String, Object> params) throws Exception;
    CatSituacionViviendaModel build(Map<String, Object> params, CatSituacionViviendaModel model);
    CatSituacionViviendaModel updateInstance(CatSituacionViviendaModel instance) throws Exception;
    void deleteById(Long id);
}
