/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatTipoViviendaModel;

import java.util.List;
import java.util.Map;
/**
 *
 * @author 24mda
 */
public interface ICatTipoViviendaService {
    List<CatTipoViviendaModel> findAll();
    CatTipoViviendaModel findById(Long id);
    CatTipoViviendaModel save(CatTipoViviendaModel model) throws Exception;
    CatTipoViviendaModel create(Map<String, Object> params) throws Exception;
    CatTipoViviendaModel update(CatTipoViviendaModel model, Map<String, Object> params) throws Exception;
    CatTipoViviendaModel build(Map<String, Object> params, CatTipoViviendaModel model);
    CatTipoViviendaModel updateInstance(CatTipoViviendaModel instance) throws Exception;
    void deleteById(Long id);
}
