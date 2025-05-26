package com.UNSIJ.INESIS_BACKEND.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UNSIJ.INESIS_BACKEND.model.CatTipoTransporte;
import com.UNSIJ.INESIS_BACKEND.service.CatTipoTransporteServiceJPA;

@RestController
@RequestMapping("/catTipoTransporte")
public class CatiTipoTransporteController {
    @Autowired
    private CatTipoTransporteServiceJPA catTipoTransporteServiceJPA; // aquí siempre es el service no la interfaz

    @GetMapping
    public List<CatTipoTransporte> list() {
        return catTipoTransporteServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            CatTipoTransporte catTipoTransporte = catTipoTransporteServiceJPA.findById(id);
            return ResponseEntity.ok(catTipoTransporte);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            CatTipoTransporte catTipoTransporte = catTipoTransporteServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(catTipoTransporte);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            CatTipoTransporte catTipoTransporteUpdate = catTipoTransporteServiceJPA.update(catTipoTransporteServiceJPA.findById(id),params);
            return ResponseEntity.status(HttpStatus.CREATED).body(catTipoTransporteUpdate);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        try {
            catTipoTransporteServiceJPA.findById(id); // PARA TIRAR LA EXEPCION SI NO SE ENCUENTRA EL REGISTRO
            catTipoTransporteServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
