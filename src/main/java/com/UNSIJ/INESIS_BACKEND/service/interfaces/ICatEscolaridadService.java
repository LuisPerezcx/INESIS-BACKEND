/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatEscolaridad;

import java.util.List;
import java.util.Map;

public interface ICatEscolaridadService {
    List<CatEscolaridad> findAll();

    CatEscolaridad findById(Long id);

    CatEscolaridad save(CatEscolaridad model) throws Exception;

    CatEscolaridad create(Map<String, Object> params) throws Exception;

    CatEscolaridad update(CatEscolaridad model, Map<String, Object> params) throws Exception;

    CatEscolaridad build(Map<String, Object> params, CatEscolaridad model);

    CatEscolaridad updateInstance(CatEscolaridad instance) throws Exception;

    void deleteById(Long id);
}
