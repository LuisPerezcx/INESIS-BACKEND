package com.UNSIJ.INESIS_BACKEND.utils;

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
}
