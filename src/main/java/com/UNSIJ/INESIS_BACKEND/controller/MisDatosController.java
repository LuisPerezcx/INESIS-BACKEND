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

import com.UNSIJ.INESIS_BACKEND.dto.MisDatosDTO;
import com.UNSIJ.INESIS_BACKEND.model.MisDatos;
import com.UNSIJ.INESIS_BACKEND.service.MisDatosServiceJPA;

@RestController
@RequestMapping("/misDatos")
public class MisDatosController {
    @Autowired
    private MisDatosServiceJPA misDatosServiceJPA; // Aquí siempre es el service no la interfaz

    @GetMapping
    public List<MisDatos> list() {
        return misDatosServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            MisDatos misDatos = misDatosServiceJPA.findById(id);
            return ResponseEntity.ok(misDatos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<MisDatos> getMisDatosByAlumno(@PathVariable Long idAlumno) {
        try {
            MisDatos datos = misDatosServiceJPA.findByAlumno(idAlumno);
            return ResponseEntity.ok(datos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //AQUI SIEMPRE RECIBIR UN MAPA, ES LA FORMA DE RECIBIR UN JSON
    //NO RECIBIR UNA INSTANCIA DE LA CLASE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> params) {
        try {
            MisDatos misDatos = misDatosServiceJPA.create(params);
            MisDatosDTO dto = convertirAMisDatosDTO(misDatos);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
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
            MisDatos misDatosUpdate = misDatosServiceJPA.update(misDatosServiceJPA.findById(id), params);
            return ResponseEntity.status(HttpStatus.CREATED).body(misDatosUpdate);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            misDatosServiceJPA.findById(id); // PARA TIRAR LA EXEPCION SI NO SE ENCUENTRA EL REGISTRO
            misDatosServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    private MisDatosDTO convertirAMisDatosDTO(MisDatos misDatos) {
        MisDatosDTO dto = new MisDatosDTO();

        dto.setId(misDatos.getId());
        //dto.setNombreCompleto(misDatos.getNombreCompleto());
        dto.setIdioma(misDatos.getIdioma());
        dto.setRecursosSuficientes(misDatos.getRecursosSuficientes());
        dto.setFamiliarComunero(misDatos.getFamiliarComunero());
        dto.setUtilizaCelular(misDatos.getUtilizaCelular());
        dto.setTieneComputadora(misDatos.getTieneComputadora());

        // Extraer los nombres de entidades relacionadas
        //dto.setCarrera(misDatos.getCarrera() != null ? misDatos.getCarrera().getNombreCarrera() : null);
        //dto.setSemestre(misDatos.getSemestre() != null ? misDatos.getSemestre().getNombreSemestre() : null);
        dto.setSexo(misDatos.getSexo() != null ? misDatos.getSexo().getNombreSexo() : null);
        dto.setEstadoCivil(misDatos.getEstadoCivil() != null ? misDatos.getEstadoCivil().getNombreEstadoCivil() : null);

        // Mapear solo los nombres de medios de traslado
        if (misDatos.getMediosTraslado() != null) {
            dto.setMediosTraslado(
                    misDatos.getMediosTraslado().stream()
                            .map(mt -> mt.getCatMediosTransporte().getNombreMedio())
                            .toList()
            );
        }

        return dto;
    }


}
