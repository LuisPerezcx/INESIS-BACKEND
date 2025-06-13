/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatInternet;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatInternetRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatInternetService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CatInternetServiceJPA implements ICatInternetService {

    @Autowired
    private CatInternetRepository repository;

    @Override
    public List<CatInternet> findAll() {
        return repository.findAll();
    }

    @Override
    public CatInternet findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Internet no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatInternet save(CatInternet model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatInternet create(Map<String, Object> params) throws Exception {
        CatInternet model = new CatInternet();
        try {
            model = this.build(params, model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el registro de internet");
        }
        return this.save(model);
    }

    @Override
    public CatInternet update(CatInternet model, Map<String, Object> params) throws Exception {
        try {
            model = this.build(params, model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el registro de internet");
        }
        return this.save(model);
    }

    @Override
    public CatInternet build(Map<String, Object> params, CatInternet model) {
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
    public CatInternet updateInstance(CatInternet instance) throws Exception {
        CatInternet dbModel = this.findById(instance.getId());
        dbModel.setNombreInternet(instance.getNombreInternet());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatInternet model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }

}
