package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.service.AlumnoServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> cambiarPassword(
            @PathVariable Long id,
            @RequestBody Map<String, Object> datos) {
        try {
            String rawPassword = datos.get("rawPassword").toString();
            alumnoServiceJPA.cambiarPasswordAlumno(id, rawPassword);
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la contraseña: " + e.getMessage());
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

    @PatchMapping("/completarEstudio/{id}")
    public ResponseEntity<?> estudioCompleto(@PathVariable Long id) {
        try {
            Alumno alumno = alumnoServiceJPA.findById(id);
            // Actualizar el campo estudioCompleto a true
            alumno.setEstudioCompleto(true);
            // Guardar cambios usando el servicio
            alumnoServiceJPA.save(alumno);
            return ResponseEntity.ok("Estudio completo actualizado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el estado de estudio completo: " + e.getMessage());
        }
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
            alumno.setEstadoRevision(estado);

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

    @PostMapping("/importar")
    public ResponseEntity<?> importarDesdeExcel(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Por favor seleccione un archivo");
            }

            if (!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls")) {
                return ResponseEntity.badRequest().body("Por favor suba un archivo Excel válido");
            }

            // Procesar el archivo y crear alumnos
            List<Alumno> alumnosImportados = alumnoServiceJPA.importarDesdeExcel(file);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", "Alumnos importados correctamente",
                    "cantidad", alumnosImportados.size(),
                    "alumnos", alumnosImportados));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al importar alumnos: " + e.getMessage());
        }
    }

}
