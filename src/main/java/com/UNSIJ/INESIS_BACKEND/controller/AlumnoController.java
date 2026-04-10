package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.service.AlumnoServiceJPA;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
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

        boolean exists = alumnoServiceJPA.checkIfExists(curp, matricula);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/completarEstudio/{id}")
    public ResponseEntity<?> estudioCompleto(@PathVariable Long id) {
        try {
            Alumno alumno = alumnoServiceJPA.findById(id);
            //cambiar a pendiente si no tiene correcciones
            if(alumno.getEstadoRevision() !=2) alumno.setEstadoRevision(1);
            //cambiar de con correccion a corregido
            if(alumno.getEstadoRevision() == 2) alumno.setEstadoRevision(3);
            // Actualizar el campo estudioCompleto a true
            alumno.setEstudioCompleto(true);
            //actualizar fecha envio
            alumno.setFechaEnvio(new Date());
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
            /* 0.- sin revisar(no se ocupa), 1.-pendiente, 2.-con correcciones
            * 3.- corregido, 4.- finalizado  */
            alumnoServiceJPA.cambiarEstadoRevision(alumnoServiceJPA.findById(id), datos);
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

    @PatchMapping("/{id}/editarMatricula")
    public ResponseEntity<?> matriculaModificada(@PathVariable Long id, @RequestBody String matricula) {
        try {
            if(matricula == null || matricula.trim().isEmpty()){
                return ResponseEntity.badRequest().body("La nueva matrícula no puede estar vacía");
            }
            alumnoServiceJPA.actualizarMatricula(id, matricula);
            return ResponseEntity.ok("Alumno actualizado correctamente");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en los datos enviados: " + e.getMessage());
        }
    }
}
