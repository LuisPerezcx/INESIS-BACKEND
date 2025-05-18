package com.UNSIJ.INESIS_BACKEND.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cp")
public class DomicilioController {

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
