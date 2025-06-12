package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatRol;
import com.UNSIJ.INESIS_BACKEND.repository.CatRolRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatRolService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CatRolServiceJPA implements ICatRolService {

    @Autowired
    private CatRolRepository catRolRepository;

    @Override
    public List<CatRol> findAll() {
        return catRolRepository.findAll();
    }

    @Override
    public CatRol findById(Long id) {
        return catRolRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Rol no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public CatRol save(CatRol catRolModel) throws Exception {
        return catRolRepository.save(catRolModel);
    }

    @Override
    public CatRol create(Map<String, Object> params) throws Exception {
        CatRol catRolModel = new CatRol();
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
    public CatRol update(CatRol catRolModel, Map<String, Object> params) throws Exception {
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
    public CatRol build(Map<String, Object> params, CatRol catRolModel) {
        try {
            String nombre = JsonUtils.obtString(params, "nombreRol");
            if (nombre == null || nombre.isEmpty())
                throw new IllegalArgumentException("El campo nombre es obligatorio");
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
    public CatRol updateInstance(CatRol catRolInstance) throws Exception {
        CatRol catRolBD = this.findById(catRolInstance.getId());
        catRolBD.setNombreRol(catRolInstance.getNombreRol());
        return this.save(catRolBD);
    }

    @Override
    public void deleteById(Long id) {
        CatRol catRolModel = this.findById(id);
        if (catRolModel != null) {
            catRolRepository.deleteById(id);
        }
    }
}
