/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.HermanosDependientesModel;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Alumnos
 */
public interface  IHermanosDependientesService {
List<HermanosDependientesModel> findAll();
    HermanosDependientesModel findById(Long id);
    HermanosDependientesModel save(HermanosDependientesModel model) throws Exception;
    HermanosDependientesModel create(Map<String, Object> params) throws Exception;
    HermanosDependientesModel update(HermanosDependientesModel model, Map<String, Object> params) throws Exception;
    HermanosDependientesModel build(Map<String, Object> params, HermanosDependientesModel model);
    void deleteById(Long id);
}
