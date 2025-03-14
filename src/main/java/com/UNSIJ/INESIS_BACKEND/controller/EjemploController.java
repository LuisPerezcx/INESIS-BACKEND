package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.service.EjemploServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ejemplos") // esta es la ruta para este controlador
public class EjemploController {
    @Autowired
    private EjemploServiceJPA ejemploServiceJPA; // aquí siempre es el service no la interfaz

    @GetMapping
    public List<Ejemplo> list() {
        return ejemploServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            Ejemplo ejemplo = ejemploServiceJPA.findById(id);
            return ResponseEntity.ok(ejemplo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    //AQUI SIEMPRE RECIBIR UN MAPA ES LA FORMA DE RECIBIR UN JSON
    //NUNCA RECIBIR UNA INSTANCIA DE LA CLASE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            Ejemplo ejemplo = ejemploServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(ejemplo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            Ejemplo ejemploUpdated = ejemploServiceJPA.update(ejemploServiceJPA.findById(id),params);
            return ResponseEntity.status(HttpStatus.CREATED).body(ejemploUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
