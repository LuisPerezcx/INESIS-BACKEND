package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.CatDistrito;
import com.UNSIJ.INESIS_BACKEND.repository.CatDistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/distritos")
public class CatDistritoController {

    @Autowired
    private CatDistritoRepository catDistritoRepository;

    // Obtener todos los distritos
    @GetMapping
    public List<CatDistrito> getAllDistricts() {
        return catDistritoRepository.findAll();
    }

    // Opcional: Obtener distritos filtrando por región (id_cat_region)
    @GetMapping("/region/{regionId}")
    public List<CatDistrito> getDistrictsByRegion(@PathVariable Long regionId) {
        return catDistritoRepository.findByRegion_Id(regionId);
    }
}
