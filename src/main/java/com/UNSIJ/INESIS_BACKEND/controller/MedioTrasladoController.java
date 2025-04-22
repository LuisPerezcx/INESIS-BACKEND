package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.MedioTraslado;
import com.UNSIJ.INESIS_BACKEND.repository.MediosTrasladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medio_traslado")
public class MedioTrasladoController {
     @Autowired
    private MediosTrasladoRepository repository;

    @GetMapping("/usuario/{idMisDatos}")
    public List<MedioTraslado> getByUsuario(@PathVariable Long idMisDatos) {
        return repository.findByMisDatosId(idMisDatos);
    }

    @PostMapping
    public List<MedioTraslado> saveAll(@RequestBody List<MedioTraslado> medios) {
        return repository.saveAll(medios);
    }
}
