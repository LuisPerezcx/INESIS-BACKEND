/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ServiciosViviendaModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliarModel;
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
 *
 * @author 24mda
 */
@Service
public class ServiciosViviendaServiceJPA implements IServiciosViviendaService {

    @Autowired
    private ServiciosViviendaRepository repository;

    @Autowired
    private ViviendaFamiliarRepository viviendaFamiliarRepository;

    @Override
    public List<ServiciosViviendaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public ServiciosViviendaModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio vivienda no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public ServiciosViviendaModel save(ServiciosViviendaModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public ServiciosViviendaModel create(Map<String, Object> params) throws Exception {
        ServiciosViviendaModel model = new ServiciosViviendaModel();
        return this.save(this.build(params, model));
    }

    @Override
    public ServiciosViviendaModel update(ServiciosViviendaModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public ServiciosViviendaModel build(Map<String, Object> params, ServiciosViviendaModel model) {
        Long viviendaFamiliarId = JsonUtils.obtLong(params, "id_vivienda_familiar");
        if (viviendaFamiliarId == null) {
            throw new IllegalArgumentException("El campo 'id_vivienda_familiar' es obligatorio.");
        }

        ViviendaFamiliarModel vivienda = viviendaFamiliarRepository.findById(viviendaFamiliarId)
                .orElseThrow(() -> new IllegalArgumentException("Vivienda familiar no encontrada con ID: " + viviendaFamiliarId));

        model.setViviendaFamiliar(vivienda);
        return model;
    }

    @Override
    public ServiciosViviendaModel updateInstance(ServiciosViviendaModel instance) throws Exception {
        ServiciosViviendaModel dbModel = this.findById(instance.getId());
        dbModel.setViviendaFamiliar(instance.getViviendaFamiliar());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        ServiciosViviendaModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
