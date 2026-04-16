package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CodigoPostal;
import com.UNSIJ.INESIS_BACKEND.repository.CatCodigoPostalRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatCodigoPostalService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CatCodigoPostalServiceJPA implements ICatCodigoPostalService {

    private static final Logger log = LoggerFactory.getLogger(CatCodigoPostalServiceJPA.class);

    private static final int BATCH_SIZE = 1000;

    private static final int COL_D_CODIGO = 0; // A
    private static final int COL_D_ASENTA = 1; // B
    private static final int COL_D_MNPIO = 3;  // D
    private static final int COL_D_ESTADO = 4; // E

    @Autowired
    private CatCodigoPostalRepository catCodigoPostalRepository;

    @Override
    public List<CodigoPostal> findByCp(String cp) {
        return catCodigoPostalRepository.findCodigoPostalByCodigoPostal(cp);
    }

    @Override
    @Transactional
    public Map<String, Object> importarDesdeExcel(MultipartFile file) throws Exception {
        validarArchivo(file);

        int hojasProcesadas = 0;
        int filasLeidas = 0;
        int filasValidas = 0;
        int filasOmitidas = 0;
        int registrosGuardados = 0;

        List<CodigoPostal> batch = new ArrayList<>(BATCH_SIZE);

        //  Borrar todo antes de importar
        log.info("Iniciando importación... Limpiando tabla de codigos postales");
        catCodigoPostalRepository.deleteAllInBatch();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            DataFormatter formatter = new DataFormatter(Locale.ROOT);

            for (Sheet sheet : workbook) {
                hojasProcesadas++;

                int filasLeidasHoja = 0;
                int filasValidasHoja = 0;
                int filasOmitidasHoja = 0;
                String estadoHoja = sheet.getSheetName();

                log.info("Iniciando procesamiento de hoja/estado: {}", estadoHoja);

                if (sheet.getPhysicalNumberOfRows() == 0) continue;
                
                int inicio = sheet.getFirstRowNum() + 1;
                for (int i = inicio; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;

                    filasLeidas++;
                    filasLeidasHoja++;

                    String codigoPostal = normalizarCodigoPostal(obtenerCelda(row, COL_D_CODIGO, formatter));
                    String asentamiento = limpiarTexto(obtenerCelda(row, COL_D_ASENTA, formatter));
                    String municipio = limpiarTexto(obtenerCelda(row, COL_D_MNPIO, formatter));
                    String estado = limpiarTexto(obtenerCelda(row, COL_D_ESTADO, formatter));

                    //saltar filas vacias
                    if (codigoPostal == null && asentamiento == null && municipio == null && estado == null) continue;

                    if (codigoPostal == null || asentamiento == null || municipio == null || estado == null) {
                        filasOmitidas++;
                        filasOmitidasHoja++;

                        // Log detallado de por qué se omitió
                        StringBuilder razon = new StringBuilder("Campos faltantes: ");
                        if (codigoPostal == null) razon.append("d_codigo ");
                        if (asentamiento == null) razon.append("d_asenta ");
                        if (municipio == null) razon.append("d_mnpio ");
                        if (estado == null) razon.append("d_estado ");
                        log.info("Fila {} omitida: {}", i, razon.toString());
                        continue;
                    }

                    if (filasValidasHoja == 0) {
                        estadoHoja = estado;
                    }

                    CodigoPostal cp = new CodigoPostal();
                    cp.setCodigoPostal(codigoPostal);
                    cp.setNombreAsentamiento(asentamiento);
                    cp.setNombreMunicipio(municipio);
                    cp.setNombreEstado(estado);
                    cp.setActive(true);

                    batch.add(cp);
                    filasValidas++;
                    filasValidasHoja++;

                    if (batch.size() >= BATCH_SIZE) {
                        registrosGuardados += guardarLote(batch);
                    }
                }

                log.info(
                        "Hoja/estado procesado: {} | Leidas: {} | Validas: {} | Omitidas: {}",
                        estadoHoja,
                        filasLeidasHoja,
                        filasValidasHoja,
                        filasOmitidasHoja
                );
            }

            if (!batch.isEmpty()) {
                registrosGuardados += guardarLote(batch);
            }
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("hojasProcesadas", hojasProcesadas);
        resultado.put("filasLeidas", filasLeidas);
        resultado.put("filasValidas", filasValidas);
        resultado.put("filasOmitidas", filasOmitidas);
        resultado.put("registrosGuardados", registrosGuardados);

        return resultado;
    }

    private int guardarLote(List<CodigoPostal> batch) {
        int size = batch.size();
        catCodigoPostalRepository.saveAll(batch);
        batch.clear();
        return size;
    }

    private void validarArchivo(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Debes enviar un archivo Excel no vacio.");
        }

        String nombre = file.getOriginalFilename();
        if (nombre == null) {
            throw new IllegalArgumentException("No se pudo obtener el nombre del archivo.");
        }

        String lower = nombre.toLowerCase();
        if (!lower.endsWith(".xlsx") && !lower.endsWith(".xls")) {
            throw new IllegalArgumentException("El archivo debe ser .xlsx o .xls");
        }
    }


    private String obtenerCelda(Row row, Integer index, DataFormatter formatter) {
        if (index == null) return null;
        Cell cell = row.getCell(index);
        if (cell == null) return null;
        return formatter.formatCellValue(cell);
    }


    private String limpiarTexto(String value) {
        if (value == null) return null;
        String limpio = value.trim();
        return limpio.isEmpty() ? null : limpio;
    }

    private String normalizarCodigoPostal(String value) {
        String base = limpiarTexto(value);
        if (base == null) return null;

        String digitos = base.replaceAll("\\D", "");
        if (digitos.isEmpty() || digitos.length() > 5) return null;

        if (digitos.length() < 5) {
            digitos = "0".repeat(5 - digitos.length()) + digitos;
        }

        return digitos;
    }
}
