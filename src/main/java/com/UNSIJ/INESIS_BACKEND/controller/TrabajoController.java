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

import com.UNSIJ.INESIS_BACKEND.model.Trabajo;
import com.UNSIJ.INESIS_BACKEND.service.TrabajoServiceJPA;

@RestController
@RequestMapping("/trabajo")
public class TrabajoController {
    @Autowired
    private TrabajoServiceJPA trabajoServiceJPA; // aquí siempre es el service no la interfaz

    @GetMapping
    public List<Trabajo> list() {
        return trabajoServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            Trabajo trabajo = trabajoServiceJPA.findById(id);
            return ResponseEntity.ok(trabajo);
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
            Trabajo trabajo = trabajoServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(trabajo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            Trabajo trabajoUpdated = trabajoServiceJPA.update(trabajoServiceJPA.findById(id),params);
            return ResponseEntity.status(HttpStatus.CREATED).body(trabajoUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        try {
            trabajoServiceJPA.findById(id); // PARA TIRAR LA EXEPCION SI NO SE ENCUENTRA EL REGISTRO
            trabajoServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
