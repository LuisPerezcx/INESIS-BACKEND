package com.UNSIJ.INESIS_BACKEND.config;

import com.UNSIJ.INESIS_BACKEND.service.CatCodigoPostalServiceJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

@Component
public class DataLoaderStartup {

    private static final Logger log = LoggerFactory.getLogger(DataLoaderStartup.class);

    @Autowired
    private CatCodigoPostalServiceJPA catCodigoPostalService;

    @Value("${app.autoload.codigos-postales.enabled:false}")
    private boolean autoloadCodigosPostalesEnabled;

    @Value("${app.autoload.codigos-postales.file:}")
    private String codigosPostalesFilePath;

    @EventListener(ApplicationReadyEvent.class)
    public void cargarDatosAlIniciar() {
        log.info("===============================================");
        log.info("Iniciando DataLoader - Aplicación lista");
        log.info("===============================================");

        if (autoloadCodigosPostalesEnabled) {
            cargarCodigosPostales();
        } else {
            log.info("Autoload de codigos postales deshabilitado en application.properties");
        }

        log.info("===============================================");
        log.info("DataLoader completado");
        log.info("===============================================");
    }

    private void cargarCodigosPostales() {
        log.info("Iniciando carga automática de Códigos Postales...");

        if (codigosPostalesFilePath == null || codigosPostalesFilePath.trim().isEmpty()) {
            log.warn("No se especificó ruta para el archivo de códigos postales");
            return;
        }

        File archivo = new File(codigosPostalesFilePath);
        if (!archivo.exists()) {
            log.warn("El archivo de códigos postales no existe en: {}", codigosPostalesFilePath);
            return;
        }

        // Verificar si ya existen datos en la BD
        long countRegistros = catCodigoPostalService.contarRegistros();
        if (countRegistros > 0) {
            log.info("Ya existen {} registros de códigos postales en la BD. Omitiendo carga automática.", countRegistros);
            return;
        }

        try {
            long startTime = System.currentTimeMillis();
            Map<String, Object> resultado = catCodigoPostalService.importarDesdeArchivo(codigosPostalesFilePath);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            log.info("✓ Carga de Códigos Postales completada exitosamente");
            log.info("  - Hojas procesadas: {}", resultado.get("hojasProcesadas"));
            log.info("  - Filas leídas: {}", resultado.get("filasLeidas"));
            log.info("  - Filas válidas: {}", resultado.get("filasValidas"));
            log.info("  - Filas omitidas: {}", resultado.get("filasOmitidas"));
            log.info("  - Registros guardados: {}", resultado.get("registrosGuardados"));
            log.info("  - Tiempo de ejecución: {} ms", duration);

        } catch (Exception e) {
            log.error("✗ Error al cargar Códigos Postales desde: {}", codigosPostalesFilePath);
            log.error("Detalles del error:", e);
        }
    }
}

