package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatCarreraModel;
import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradasModel;
import com.UNSIJ.INESIS_BACKEND.repository.FechasRegistradasRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IFechasRegistradasService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class FechasRegistradasServiceJPA implements IFechasRegistradasService {

    @Autowired
    private FechasRegistradasRepository fechasRegistradasRepository;

    @Autowired
    private CatCarreraServiceJPA carreraServiceJPA;

    @Override
    public List<FechasRegistradasModel> findAll() {
        return fechasRegistradasRepository.findAll();
    }

    @Override
    public FechasRegistradasModel findById(Long id) {
        return fechasRegistradasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fecha registrada no encontrada con el ID: " + id));
    }

    @Override
    @Transactional // SIEMPRE TRANSACTIONAL AQUI
    public FechasRegistradasModel save(FechasRegistradasModel fechasRegistradas) throws Exception {
        return fechasRegistradasRepository.save(fechasRegistradas);
    }

    @Override
    public FechasRegistradasModel create(Map<String, Object> params) throws Exception {
        FechasRegistradasModel fechasRegistradas = new FechasRegistradasModel();
        try {
            // Aquí puedes asignar valores que sólo se necesitan al crear un registro por
            // primera vez
            // fechasRegistradas.setActive(true); // Ejemplo de valor por defecto
            // Ahora llamamos al método que se ocupa de construir el objeto
            this.build(params, fechasRegistradas);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Opcional, ayuda en depuración si ocurre algún error inesperado
            throw new IllegalArgumentException("Error al construir la fecha registrada");
        }
        return this.save(fechasRegistradas);
    }

    @Override
    public FechasRegistradasModel update(FechasRegistradasModel fechasRegistradas, Map<String, Object> params)
            throws Exception {
        try {
            this.build(params, fechasRegistradas);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Opcional, ayuda en depuración si ocurre algún error inesperado
            throw new IllegalArgumentException("Error al construir la fecha registrada");
        }
        return this.save(fechasRegistradas);
    }

   @Override
public FechasRegistradasModel build(Map<String, Object> params, FechasRegistradasModel fechasRegistradas)
        throws IllegalArgumentException {
    try {
        Long idCarrera = JsonUtils.obtLong(params, "idCarrera");
        if (idCarrera == null) {
            throw new IllegalArgumentException("El campo 'carrera' es obligatorio");
        }

        CatCarreraModel carrera = carreraServiceJPA.findById(idCarrera);
        if (carrera == null) {
            throw new IllegalArgumentException("Carrera no encontrada con el ID: " + idCarrera);
        }
        fechasRegistradas.setCarrera(carrera);

        // Obtener fechas
        java.sql.Date fechaInicio = JsonUtils.obtDate(params, "fechaInicio");
        java.sql.Date fechaFin = JsonUtils.obtDate(params, "fechaFin");

        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias");
        }

        fechasRegistradas.setFechaInicio(fechaInicio);
        fechasRegistradas.setFechaFin(fechaFin);

        // Asignar valor al campo obligatorio 'status' (ejemplo con valor por defecto)
        String status = JsonUtils.obtString(params, "status");
        if (status == null) {
            status = "ACTIVO"; // Valor por defecto si no se envía
        }
        fechasRegistradas.setStatus(status);

    } catch (IllegalArgumentException e) {
        throw e;
    } catch (Exception e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Error al construir la fecha registrada: " + e.getMessage());
    }
    return fechasRegistradas;
}
    @Override
    public FechasRegistradasModel updateInstance(FechasRegistradasModel fechasRegistradasInstance) throws Exception {
        FechasRegistradasModel fechasRegistradasBD = this.findById(fechasRegistradasInstance.getId());
        fechasRegistradasBD.setCarrera(fechasRegistradasInstance.getCarrera());
        fechasRegistradasBD.setFechaInicio(fechasRegistradasInstance.getFechaInicio());
        fechasRegistradasBD.setFechaFin(fechasRegistradasInstance.getFechaFin());
        return this.save(fechasRegistradasBD);
    }

    @Override
    public void deleteById(Long id) {
        FechasRegistradasModel fechasRegistradas = this.findById(id);
        if (fechasRegistradas != null) {
            fechasRegistradasRepository.deleteById(id);
        }
    }

    public class DateUtils {
        public static String formatDateToString(Date date) {
            // Usamos SimpleDateFormat para convertir la fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(date); // Convertimos la fecha a String con el formato deseado
        }
    }

public FechasRegistradasModel findByCarreraId(Long idCarrera) {
    return fechasRegistradasRepository.findByCarrera_Id(idCarrera)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró fecha registrada para la carrera con id: " + idCarrera));
}


}
