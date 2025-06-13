package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatTipoTrabajo;
import com.UNSIJ.INESIS_BACKEND.repository.CatTipoTrabajoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatTipoTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatTipoTrabajaoServiceJPA implements ICatTipoTrabajoService {
    @Autowired
    private CatTipoTrabajoRepository catTipoTrabajoRepository;

    @Override
    public List<CatTipoTrabajo> findAll() {
        return catTipoTrabajoRepository.findAll();
    }

    @Override
    public CatTipoTrabajo findById(Long id) {
        return catTipoTrabajoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("CatTipoTrabajo no encontrado con el ID: " + id));
    }
}
