/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogar;

import java.util.List;
import java.util.Map;

public interface ICatBienesHogarService {

    List<CatBienesHogar> findAll();

    CatBienesHogar findById(Long id);

    CatBienesHogar save(CatBienesHogar model) throws Exception;

    CatBienesHogar create(Map<String, Object> params) throws Exception;

    CatBienesHogar update(CatBienesHogar model, Map<String, Object> params) throws Exception;

    CatBienesHogar build(Map<String, Object> params, CatBienesHogar model);

    CatBienesHogar updateInstance(CatBienesHogar instance) throws Exception;

    void deleteById(Long id);
}
