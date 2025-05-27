/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogarModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatBienesHogarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatBienesHogarService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author 24mda
 */
import java.util.List;
import java.util.Map;

@Service
public class CatBienesHogarServiceJPA implements ICatBienesHogarService {

    @Autowired
    private CatBienesHogarRepository repository;

    @Override
    public List<CatBienesHogarModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CatBienesHogarModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bien del hogar no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatBienesHogarModel save(CatBienesHogarModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatBienesHogarModel create(Map<String, Object> params) throws Exception {
        CatBienesHogarModel model = new CatBienesHogarModel();
        try {
            model = this.build(params, model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el bien del hogar");
        }
        return this.save(model);
    }

    @Override
    public CatBienesHogarModel update(CatBienesHogarModel model, Map<String, Object> params) throws Exception {
        try {
            model = this.build(params, model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el bien del hogar");
        }
        return this.save(model);
    }

    @Override
    public CatBienesHogarModel build(Map<String, Object> params, CatBienesHogarModel model) {
        try {
            String nombre = JsonUtils.obtString(params, "nombreBien");
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo 'nombreBien' es obligatorio");
            }
            model.setNombreBien(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al procesar los datos del bien del hogar");
        }
        return model;
    }

    @Override
    public CatBienesHogarModel updateInstance(CatBienesHogarModel instance) throws Exception {
        CatBienesHogarModel dbModel = this.findById(instance.getId());
        dbModel.setNombreBien(instance.getNombreBien());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatBienesHogarModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
