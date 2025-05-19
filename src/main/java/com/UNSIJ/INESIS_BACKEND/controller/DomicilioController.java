package com.UNSIJ.INESIS_BACKEND.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.UNSIJ.INESIS_BACKEND.model.Domicilio;
import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.service.DomicilioServiceJPA;
import com.UNSIJ.INESIS_BACKEND.service.EjemploServiceJPA;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {

    @Autowired
    private DomicilioServiceJPA domicilioServiceJPA; // aquí siempre es el service no la interfaz

    @GetMapping
    public List<Domicilio> list() {
        return domicilioServiceJPA.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        try {
            Domicilio domicilio = domicilioServiceJPA.findById(id);
            return ResponseEntity.ok(domicilio);
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
            Domicilio domicilio = domicilioServiceJPA.create(params);
            return ResponseEntity.status(HttpStatus.CREATED).body(domicilio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> params) {
        try {
            Domicilio domicilioUpdated = domicilioServiceJPA.update(domicilioServiceJPA.findById(id),params);
            return ResponseEntity.status(HttpStatus.CREATED).body(domicilioUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        try {
            domicilioServiceJPA.findById(id);
            domicilioServiceJPA.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/codigo_postal")
    public ResponseEntity<String> obtenerColoniasPorCP(@RequestParam String cp) {
        String urlApiExterna = "https://api.tau.com.mx/dipomex/v1/codigo_postal?cp=" + cp;

        RestTemplate restTemplate = new RestTemplate();

        // Aquí si la API requiere headers, como APIKEY, los agregas:
        HttpHeaders headers = new HttpHeaders();
        headers.set("APIKEY", "b502adbdb721f79c3f1a40435af924b071d5b637");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                urlApiExterna,
                HttpMethod.GET,
                entity,
                String.class
            );

            // Retornas la respuesta tal cual a tu frontend
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            // Manejo básico de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\":\"No se pudo obtener datos del CP\"}");
        }
    }
}
