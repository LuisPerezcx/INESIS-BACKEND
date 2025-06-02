/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.PersonasDependientesModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamiliaModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.PersonasDependientesRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MiFamiliaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IPersonasDependientesService;
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
public class PersonasDependientesServiceJPA implements IPersonasDependientesService {

    @Autowired
    private PersonasDependientesRepository repository;

    @Autowired
    private MiFamiliaRepository miFamiliaRepository;

    @Override
    public List<PersonasDependientesModel> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonasDependientesModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HermanosDependientes no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public PersonasDependientesModel save(PersonasDependientesModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public PersonasDependientesModel create(Map<String, Object> params) throws Exception {
        PersonasDependientesModel model = new PersonasDependientesModel();
        return this.save(this.build(params, model));
    }

    @Override
    public PersonasDependientesModel update(PersonasDependientesModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public PersonasDependientesModel build(Map<String, Object> params, PersonasDependientesModel model) {
        model.setNombrePersona(JsonUtils.obtString(params, "nombre_persona"));
        model.setEdad(JsonUtils.obtInteger(params, "edad"));
        model.setParentesco(JsonUtils.obtString(params, "parentesco"));
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
        PersonasDependientesModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
