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

import com.UNSIJ.INESIS_BACKEND.model.CatCarreraModel;
import com.UNSIJ.INESIS_BACKEND.service.CatCarreraServiceJPA;

@RestController
@RequestMapping("/carrera") 
public class CatCarreraController {
     @Autowired
    private CatCarreraServiceJPA carreraServiceJPA; 

    
    @GetMapping
    public List<CatCarreraModel> list() {
        return carreraServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            CatCarreraModel catCatCarreraModel = carreraServiceJPA.findById(id);
            return ResponseEntity.ok(catCatCarreraModel);
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
            CatCarreraModel catCatCarreraModel = carreraServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(catCatCarreraModel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            CatCarreraModel catCatCarreraUpdate = carreraServiceJPA.update(carreraServiceJPA.findById(id),params);
            return ResponseEntity.status(HttpStatus.CREATED).body(catCatCarreraUpdate);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        try {
            carreraServiceJPA.findById(id); // PARA TIRAR LA EXEPCION SI NO SE ENCUENTRA EL REGISTRO
            carreraServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
