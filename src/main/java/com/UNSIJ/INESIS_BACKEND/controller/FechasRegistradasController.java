package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradasModel;
import com.UNSIJ.INESIS_BACKEND.service.FechasRegistradasServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fechas-registradas")
public class FechasRegistradasController {

    @Autowired
    private FechasRegistradasServiceJPA fechasRegistradasServiceJPA;

    @GetMapping
    public List<FechasRegistradasModel> list() {
        return fechasRegistradasServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            FechasRegistradasModel fechasRegistradas = fechasRegistradasServiceJPA.findById(id);
            return ResponseEntity.ok(fechasRegistradas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            FechasRegistradasModel fechasRegistradas = fechasRegistradasServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(fechasRegistradas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            FechasRegistradasModel fechasRegistradasUpdated = fechasRegistradasServiceJPA
                    .update(fechasRegistradasServiceJPA.findById(id), params);
            return ResponseEntity.status(HttpStatus.CREATED).body(fechasRegistradasUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            fechasRegistradasServiceJPA.findById(id); // Para lanzar excepción si no se encuentra el registro
            fechasRegistradasServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/carrera/{idCarrera}")
public ResponseEntity<?> getByCarrera(@PathVariable Long idCarrera) {
    try {
        FechasRegistradasModel fecha = fechasRegistradasServiceJPA.findByCarreraId(idCarrera);
        return ResponseEntity.ok(fecha);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        e.printStackTrace(); 
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar la fecha por carrera.");
    }
}


}
