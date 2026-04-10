package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ArchivoServiceJPA {

    @Value("${app.upload.dir:archivos}")
    private String uploadDir;

    public String guardarArchivoBase64(String base64Data, String nombreOriginal, String tipoCarpeta, String carpetaPersonal, boolean limpiarCarpeta) throws IOException {
        // Normalizar carpeta personal
        carpetaPersonal = java.text.Normalizer.normalize(carpetaPersonal, java.text.Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9_-]", "_");

        // Crear ruta final
        Path directorioCompleto = Paths.get(uploadDir, tipoCarpeta, carpetaPersonal);

        // Eliminar archivos existentes
        if (limpiarCarpeta && Files.exists(directorioCompleto)) {
            Files.walk(directorioCompleto)
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            System.err.println("Error al eliminar archivo existente: " + path + " -> " + e.getMessage());
                        }
                    });
        }

        Files.createDirectories(directorioCompleto);

        // Validar extensión
        String extension = "";
        if (nombreOriginal != null && nombreOriginal.contains(".")) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf(".")).toLowerCase();
        }

        List<String> extensionesPermitidas = List.of(".jpg", ".jpeg", ".png", ".pdf");
        if (!extensionesPermitidas.contains(extension)) {
            throw new IllegalArgumentException("Extensión de archivo no permitida: " + extension);
        }


        // Construir rutas
        String rutaRelativa = Paths.get(tipoCarpeta, carpetaPersonal, nombreOriginal).toString();
        Path rutaCompleta = Paths.get(uploadDir, rutaRelativa);

        // Limpiar base64
        if (base64Data.contains(",")) {
            base64Data = base64Data.split(",")[1];
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
        Files.write(rutaCompleta, decodedBytes);

        return rutaRelativa;
    }



    public byte[] obtenerArchivo(String rutaRelativa) throws IOException {
        Path rutaCompleta = Paths.get(uploadDir, rutaRelativa);
        return Files.readAllBytes(rutaCompleta);
    }

    public void eliminarArchivo(String rutaRelativa) {
        if (StringUtils.hasText(rutaRelativa)) {
            try {
                Path rutaCompleta = Paths.get(uploadDir, rutaRelativa);
                Files.deleteIfExists(rutaCompleta);
            } catch (IOException e) {
                System.err.println("Error al eliminar archivo: " + e.getMessage());
            }
        }
    }
}