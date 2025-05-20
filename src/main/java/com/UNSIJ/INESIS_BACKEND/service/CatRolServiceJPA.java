package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.CatRolModel;
import com.UNSIJ.INESIS_BACKEND.repository.CatRolRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatRolService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class CatRolServiceJPA implements ICatRolService {

    @Autowired
    private CatRolRepository catRolRepository;

    @Override
    public List<CatRolModel> findAll() {
        return catRolRepository.findAll();
    }

    @Override
    public CatRolModel findById(Long id) {
        return catRolRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Rol no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public CatRolModel save(CatRolModel catRolModel) throws Exception {
        return catRolRepository.save(catRolModel);
    }

    @Override
    public CatRolModel create(Map<String, Object> params) throws Exception {
        CatRolModel catRolModel = new CatRolModel();
        try {
            this.build(params, catRolModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el rol");
        }
        return this.save(catRolModel);
    }

    @Override
    public CatRolModel update(CatRolModel catRolModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catRolModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el rol");
        }
        return this.save(catRolModel);
    }

    @Override
    public CatRolModel build(Map<String, Object> params, CatRolModel catRolModel) {
        try {
            String nombre = JsonUtils.obtString(params, "nombreRol");
            if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("El campo nombre es obligatorio");
            catRolModel.setNombreRol(nombre);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el rol");
        }
        return catRolModel;
    }

    @Override
    public CatRolModel updateInstance(CatRolModel catRolInstance) throws Exception {
        CatRolModel catRolBD = this.findById(catRolInstance.getId());
        catRolBD.setNombreRol(catRolInstance.getNombreRol());
        return this.save(catRolBD);
    }

    @Override
    public void deleteById(Long id) {
        CatRolModel catRolModel = this.findById(id);
        if (catRolModel != null) {
            catRolRepository.deleteById(id);
        }
    }
}
