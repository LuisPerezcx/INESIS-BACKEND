/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatServiciosOtro;
import com.UNSIJ.INESIS_BACKEND.service.CatServiciosOtroServiceJPA;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Alumnos
 */
@RestController
@RequestMapping("/cat_servicios_otro")
public class CatServiciosOtroController {
    @Autowired
    private CatServiciosOtroServiceJPA catServiciosOtroService;

    @GetMapping
    public List<CatServiciosOtro> list() {
        return catServiciosOtroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            CatServiciosOtro servicio = catServiciosOtroService.findById(id);
            return ResponseEntity.ok(servicio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
