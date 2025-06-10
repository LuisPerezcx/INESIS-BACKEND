/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamiliaModel;

import java.util.List;
import java.util.Map;
/**
 *
 * @author 24mda
 */
public interface IMiFamiliaService {
    List<MiFamiliaModel> findAll();
    MiFamiliaModel findById(Long id);
    MiFamiliaModel save(MiFamiliaModel model) throws Exception;
    MiFamiliaModel create(Map<String, Object> params) throws Exception;
    MiFamiliaModel update(MiFamiliaModel model, Map<String, Object> params) throws Exception;
    MiFamiliaModel build(Map<String, Object> params, MiFamiliaModel model);
    void deleteById(Long id); 
}
