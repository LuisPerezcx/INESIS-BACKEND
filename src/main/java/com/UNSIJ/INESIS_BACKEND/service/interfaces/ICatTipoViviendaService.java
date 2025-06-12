/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatTipoVivienda;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface ICatTipoViviendaService {
    List<CatTipoVivienda> findAll();

    CatTipoVivienda findById(Long id);

    CatTipoVivienda save(CatTipoVivienda model) throws Exception;

    CatTipoVivienda create(Map<String, Object> params) throws Exception;

    CatTipoVivienda update(CatTipoVivienda model, Map<String, Object> params) throws Exception;

    CatTipoVivienda build(Map<String, Object> params, CatTipoVivienda model);

    CatTipoVivienda updateInstance(CatTipoVivienda instance) throws Exception;

    void deleteById(Long id);
}
