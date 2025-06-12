/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatInternet;

import java.util.List;
import java.util.Map;

public interface ICatInternetService {

    List<CatInternet> findAll();

    CatInternet findById(Long id);

    CatInternet save(CatInternet model) throws Exception;

    CatInternet create(Map<String, Object> params) throws Exception;

    CatInternet update(CatInternet model, Map<String, Object> params) throws Exception;

    CatInternet build(Map<String, Object> params, CatInternet model);

    CatInternet updateInstance(CatInternet instance) throws Exception;

    void deleteById(Long id);
}
