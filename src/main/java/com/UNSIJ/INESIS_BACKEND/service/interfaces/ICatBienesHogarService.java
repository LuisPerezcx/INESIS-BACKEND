/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogarModel;

/**
 *
 * @author 24mda
 */
import java.util.List;
import java.util.Map;

public interface ICatBienesHogarService {

    List<CatBienesHogarModel> findAll();

    CatBienesHogarModel findById(Long id);

    CatBienesHogarModel save(CatBienesHogarModel model) throws Exception;

    CatBienesHogarModel create(Map<String, Object> params) throws Exception;

    CatBienesHogarModel update(CatBienesHogarModel model, Map<String, Object> params) throws Exception;

    CatBienesHogarModel build(Map<String, Object> params, CatBienesHogarModel model);

    CatBienesHogarModel updateInstance(CatBienesHogarModel instance) throws Exception;

    void deleteById(Long id);
}
