package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.model.OcupacionModel;
import com.UNSIJ.INESIS_BACKEND.repository.EjemploRepository;
import com.UNSIJ.INESIS_BACKEND.repository.OcupacionRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IOcupacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OcupacionServiceJPA implements IOcupacionService {
    @Autowired
    private OcupacionRepository ocupacionRepository;

    @Override
    public List<OcupacionModel> findAll() {
        return ocupacionRepository.findAll();
    }

    @Override
    public OcupacionModel findById(Long id) {
        return ocupacionRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ocupacion no encontrado con el ID: " + id));
    }

    @Override
    public OcupacionModel save(OcupacionModel ocupacionModel) throws Exception {
        return null;
    }

    @Override
    public OcupacionModel create(Map<String, Object> params) throws Exception {
        return null;
    }

    @Override
    public OcupacionModel update(OcupacionModel ocupacionModel, Map<String, Object> params) throws Exception {
        return null;
    }

    @Override
    public OcupacionModel build(Map<String, Object> params, OcupacionModel ocupacionModel) throws IllegalArgumentException {
        return null;
    }

    @Override
    public OcupacionModel updateInstance(OcupacionModel ocupacionModel) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
