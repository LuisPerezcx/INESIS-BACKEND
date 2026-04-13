package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.CodigoPostal;
import com.UNSIJ.INESIS_BACKEND.service.CatCodigoPostalServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cp")
public class CatCodigoPostalController {
    @Autowired
    private CatCodigoPostalServiceJPA cPServiceJPA;

    @GetMapping("/{cp}")
    public ResponseEntity<?> buscarPorCp(@PathVariable String cp){
        try{
            List<CodigoPostal> resultados = cPServiceJPA.findByCp(cp);
            if (resultados == null || resultados.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe informacion para el CP: " + cp);
            }
            return ResponseEntity.ok(resultados);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor cp");
        }
    }

    @PostMapping("/importar")
    public ResponseEntity<?> importarExcel(@RequestParam("file") MultipartFile file){
        try {
            Map<String, Object> resultado = cPServiceJPA.importarDesdeExcel(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }

}
