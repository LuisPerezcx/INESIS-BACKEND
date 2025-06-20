package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.service.AlumnoServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoServiceJPA alumnoServiceJPA;

    @GetMapping
    public List<Alumno> list() {
        return alumnoServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            Alumno alumno = alumnoServiceJPA.findById(id);
            return ResponseEntity.ok(alumno);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            Alumno alumno = alumnoServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
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
            Alumno alumnoUpdated = alumnoServiceJPA.update(alumnoServiceJPA.findById(id), params);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            alumnoServiceJPA.findById(id); // Lanza la excepción si no se encuentra el registro
            alumnoServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/checkExists")
    public ResponseEntity<Map<String, Boolean>> checkIfExists(
            @RequestParam String curp,
            @RequestParam String matricula,
            @RequestParam String correo) {

        boolean exists = alumnoServiceJPA.checkIfExists(curp, matricula, correo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/revision")
    public ResponseEntity<?> actualizarRevisionAlumno(
            @PathVariable Long id,
            @RequestBody Map<String, Object> datos) {

        try {
            String observaciones = datos.get("observaciones").toString();
            Boolean estado = Boolean.parseBoolean(datos.get("estado").toString());

            Alumno alumno = alumnoServiceJPA.findById(id);
            // Actualizar campos
            alumno.setObservaciones(observaciones);
            alumno.setEstado(estado);

            // Guardar cambios usando el servicio
            alumnoServiceJPA.save(alumno);

            return ResponseEntity.ok("Alumno actualizado correctamente");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en los datos enviados: " + e.getMessage());
        }
    }

}
