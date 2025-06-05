package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudioModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudioModel;
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
    public List<MediosEstudioModel> findAll() {
        return repository.findAll();
    }

    @Override
    public MediosEstudioModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medio de estudio no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public MediosEstudioModel save(MediosEstudioModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public MediosEstudioModel create(Map<String, Object> params) throws Exception {
        MediosEstudioModel model = new MediosEstudioModel();
        return this.save(this.build(params, model));
    }

    @Override
    public MediosEstudioModel update(MediosEstudioModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public MediosEstudioModel build(Map<String, Object> params, MediosEstudioModel model) {
        Long idCategoria = JsonUtils.obtLong(params, "id_cat_medios_estudios");

        if (idCategoria == null) {
            throw new IllegalArgumentException("El campo 'id_cat_medios_estudios' es obligatorio.");
        }

        CatMediosEstudioModel categoria = catRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + idCategoria));

        model.setCatMediosEstudio(categoria);
        return model;
    }

    @Override
    public MediosEstudioModel updateInstance(MediosEstudioModel instance) throws Exception {
        MediosEstudioModel dbModel = this.findById(instance.getId());
        dbModel.setCatMediosEstudio(instance.getCatMediosEstudio());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        MediosEstudioModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
