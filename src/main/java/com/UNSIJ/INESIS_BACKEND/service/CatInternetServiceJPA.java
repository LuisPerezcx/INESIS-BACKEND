/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatInternetModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatInternetRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatInternetService;
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
public class CatInternetServiceJPA implements ICatInternetService {

    @Autowired
    private CatInternetRepository repository;

    @Override
    public List<CatInternetModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CatInternetModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Internet no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatInternetModel save(CatInternetModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatInternetModel create(Map<String, Object> params) throws Exception {
        CatInternetModel model = new CatInternetModel();
        try {
            model = this.build(params, model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el registro de internet");
        }
        return this.save(model);
    }

    @Override
    public CatInternetModel update(CatInternetModel model, Map<String, Object> params) throws Exception {
        try {
            model = this.build(params, model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el registro de internet");
        }
        return this.save(model);
    }

    @Override
    public CatInternetModel build(Map<String, Object> params, CatInternetModel model) {
        try {
            String nombreInternet = JsonUtils.obtString(params, "nombreInternet");
            if (nombreInternet == null || nombreInternet.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo 'nombreInternet' es obligatorio");
            }
            model.setNombreInternet(nombreInternet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el registro de internet");
        }
        return model;
    }

    @Override
    public CatInternetModel updateInstance(CatInternetModel instance) throws Exception {
        CatInternetModel dbModel = this.findById(instance.getId());
        dbModel.setNombreInternet(instance.getNombreInternet());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatInternetModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }

}
