package com.UNSIJ.INESIS_BACKEND.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ArchivoUtil {

    public static String guardarArchivoBase64(String base64, String nombreArchivo, String carpetaDestino) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);

        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivo = new File(carpetaDestino + File.separator + nombreArchivo);
        try (FileOutputStream fos = new FileOutputStream(archivo)) {
            fos.write(decodedBytes);
        }

        return archivo.getAbsolutePath();
    }

    public static boolean isEmptyRow(Row row) {
        if (row == null) return true;

        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    public static String getCellValueAsString(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }


}
