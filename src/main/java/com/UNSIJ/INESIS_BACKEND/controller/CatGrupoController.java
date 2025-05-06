package com.UNSIJ.INESIS_BACKEND.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.UNSIJ.INESIS_BACKEND.model.CatGrupoModel;
import com.UNSIJ.INESIS_BACKEND.service.CatGrupoServiceJPA;

@RestController
@RequestMapping("/grupo")
public class CatGrupoController {

    @Autowired
    private CatGrupoServiceJPA grupoServiceJPA;

    @GetMapping
    public List<CatGrupoModel> list() {
        return grupoServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            CatGrupoModel grupo = grupoServiceJPA.findById(id);
            return ResponseEntity.ok(grupo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            CatGrupoModel grupo = grupoServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            CatGrupoModel grupoUpdate = grupoServiceJPA.update(grupoServiceJPA.findById(id), params);
            return ResponseEntity.status(HttpStatus.CREATED).body(grupoUpdate);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            grupoServiceJPA.findById(id); // Lanza excepción si no encuentra el registro
            grupoServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    //Endpoint para obtener el grupo basado en carrera y semestre seleccionados
    @GetMapping("/carrera/{idCarrera}/semestre/{idSemestre}")
    public ResponseEntity<?> obtenerGrupo(@PathVariable Long idCarrera, @PathVariable Long idSemestre) {
        try {
            String nombreGrupo = grupoServiceJPA.obtenerNombreGrupo(idCarrera, idSemestre);
            return ResponseEntity.ok(nombreGrupo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el grupo");
        }
    }    
}
