/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatEscolaridadModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatEscolaridadRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatEscolaridadService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 24mda
 */
@Service
public class CatEscolaridadServiceJPA implements ICatEscolaridadService {

    @Autowired
    private CatEscolaridadRepository repository;

    @Override
    public List<CatEscolaridadModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CatEscolaridadModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Escolaridad no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public CatEscolaridadModel save(CatEscolaridadModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatEscolaridadModel create(Map<String, Object> params) throws Exception {
        CatEscolaridadModel model = new CatEscolaridadModel();
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatEscolaridadModel update(CatEscolaridadModel model, Map<String, Object> params) throws Exception {
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatEscolaridadModel build(Map<String, Object> params, CatEscolaridadModel model) {
        try {
            String nombreEscolaridad = JsonUtils.obtString(params, "nombreEscolaridad");
            if (nombreEscolaridad == null || nombreEscolaridad.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo 'nombreEscolaridad' es obligatorio");
            }
            model.setNombreEscolaridad(nombreEscolaridad);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar los datos de escolaridad");
        }
        return model;
    }

    @Override
    public CatEscolaridadModel updateInstance(CatEscolaridadModel instance) throws Exception {
        CatEscolaridadModel dbModel = this.findById(instance.getId());
        dbModel.setNombreEscolaridad(instance.getNombreEscolaridad());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatEscolaridadModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
