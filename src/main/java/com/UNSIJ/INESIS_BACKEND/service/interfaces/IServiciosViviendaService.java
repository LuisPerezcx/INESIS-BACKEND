/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ServiciosVivienda;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliar;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface IServiciosViviendaService {

    List<ServiciosVivienda> findAll();

    ServiciosVivienda findById(Long id);

    ServiciosVivienda save(ServiciosVivienda model) throws Exception;

    ServiciosVivienda create(Long idServicio, ViviendaFamiliar viviendaFamiliar) throws Exception;

    ServiciosVivienda build(Long idServicio, ServiciosVivienda model, ViviendaFamiliar viviendaFamiliar);

    ServiciosVivienda updateInstance(ServiciosVivienda instance) throws Exception;

    void deleteById(Long id);
}
