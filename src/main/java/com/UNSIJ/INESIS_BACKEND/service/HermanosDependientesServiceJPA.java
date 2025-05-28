/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.HermanosDependientesModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamiliaModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.HermanosDependientesRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MiFamiliaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IHermanosDependientesService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Alumnos
 */
@Service
public class HermanosDependientesServiceJPA implements IHermanosDependientesService{
@Autowired
    private HermanosDependientesRepository repository;

    @Autowired
    private MiFamiliaRepository miFamiliaRepository;

    @Override
    public List<HermanosDependientesModel> findAll() {
        return repository.findAll();
    }

    @Override
    public HermanosDependientesModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HermanosDependientes no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public HermanosDependientesModel save(HermanosDependientesModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public HermanosDependientesModel create(Map<String, Object> params) throws Exception {
        HermanosDependientesModel model = new HermanosDependientesModel();
        return this.save(this.build(params, model));
    }

    @Override
    public HermanosDependientesModel update(HermanosDependientesModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public HermanosDependientesModel build(Map<String, Object> params, HermanosDependientesModel model) {
        model.setNombreHermano(JsonUtils.obtString(params, "nombre_hermano"));
        model.setEdad(JsonUtils.obtInteger(params, "edad"));
        model.setNombreEscuela(JsonUtils.obtString(params, "nombre_escuela"));
        model.setGrado(JsonUtils.obtString(params, "grado"));
        model.setNombreArchivo(JsonUtils.obtString(params, "nombre_archivo"));

        Long idMiFamilia = JsonUtils.obtLong(params, "id_mi_familia");
        if (idMiFamilia != null) {
            MiFamiliaModel miFamilia = miFamiliaRepository.findById(idMiFamilia)
                .orElseThrow(() -> new IllegalArgumentException("MiFamilia no encontrada con ID: " + idMiFamilia));
            model.setMiFamilia(miFamilia);
        }

        return model;
    }

    @Override
    public void deleteById(Long id) {
        HermanosDependientesModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
