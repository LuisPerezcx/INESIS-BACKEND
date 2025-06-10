/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudioModel;
import java.util.List;
import java.util.Map;
/**
 *
 * @author 24mda
 */
public interface ICatMediosEstudioService {

    List<CatMediosEstudioModel> findAll();
    CatMediosEstudioModel findById(Long id);
    CatMediosEstudioModel save(CatMediosEstudioModel model) throws Exception;
    CatMediosEstudioModel create(Map<String, Object> params) throws Exception;
    CatMediosEstudioModel update(CatMediosEstudioModel model, Map<String, Object> params) throws Exception;
    CatMediosEstudioModel build(Map<String, Object> params, CatMediosEstudioModel model);
    CatMediosEstudioModel updateInstance(CatMediosEstudioModel instance) throws Exception;
    void deleteById(Long id);
}
