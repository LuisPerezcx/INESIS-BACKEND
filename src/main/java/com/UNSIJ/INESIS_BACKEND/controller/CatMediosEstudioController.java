/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.controller;

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

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudio;
import com.UNSIJ.INESIS_BACKEND.service.CatMediosEstudioServiceJPA;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
@RestController
@RequestMapping("/cat_medios_estudio")
public class CatMediosEstudioController {
    @Autowired
    private CatMediosEstudioServiceJPA serviceCatMediosEstudioJPA;

    @GetMapping
    public List<CatMediosEstudio> list() {
        return serviceCatMediosEstudioJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            CatMediosEstudio catMediosEstudio = serviceCatMediosEstudioJPA.findById(id);
            return ResponseEntity.ok(catMediosEstudio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            CatMediosEstudio createCatMediosEstudio = serviceCatMediosEstudioJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(createCatMediosEstudio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            CatMediosEstudio updated = serviceCatMediosEstudioJPA.update(serviceCatMediosEstudioJPA.findById(id), params);
            return ResponseEntity.status(HttpStatus.CREATED).body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch
        (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            serviceCatMediosEstudioJPA.deleteById(id);
            serviceCatMediosEstudioJPA.findById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
