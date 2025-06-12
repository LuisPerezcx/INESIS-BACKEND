package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Ocupacion;
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
    public List<Ocupacion> findAll() {
        return ocupacionRepository.findAll();
    }

    @Override
    public Ocupacion findById(Long id) {
        return ocupacionRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ocupacion no encontrado con el ID: " + id));
    }

    @Override
    public Ocupacion save(Ocupacion ocupacion) throws Exception {
        return null;
    }

    @Override
    public Ocupacion create(Map<String, Object> params) throws Exception {
        return null;
    }

    @Override
    public Ocupacion update(Ocupacion ocupacion, Map<String, Object> params) throws Exception {
        return null;
    }

    @Override
    public Ocupacion build(Map<String, Object> params, Ocupacion ocupacion) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Ocupacion updateInstance(Ocupacion ocupacion) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
