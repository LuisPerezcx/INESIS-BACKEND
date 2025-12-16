package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.Revisor;
import com.UNSIJ.INESIS_BACKEND.service.AlumnoServiceJPA;
import com.UNSIJ.INESIS_BACKEND.service.RevisorServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/revisor")
public class RevisorController {

    @Autowired
    private RevisorServiceJPA revisorServiceJPA;

    @Autowired
    private AlumnoServiceJPA alumnoServiceJPA;

    @GetMapping
    public List<Revisor> list() {
        return revisorServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            Revisor revisor = revisorServiceJPA.findById(id);
            return ResponseEntity.ok(revisor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            Revisor revisor = revisorServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(revisor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            Revisor revisorUpdated = revisorServiceJPA.update(revisorServiceJPA.findById(id), params);
            return ResponseEntity.status(HttpStatus.CREATED).body(revisorUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            revisorServiceJPA.findById(id); // Verifica existencia
            revisorServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/checkExists")
    public ResponseEntity<Map<String, Boolean>> checkIfExists(@RequestParam String matricula) {

        boolean exists = revisorServiceJPA.checkIfExists(matricula);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/exportar")
    public ResponseEntity<?> exportarFinalizados(){
        try {
            String excel = alumnoServiceJPA.exportarFinalizados();
            return ResponseEntity.status(HttpStatus.OK).body(excel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
