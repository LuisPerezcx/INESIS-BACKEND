/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudioModel;

import java.util.List;
import java.util.Map;
/**
 *
 * @author 24mda
 */
public interface IMediosEstudiosService {
    List<MediosEstudioModel> findAll();
    MediosEstudioModel findById(Long id);
    MediosEstudioModel save(MediosEstudioModel model) throws Exception;
    MediosEstudioModel create(Map<String, Object> params) throws Exception;
    MediosEstudioModel update(MediosEstudioModel model, Map<String, Object> params) throws Exception;
    MediosEstudioModel build(Map<String, Object> params, MediosEstudioModel model);
    MediosEstudioModel updateInstance(MediosEstudioModel instance) throws Exception;
    void deleteById(Long id);
}
