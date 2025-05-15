/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatEscolaridadModel;

/**
 *
 * @author 24mda
 */
import java.util.List;
import java.util.Map;

public interface ICatEscolaridadService {
    List<CatEscolaridadModel> findAll();
    CatEscolaridadModel findById(Long id);
    CatEscolaridadModel save(CatEscolaridadModel model) throws Exception;
    CatEscolaridadModel create(Map<String, Object> params) throws Exception;
    CatEscolaridadModel update(CatEscolaridadModel model, Map<String, Object> params) throws Exception;
    CatEscolaridadModel build(Map<String, Object> params, CatEscolaridadModel model);
    CatEscolaridadModel updateInstance(CatEscolaridadModel instance) throws Exception;
    void deleteById(Long id);
}
