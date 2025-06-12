/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.BienesHogar;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogar;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
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
    public List<BienesHogar> findAll() {
        return repository.findAll();
    }

    @Override
    public BienesHogar findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bien del hogar no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public BienesHogar save(BienesHogar model) throws Exception {
        return repository.save(model);
    }

    @Override
    public BienesHogar create(Map<String, Object> params) throws Exception {
        BienesHogar model = new BienesHogar();
        return this.save(this.build(params, model));
    }

    @Override
    public BienesHogar update(BienesHogar model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public BienesHogar build(Map<String, Object> params, BienesHogar model) {
        Long idMiFamilia = JsonUtils.obtLong(params, "id_mi_familia");
        Long idCatBien = JsonUtils.obtLong(params, "id_cat_bienes_hogar");

        if (idMiFamilia == null || idCatBien == null) {
            throw new IllegalArgumentException("Los campos 'id_mi_familia' y 'id_cat_bienes_hogar' son obligatorios.");
        }

        MiFamilia familia = miFamiliaRepository.findById(idMiFamilia)
                .orElseThrow(() -> new IllegalArgumentException("Mi familia no encontrada con ID: " + idMiFamilia));

        CatBienesHogar categoria = catRepository.findById(idCatBien)
                .orElseThrow(() -> new IllegalArgumentException("Categoría de bien no encontrada con ID: " + idCatBien));

        model.setMiFamilia(familia);
        model.setCatBienHogar(categoria);

        return model;
    }

    @Override
    public BienesHogar updateInstance(BienesHogar instance) throws Exception {
        BienesHogar dbModel = this.findById(instance.getId());
        dbModel.setCatBienHogar(instance.getCatBienHogar());
        dbModel.setMiFamilia(instance.getMiFamilia());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        BienesHogar model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
