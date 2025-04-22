package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.CatMediosTransporte;
import com.UNSIJ.INESIS_BACKEND.repository.CatMediosTransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat_medios_transporte")
public class CatMediosTransporteController {

    @Autowired
    private CatMediosTransporteRepository repository;

    @GetMapping
    public List<CatMediosTransporte> getAll() {
        return repository.findAll();
    }
}