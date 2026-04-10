/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatServiciosVivienda;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ServiciosVivienda;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliar;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.ServiciosViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.ViviendaFamiliarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IServiciosViviendaService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
@Service
public class ServiciosViviendaServiceJPA implements IServiciosViviendaService {

    @Autowired
    private ServiciosViviendaRepository repository;

    @Autowired CatServiciosOtroServiceJPA catServiciosOtroService;

    @Override
    public List<ServiciosVivienda> findAll() {
        return repository.findAll();
    }

    @Override
    public ServiciosVivienda findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio vivienda no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public ServiciosVivienda save(ServiciosVivienda model) throws Exception {
        return repository.save(model);
    }

    @Override
    @Transactional
    public ServiciosVivienda create(Long idServicio, ViviendaFamiliar viviendaFamiliar) throws Exception {
        ServiciosVivienda model = new ServiciosVivienda();
        try {
            this.build(idServicio, model, viviendaFamiliar);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el tramite");
        }
        return this.save(model);
    }

    @Override
    @Transactional
    public ServiciosVivienda build(Long idServicio, ServiciosVivienda model, ViviendaFamiliar viviendaFamiliar) {
        if (idServicio == null) {
            throw new IllegalArgumentException("El campo 'id_servicio' es obligatorio.");
        }
        CatServiciosVivienda catServiciosVivienda = catServiciosOtroService.findById(idServicio);
        model.setCatServiciosVivienda(catServiciosVivienda);
        model.setViviendaFamiliar(viviendaFamiliar);

        return model;
    }

    @Override
    public ServiciosVivienda updateInstance(ServiciosVivienda instance) throws Exception {
        ServiciosVivienda dbModel = this.findById(instance.getId());
        dbModel.setViviendaFamiliar(instance.getViviendaFamiliar());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        ServiciosVivienda model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
