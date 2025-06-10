package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.service.PDFServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class pdfController {
    @Autowired
    private PDFServiceJPA pdfServiceJPA;

    @GetMapping("/alumnopdf/{id}")
    public ResponseEntity<String> generarPdfBase64(@PathVariable Long id) {
        String base64pdf = pdfServiceJPA.generarPdf(id);
        return ResponseEntity.ok(base64pdf);
    }
}
