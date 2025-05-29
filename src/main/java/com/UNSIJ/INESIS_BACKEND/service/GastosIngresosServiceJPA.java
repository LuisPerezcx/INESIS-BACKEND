package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.Ocupacion;
import com.UNSIJ.INESIS_BACKEND.repository.OcupacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.GastosIngresos;
import com.UNSIJ.INESIS_BACKEND.model.Trabajo;
import com.UNSIJ.INESIS_BACKEND.repository.GastosIngresosRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IGastosIngresosService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class GastosIngresosServiceJPA implements IGastosIngresosService {
    @Autowired
    private GastosIngresosRepository gastosIngresosRepository;

    @Autowired
    private TrabajoServiceJPA trabajoServiceJPA;

    @Autowired
    private OcupacionRepository ocupacionRepository;

    @Override
    public List<GastosIngresos> findAll() {
        return gastosIngresosRepository.findAll();
    }

    @Override
    public GastosIngresos findById(Long id) {
        return gastosIngresosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional // SIEMPRE TRANSACTIONAL AQUI
    public GastosIngresos save(GastosIngresos gastosIngresos) throws Exception {
        return gastosIngresosRepository.save(gastosIngresos);
    }

    @Override
    public GastosIngresos create(Map<String, Object> params) throws Exception {
        GastosIngresos gastosIngresos = new GastosIngresos();
        try {
            this.build(params, gastosIngresos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el gastosIngresos(create)");
        }
        return this.save(gastosIngresos);
    }

    // ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN
    // MAPA(JSON)
    @Override
    public GastosIngresos update(GastosIngresos gastosIngresos, Map<String, Object> params) throws Exception {
        try {
            this.build(params, gastosIngresos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el gastosIngresos(update)");
        }
        return this.save(gastosIngresos);
    }

    @Override
    public GastosIngresos build(Map<String, Object> params, GastosIngresos gastosIngresos) {
        try {
            Double gastoMensual = JsonUtils.obtDouble(params, "gastoMensual");
            if (gastoMensual == null)
                throw new IllegalArgumentException("El campo gasto mensual es obligatorio");
            gastosIngresos.setGastoMensual(gastoMensual);

            String dependeEconomicamente = JsonUtils.obtString(params, "dependeEconomicamente");
            if (dependeEconomicamente == null)
                throw new IllegalArgumentException("El campo depende economicamente es obligatorio");
            gastosIngresos.setDependeEconomicamente(dependeEconomicamente);

            if (dependeEconomicamente.equals("Si")) {
                String nombreQuienDependes = JsonUtils.obtString(params, "nombreQuienDependes");
                gastosIngresos.setNombreQuienDependes(nombreQuienDependes);
                String trabajoTipo = JsonUtils.obtString(params, "trabajoTipo");
                gastosIngresos.setTrabajoTipo(trabajoTipo);
                Long idOcupacion = JsonUtils.obtLong(params, "ocupacion");
                if (idOcupacion == null) {
                    throw new IllegalArgumentException("El campo 'idOcupacion' es obligatorio.");
                }
                Ocupacion ocupacion = ocupacionRepository.findById(idOcupacion)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Ocupacion no encontrado con el ID: " + idOcupacion));
                gastosIngresos.setOcupacion(ocupacion);

                String otro = JsonUtils.obtString(params, "otro");
                gastosIngresos.setOtro(otro);
            }

            String solicitaBecaAlimenticia = JsonUtils.obtString(params, "solicitaBecaAlimenticia");
            if (solicitaBecaAlimenticia == null)
                throw new IllegalArgumentException("El campo solicitar besa alimenticia es obligatorio");
            gastosIngresos.setSolicitaBecaAlimenticia(solicitaBecaAlimenticia);
            System.out.println("Parametros gastosIngresos: " + params);

            Map<String, Object> trabajoMap = (Map<String, Object>) params.get("trabajo");
            if (trabajoMap != null
                    && trabajoMap.values().stream().anyMatch(v -> v != null && !v.toString().trim().isEmpty())) {
                Trabajo trabajo = trabajoServiceJPA.create(trabajoMap);
                gastosIngresos.setTrabajo(trabajo);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el gatosIngresos(buil)");
        }
        return gastosIngresos;
    }

    // ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public GastosIngresos updateInstance(GastosIngresos gastosIngresosInstance) throws Exception {
        GastosIngresos gastosIngresosBD = this.findById(gastosIngresosInstance.getIdGatosIngresos());

        // gastosIngresosBD.setCuantoHacienden(gastosIngresosInstance.getCuantoHacienden());
        gastosIngresosBD.setDependeEconomicamente(gastosIngresosInstance.getDependeEconomicamente());
        gastosIngresosBD.setNombreQuienDependes(gastosIngresosInstance.getNombreQuienDependes());
        gastosIngresosBD.setGastoMensual(gastosIngresosInstance.getGastoMensual());
        gastosIngresosBD.setSolicitaBecaAlimenticia(gastosIngresosInstance.getSolicitaBecaAlimenticia());
        gastosIngresosBD.setTrabajoTipo(gastosIngresosInstance.getTrabajoTipo());
        gastosIngresosBD.setOcupacion(gastosIngresosInstance.getOcupacion());
        gastosIngresosBD.setOtro(gastosIngresosInstance.getOtro());

        return this.save(gastosIngresosBD);
    }

    @Override
    public void deleteById(Long id) {
        GastosIngresos gastosIngresos = this.findById(id);
        if (gastosIngresos != null) {
            gastosIngresosRepository.deleteById(id);
        }
    }
}
