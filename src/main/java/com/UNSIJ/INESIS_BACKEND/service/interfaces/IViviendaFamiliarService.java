/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliarModel;

/**
 *
 * @author 24mda
 */
import java.util.List;
import java.util.Map;

public interface IViviendaFamiliarService {
List<ViviendaFamiliarModel> findAll();
    ViviendaFamiliarModel findById(Long id);
    ViviendaFamiliarModel save(ViviendaFamiliarModel model) throws Exception;
    ViviendaFamiliarModel create(Map<String, Object> params) throws Exception;
    ViviendaFamiliarModel update(ViviendaFamiliarModel model, Map<String, Object> params) throws Exception;
    ViviendaFamiliarModel build(Map<String, Object> params, ViviendaFamiliarModel model);
    ViviendaFamiliarModel updateInstance(ViviendaFamiliarModel instance) throws Exception;
    void deleteById(Long id);
    

}
