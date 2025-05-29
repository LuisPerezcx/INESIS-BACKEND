package com.UNSIJ.INESIS_BACKEND.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;

public class JsonUtils {

    public static Object obtObject(Map<String, Object> map, String key) {
        if (map == null || key == null || key.isEmpty())
            return null;

        String[] keys = key.split("\\.");
        Object current = map;

        for (String k : keys) {
            if (current instanceof Map) {
                current = ((Map<String, Object>) current).get(k);
            } else
                return null;
        }
        return current;
    }

    public static String obtString(Map<String, Object> map, String key) {
        Object value = obtObject(map, key);
        if (value == null)
            return null;
        return value instanceof String ? (String) value : null;
    }

    public static Integer obtInteger(Map<String, Object> map, String key) {
        Object value = obtObject(map, key);
        if (value == null)
            return null;
        if (value instanceof Integer)
            return (Integer) value;

        if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public static Double obtDouble(Map<String, Object> map, String key) {
        Object value = obtObject(map, key);
        if (value == null) {
            return null;
        }
        if (value instanceof Double)
            return (Double) value;

        if (value instanceof Integer)
            return ((Integer) value).doubleValue();

        if (value instanceof Long)
            return ((Long) value).doubleValue();

        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir a Double.");
                return null;
            }
        }
        return null;
    }

    public static Long obtLong(Map<String, Object> map, String key) {
        Object value = obtObject(map, key);
        if (value == null)
            return null;
        if (value instanceof Long)
            return (Long) value;

        try {
            if (value instanceof String)
                return Long.parseLong((String) value);
            if (value instanceof Number)
                return ((Number) value).longValue();
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public static java.sql.Date obtDate(Map<String, Object> map, String key) {
        String value = obtString(map, key);
        if (value == null) {
            return null;
        }
        try {
            // Formato esperado: "yyyy-MM-dd" (ejemplo: "2025-05-15")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(value, formatter);
            // Convertir directamente a java.sql.Date (sin zonas horarias)
            return java.sql.Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            // Lanza excepción para manejar errores claramente
            throw new IllegalArgumentException("Formato de fecha inválido para '" + key + "'. Use yyyy-MM-dd");
        }
    }

    public static LocalDate obtLocalDate(Map<String, Object> map, String key) {
        String value = obtString(map, key);
        if (value == null)
            return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static LocalTime obtLocalTime(Map<String, Object> map, String key) {
        String value = obtString(map, key);
        if (value == null)
            return null;
        try {
            return LocalTime.parse(value);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static Boolean obtBoolean(Map<String, Object> map, String key) {
        Object value = obtObject(map, key);

        if (value instanceof Boolean)
            return (boolean) value;

        if (value instanceof String) {
            String strValue = ((String) value).trim().toLowerCase();
            return "true".equals(strValue);
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() != 0; // 0 es false, cualquier otro número es true
        }

        return null; // Si el valor es null o no es convertible a boolean, retorna false
    }

    public static Boolean parseBooleanFlexible(Object value, String fieldName) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof String) {
            String str = ((String) value).trim();
            if ("Si".equalsIgnoreCase(str)) return true;
            if ("No".equalsIgnoreCase(str)) return false;
            if ("true".equalsIgnoreCase(str)) return true;
            if ("false".equalsIgnoreCase(str)) return false;
        }
        if (value == null) {
            throw new IllegalArgumentException("El campo " + fieldName + " es obligatorio");
        }
        throw new IllegalArgumentException("El valor de '" + fieldName + "' debe ser 'Si', 'No', true o false.");
    }
}