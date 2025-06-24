package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.CatRegion;
import com.UNSIJ.INESIS_BACKEND.repository.CatRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regiones")
public class CatRegionController {
    @Autowired
    private CatRegionRepository catRegionRepository;

    // Obtener todas las regiones
    @GetMapping
    public List<CatRegion> getAllRegions() {
        return catRegionRepository.findAll();
    }
}
