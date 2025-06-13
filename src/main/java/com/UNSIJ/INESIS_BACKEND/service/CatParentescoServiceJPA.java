package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatParentesco;
import com.UNSIJ.INESIS_BACKEND.repository.CatParentescoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatParentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatParentescoServiceJPA implements ICatParentesco {
    @Autowired
    private CatParentescoRepository catParentescoRepository;

    @Override
    public List<CatParentesco> findAll() {
        return catParentescoRepository.findAll();
    }

    @Override
    public CatParentesco findById(Long id) {
        return catParentescoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }
}
