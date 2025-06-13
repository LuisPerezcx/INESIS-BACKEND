package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudio;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudio;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatMediosEstudioRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MediosEstudioRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMediosEstudiosService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MediosEstudiosServiceJPA implements IMediosEstudiosService {
    @Autowired
    private MediosEstudioRepository repository;

    @Autowired
    private CatMediosEstudioRepository catRepository;

    @Override
    public List<MediosEstudio> findAll() {
        return repository.findAll();
    }

    @Override
    public MediosEstudio findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medio de estudio no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public MediosEstudio save(MediosEstudio model) throws Exception {
        return repository.save(model);
    }

    @Override
    public MediosEstudio create(Map<String, Object> params) throws Exception {
        MediosEstudio model = new MediosEstudio();
        return this.save(this.build(params, model));
    }

    @Override
    public MediosEstudio update(MediosEstudio model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public MediosEstudio build(Map<String, Object> params, MediosEstudio model) {
        Long idCategoria = JsonUtils.obtLong(params, "id_cat_medios_estudios");

        if (idCategoria == null) {
            throw new IllegalArgumentException("El campo 'id_cat_medios_estudios' es obligatorio.");
        }

        CatMediosEstudio categoria = catRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + idCategoria));

        model.setCatMediosEstudio(categoria);
        return model;
    }

    @Override
    public MediosEstudio updateInstance(MediosEstudio instance) throws Exception {
        MediosEstudio dbModel = this.findById(instance.getId());
        dbModel.setCatMediosEstudio(instance.getCatMediosEstudio());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        MediosEstudio model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
