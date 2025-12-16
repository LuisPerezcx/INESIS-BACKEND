package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.GastosIngresosFamiliares;
import com.UNSIJ.INESIS_BACKEND.service.GastosIngresosService;

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
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            GastosIngresosFamiliares existing = gastosService.findById(id);
            GastosIngresosFamiliares updated = gastosService.update(existing, params); //TODO: REVISAR
            return ResponseEntity.status(HttpStatus.CREATED).body(updated);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }


}
