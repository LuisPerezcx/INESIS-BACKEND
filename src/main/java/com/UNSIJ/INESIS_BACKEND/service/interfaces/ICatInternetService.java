/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatInternetModel;

/**
 *
 * @author 24mda
 */
import java.util.List;
import java.util.Map;

public interface ICatInternetService {

    List<CatInternetModel> findAll();

    CatInternetModel findById(Long id);

    CatInternetModel save(CatInternetModel model) throws Exception;

    CatInternetModel create(Map<String, Object> params) throws Exception;

    CatInternetModel update(CatInternetModel model, Map<String, Object> params) throws Exception;

    CatInternetModel build(Map<String, Object> params, CatInternetModel model);

    CatInternetModel updateInstance(CatInternetModel instance) throws Exception;

    void deleteById(Long id);
}
