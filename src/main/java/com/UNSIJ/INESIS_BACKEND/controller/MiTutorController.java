package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.MiTutor;
import com.UNSIJ.INESIS_BACKEND.service.MiTutorServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/miTutor")
public class MiTutorController {
    @Autowired
    private MiTutorServiceJPA miTutorServiceJPA; // aquí siempre es el service no la interfaz

    @GetMapping
    public List<MiTutor> list() {
        return miTutorServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            MiTutor miTutor = miTutorServiceJPA.findById(id);
            return ResponseEntity.ok(miTutor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    //AQUI SIEMPRE RECIBIR UN MAPA, ES LA FORMA DE RECIBIR UN JSON
    //NO RECIBIR UNA INSTANCIA DE LA CLASE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            MiTutor miTutor = miTutorServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(miTutor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            MiTutor miTutorUpdated = miTutorServiceJPA.update(miTutorServiceJPA.findById(id),params);
            return ResponseEntity.status(HttpStatus.CREATED).body(miTutorUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        try {
            miTutorServiceJPA.findById(id);
            miTutorServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
