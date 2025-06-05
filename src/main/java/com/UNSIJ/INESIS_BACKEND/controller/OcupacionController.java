package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.model.OcupacionModel;
import com.UNSIJ.INESIS_BACKEND.service.EjemploServiceJPA;
import com.UNSIJ.INESIS_BACKEND.service.OcupacionServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ocupacion")
public class OcupacionController {
    @Autowired
    private OcupacionServiceJPA ocupacionServiceJPA; // aquí siempre es el service no la interfaz

    @GetMapping
    public List<OcupacionModel> list() {
        return ocupacionServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            OcupacionModel ocupacionModel = ocupacionServiceJPA.findById(id);
            return ResponseEntity.ok(ocupacionModel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
