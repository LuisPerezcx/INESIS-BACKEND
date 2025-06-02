/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.BienesHogarModel;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author 24mda
 */
public interface IBienesHogarService {

    List<BienesHogarModel> findAll();

    BienesHogarModel findById(Long id);

    BienesHogarModel save(BienesHogarModel model) throws Exception;

    BienesHogarModel create(Map<String, Object> params) throws Exception;

    BienesHogarModel update(BienesHogarModel model, Map<String, Object> params) throws Exception;

    BienesHogarModel build(Map<String, Object> params, BienesHogarModel model);

    BienesHogarModel updateInstance(BienesHogarModel instance) throws Exception;

    void deleteById(Long id);
}
