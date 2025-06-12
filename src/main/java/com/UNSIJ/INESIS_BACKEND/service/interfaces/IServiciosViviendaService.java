/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ServiciosVivienda;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface IServiciosViviendaService {

    List<ServiciosVivienda> findAll();

    ServiciosVivienda findById(Long id);

    ServiciosVivienda save(ServiciosVivienda model) throws Exception;

    ServiciosVivienda create(Map<String, Object> params) throws Exception;

    ServiciosVivienda update(ServiciosVivienda model, Map<String, Object> params) throws Exception;

    ServiciosVivienda build(Map<String, Object> params, ServiciosVivienda model);

    ServiciosVivienda updateInstance(ServiciosVivienda instance) throws Exception;

    void deleteById(Long id);
}
