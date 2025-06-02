/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.BienesHogarModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogarModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamiliaModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.BienesHogarRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatBienesHogarRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MiFamiliaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IBienesHogarService;
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
public class BienesHogarServiceJPA implements IBienesHogarService {

    @Autowired
    private BienesHogarRepository repository;

    @Autowired
    private MiFamiliaRepository miFamiliaRepository;

    @Autowired
    private CatBienesHogarRepository catRepository;

    @Override
    public List<BienesHogarModel> findAll() {
        return repository.findAll();
    }

    @Override
    public BienesHogarModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bien del hogar no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public BienesHogarModel save(BienesHogarModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public BienesHogarModel create(Map<String, Object> params) throws Exception {
        BienesHogarModel model = new BienesHogarModel();
        return this.save(this.build(params, model));
    }

    @Override
    public BienesHogarModel update(BienesHogarModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public BienesHogarModel build(Map<String, Object> params, BienesHogarModel model) {
        Long idMiFamilia = JsonUtils.obtLong(params, "id_mi_familia");
        Long idCatBien = JsonUtils.obtLong(params, "id_cat_bienes_hogar");

        if (idMiFamilia == null || idCatBien == null) {
            throw new IllegalArgumentException("Los campos 'id_mi_familia' y 'id_cat_bienes_hogar' son obligatorios.");
        }

        MiFamiliaModel familia = miFamiliaRepository.findById(idMiFamilia)
                .orElseThrow(() -> new IllegalArgumentException("Mi familia no encontrada con ID: " + idMiFamilia));

        CatBienesHogarModel categoria = catRepository.findById(idCatBien)
                .orElseThrow(() -> new IllegalArgumentException("Categoría de bien no encontrada con ID: " + idCatBien));

        model.setMiFamilia(familia);
        model.setCatBienHogar(categoria);

        return model;
    }

    @Override
    public BienesHogarModel updateInstance(BienesHogarModel instance) throws Exception {
        BienesHogarModel dbModel = this.findById(instance.getId());
        dbModel.setCatBienHogar(instance.getCatBienHogar());
        dbModel.setMiFamilia(instance.getMiFamilia());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        BienesHogarModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
