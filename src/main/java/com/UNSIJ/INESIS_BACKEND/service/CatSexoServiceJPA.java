package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatSexoModel;
import com.UNSIJ.INESIS_BACKEND.repository.CatSexoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatSexoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CatSexoServiceJPA implements ICatSexoService {

    @Autowired
    private CatSexoRepository catSexoRepository;

    @Override
    public List<CatSexoModel> findAll() {
        return catSexoRepository.findAll();
    }

    @Override
    public CatSexoModel findById(Long id) {
        return catSexoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Sexo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public CatSexoModel save(CatSexoModel catSexoModel) throws Exception {
        return catSexoRepository.save(catSexoModel);
    }

    @Override
    public CatSexoModel create(Map<String, Object> params) throws Exception {
        CatSexoModel catSexoModel = new CatSexoModel();
        try {
            this.build(params, catSexoModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el sexo");
        }
        return this.save(catSexoModel);
    }

    @Override
    public CatSexoModel update(CatSexoModel catSexoModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catSexoModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el sexo");
        }
        return this.save(catSexoModel);
    }

    @Override
    public CatSexoModel build(Map<String, Object> params, CatSexoModel catSexoModel) {
        try {
            String nombre = JsonUtils.obtString(params, "nombreSexo");
            if (nombre == null || nombre.isEmpty())
                throw new IllegalArgumentException("El campo nombre es obligatorio");
            catSexoModel.setNombreSexo(nombre);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el sexo");
        }
        return catSexoModel;
    }

    @Override
    public CatSexoModel updateInstance(CatSexoModel catSexoInstance) throws Exception {
        CatSexoModel catSexoBD = this.findById(catSexoInstance.getId());
        catSexoBD.setNombreSexo(catSexoInstance.getNombreSexo());
        return this.save(catSexoBD);
    }

    @Override
    public void deleteById(Long id) {
        CatSexoModel catSexoModel = this.findById(id);
        if (catSexoModel != null) {
            catSexoRepository.deleteById(id);
        }
    }
}
