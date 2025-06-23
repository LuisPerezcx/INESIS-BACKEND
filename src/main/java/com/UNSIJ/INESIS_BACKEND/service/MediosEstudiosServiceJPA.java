package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudio;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudio;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
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
    private CatMediosEstudioServiceJPA catMediosEstudioServiceJPA;

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
    public MediosEstudio create(Long idCatMedios, MiFamilia miFamilia) throws Exception {
        MediosEstudio mediosEstudio = new MediosEstudio();
        try {
            this.build(idCatMedios, mediosEstudio, miFamilia);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el tramite");
        }
        return this.save(mediosEstudio);
    }

    @Override
    public MediosEstudio build(Long idCatMedios, MediosEstudio model, MiFamilia miFamilia) {
        if (idCatMedios == null) {
            throw new IllegalArgumentException("El campo 'id_cat_medios_estudios' es obligatorio.");
        }
        CatMediosEstudio categoria = catMediosEstudioServiceJPA.findById(idCatMedios);
        model.setCatMediosEstudio(categoria);
        model.setMiFamilia(miFamilia);

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
