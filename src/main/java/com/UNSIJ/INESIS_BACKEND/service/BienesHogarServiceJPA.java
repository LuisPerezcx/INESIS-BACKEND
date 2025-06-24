/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.BienesHogar;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogar;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudio;
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
    private CatBienesHogarServiceJPA catBienesHogarServiceJPA;

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
    @Transactional
    public BienesHogar create(Long idBienes, MiFamilia miFamilia) throws Exception {
        BienesHogar bienesHogar = new BienesHogar();
        try {
            this.build(idBienes, bienesHogar, miFamilia);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el tramite");
        }
        return this.save(bienesHogar);
    }


    @Override
    @Transactional
    public BienesHogar build(Long idBienes, BienesHogar model, MiFamilia miFamilia) {
        if (idBienes == null) {
            throw new IllegalArgumentException("El campo 'id_cat_bienes_hogar' es obligatorio.");
        }
        CatBienesHogar bien = catBienesHogarServiceJPA.findById(idBienes);
        model.setCatBienHogar(bien);
        model.setMiFamilia(miFamilia);

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
