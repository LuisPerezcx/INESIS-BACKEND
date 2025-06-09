package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.dto.DatosFamiliaresDTO;
import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.model.GastosIngresosFamiliares;
import com.UNSIJ.INESIS_BACKEND.service.GastosIngresosService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gastosFamiliares")
public class GastosFamiliaresController {

    @Autowired
    private GastosIngresosService gastosService;

    // Recibe todo en JSON, no multipart/form-data

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            GastosIngresosFamiliares creado = gastosService.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

}
