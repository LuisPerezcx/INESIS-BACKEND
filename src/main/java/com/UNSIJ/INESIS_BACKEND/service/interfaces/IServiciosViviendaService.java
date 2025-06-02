/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ServiciosViviendaModel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 24mda
 */
public interface IServiciosViviendaService {

    List<ServiciosViviendaModel> findAll();

    ServiciosViviendaModel findById(Long id);

    ServiciosViviendaModel save(ServiciosViviendaModel model) throws Exception;

    ServiciosViviendaModel create(Map<String, Object> params) throws Exception;

    ServiciosViviendaModel update(ServiciosViviendaModel model, Map<String, Object> params) throws Exception;

    ServiciosViviendaModel build(Map<String, Object> params, ServiciosViviendaModel model);

    ServiciosViviendaModel updateInstance(ServiciosViviendaModel instance) throws Exception;

    void deleteById(Long id);
}
