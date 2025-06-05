package com.UNSIJ.INESIS_BACKEND.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatEstado;
import com.UNSIJ.INESIS_BACKEND.model.AlumnoModel;
import com.UNSIJ.INESIS_BACKEND.repository.CatEstadoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatEstadoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;


@Service
public class CatEstadoServiceJPA implements ICatEstadoService {

    @Autowired
    private CatEstadoRepository catEstadoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<CatEstado> findAll() {
        return catEstadoRepository.findAll();
    }

    @Override
    public CatEstado findById(Long id) {
        return catEstadoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Estado no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public CatEstado save(CatEstado catEstado) throws Exception {
        return catEstadoRepository.save(catEstado);
    }

    @Override
    public CatEstado create(Map<String, Object> params) throws Exception {
        CatEstado catEstado = new CatEstado();
        try {
            this.build(params, catEstado);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el estado");
        }
        return this.save(catEstado);
    }

    @Override
    public CatEstado update(CatEstado catEstado, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catEstado);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el estado");
        }
        return this.save(catEstado);
    }

    @Override
    public CatEstado build(Map<String, Object> params, CatEstado catEstado) {
        try {
            Long idAlumno = JsonUtils.obtLong(params, "idAlumno");
            String nombreEstado = JsonUtils.obtString(params, "nombreEstado");

            if (idAlumno == null) throw new IllegalArgumentException("El campo 'idAlumno' es obligatorio");
            if (nombreEstado == null || nombreEstado.trim().isEmpty())
                throw new IllegalArgumentException("El campo 'nombreEstado' es obligatorio");

            AlumnoModel alumno = alumnoRepository.findById(idAlumno).orElseThrow(() ->
                    new IllegalArgumentException("No se encontró el alumno con ID: " + idAlumno));

            catEstado.setAlumno(alumno);
            catEstado.setEstatus(nombreEstado);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el estado");
        }
        return catEstado;
    }

    @Override
    public CatEstado updateInstance(CatEstado catEstadoInstance) throws Exception {
        CatEstado catEstadoBD = this.findById(catEstadoInstance.getId());
        catEstadoBD.setEstatus(catEstadoInstance.getEstatus());
        catEstadoBD.setAlumno(catEstadoInstance.getAlumno());
        return this.save(catEstadoBD);
    }

    @Override
    public void deleteById(Long id) {
        CatEstado catEstado = this.findById(id);
        if (catEstado != null) {
            catEstadoRepository.deleteById(id);
        }
    }
}
