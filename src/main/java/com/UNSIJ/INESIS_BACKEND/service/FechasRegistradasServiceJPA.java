package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatCarrera;
import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradas;
import com.UNSIJ.INESIS_BACKEND.repository.FechasRegistradasRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IFechasRegistradasService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FechasRegistradasServiceJPA implements IFechasRegistradasService {

    @Autowired
    private FechasRegistradasRepository fechasRegistradasRepository;

    @Autowired
    private CatCarreraServiceJPA carreraServiceJPA;

    @Override
    public List<FechasRegistradas> findAll() {
        List<FechasRegistradas> lista = fechasRegistradasRepository.findAll();
        Date hoy = new Date();

        for (FechasRegistradas fecha : lista) {
            if (fecha.isActive()) {
                // Calcula si la fecha fin ya pasó o es igual a hoy
                if (!fecha.getFechaFin().after(hoy)) {
                    // Si ya pasó o es hoy, desactiva
                    fecha.setActive(false);
                    fecha.setRestante(0);
                    // Guarda el cambio
                    fechasRegistradasRepository.save(fecha);
                } else {
                    // Calcula días restantes
                    long diff = fecha.getFechaFin().getTime() - hoy.getTime();
                    int diasRestantes = (int) (diff / (1000 * 60 * 60 * 24));
                    fecha.setRestante(diasRestantes);
                }
            }
        }
        return lista;
    }

    @Override
    public FechasRegistradas findById(Long id) {
        return fechasRegistradasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fecha registrada no encontrada con el ID: " + id));
    }

    @Override
    @Transactional // SIEMPRE TRANSACTIONAL AQUI
    public FechasRegistradas save(FechasRegistradas fechasRegistradas) throws Exception {
        return fechasRegistradasRepository.save(fechasRegistradas);
    }

    @Override
    public FechasRegistradas create(Map<String, Object> params) throws Exception {
        FechasRegistradas fechasRegistradas = new FechasRegistradas();
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
    public FechasRegistradas update(FechasRegistradas fechasRegistradas, Map<String, Object> params)
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
    public FechasRegistradas build(Map<String, Object> params, FechasRegistradas fechasRegistradas)
            throws IllegalArgumentException {
        try {
            Long idCarrera = JsonUtils.obtLong(params, "idCarrera");
            if (idCarrera == null) {
                throw new IllegalArgumentException("El campo 'carrera' es obligatorio");
            }

            CatCarrera carrera = carreraServiceJPA.findById(idCarrera);
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

            // Obtener valor de 'active' o asignar true por defecto
            Boolean active = null;
            if (params.containsKey("active")) {
                active = JsonUtils.obtBoolean(params, "active");
            }
            if (active == null) {
                active = true; // Valor por defecto
            }
            fechasRegistradas.setActive(active);

            // Calcular días restantes entre fecha actual y fechaFin
            long millisActual = new java.util.Date().getTime();
            long millisFin = fechaFin.getTime();
            long diferenciaMillis = millisFin - millisActual;

            int diasRestantes = (int) Math.max(diferenciaMillis / (1000 * 60 * 60 * 24), 0); // mínimo 0
            fechasRegistradas.setRestante(diasRestantes);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir la fecha registrada: " + e.getMessage());
        }
        return fechasRegistradas;
    }

    @Override
    public FechasRegistradas updateInstance(FechasRegistradas fechasRegistradasInstance) throws Exception {
        FechasRegistradas fechasRegistradasBD = this.findById(fechasRegistradasInstance.getId());
        fechasRegistradasBD.setCarrera(fechasRegistradasInstance.getCarrera());
        fechasRegistradasBD.setFechaInicio(fechasRegistradasInstance.getFechaInicio());
        fechasRegistradasBD.setFechaFin(fechasRegistradasInstance.getFechaFin());
        return this.save(fechasRegistradasBD);
    }

    @Override
    public void deleteById(Long id) {
        FechasRegistradas fechasRegistradas = this.findById(id);
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

    public Optional<FechasRegistradas> findOptionalByCarreraId(Long idCarrera) {
        return fechasRegistradasRepository.findByCarrera_Id(idCarrera);
    }

    public List<FechasRegistradas> findByCarreraIds(Set<Long> ids) {
        return fechasRegistradasRepository.findByCarrera_IdIn(ids);
    }

    public FechasRegistradas findByCarreraId(Long idCarrera) {
        return fechasRegistradasRepository.findByCarrera_Id(idCarrera)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró fecha registrada para la carrera con id: " + idCarrera));
    }

    public boolean estaEnRangoFechasRegistradas(Long idCarrera, Date fechaPeticion) {
        try {
            Optional<FechasRegistradas> fechasOpt = fechasRegistradasRepository.findByCarrera_Id(idCarrera);
            // Si no hay fechas registradas o no está activo, retornamos false
            if (fechasOpt.isEmpty() || !fechasOpt.get().isActive()) {
                return false;
            }
            FechasRegistradas fechas = fechasOpt.get();
            // Comprobamos si la fecha de petición está dentro del rango (inclusivo)
            return !fechaPeticion.before(fechas.getFechaInicio()) && !fechaPeticion.after(fechas.getFechaFin());
        } catch (Exception e) {
            // Si ocurre algún error, asumimos que no está en rango
            return false;
        }
    }

    public boolean permitirRegistro(Long idCarrera) {
        return estaEnRangoFechasRegistradas(idCarrera, new Date());
    }

}
