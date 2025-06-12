package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatCarrera;
import com.UNSIJ.INESIS_BACKEND.model.CatGrupo;
import com.UNSIJ.INESIS_BACKEND.model.CatSemestre;
import com.UNSIJ.INESIS_BACKEND.repository.CatCarreraRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatGrupoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSemestreRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatGrupoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CatGrupoServiceJPA implements ICatGrupoService {

    @Autowired
    private CatGrupoRepository catGrupoRepository;

    @Autowired
    private CatCarreraRepository catCarreraRepository;

    @Autowired
    private CatSemestreRepository catSemestreRepository;

    @Override
    public List<CatGrupo> findAll() {
        return catGrupoRepository.findAll();
    }

    @Override
    public CatGrupo findById(Long id) {
        return catGrupoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public CatGrupo save(CatGrupo catGrupo) throws Exception {
        return catGrupoRepository.save(catGrupo);
    }

    @Override
    public CatGrupo create(Map<String, Object> params) throws Exception {
        CatGrupo catGrupo = new CatGrupo();
        try {
            this.build(params, catGrupo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el grupo");
        }
        return this.save(catGrupo);
    }

    @Override
    public CatGrupo update(CatGrupo catGrupo, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catGrupo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el grupo");
        }
        return this.save(catGrupo);
    }

    @Override
    public CatGrupo build(Map<String, Object> params, CatGrupo catGrupo) {
        try {
            Long idCarrera = JsonUtils.obtLong(params, "idCarrera");
            Long idSemestre = JsonUtils.obtLong(params, "idSemestre");

            if (idCarrera == null || idSemestre == null) {
                throw new IllegalArgumentException("Los campos idCarrera y idSemestre son obligatorios");
            }

            // Obtener carrera y semestre
            CatCarrera carrera = catCarreraRepository.findById(idCarrera)
                    .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada con ID: " + idCarrera));
            CatSemestre semestre = catSemestreRepository.findById(idSemestre)
                    .orElseThrow(() -> new IllegalArgumentException("Semestre no encontrado con ID: " + idSemestre));

            // Establecer las relaciones
            catGrupo.setCatCarrera(carrera);
            catGrupo.setCatSemestre(semestre);


        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el grupo");
        }
        return catGrupo;
    }

    @Override
    public CatGrupo updateInstance(CatGrupo catGrupoInstance) throws Exception {
        CatGrupo catGrupoBD = this.findById(catGrupoInstance.getId());
        catGrupoBD.setNombreGrupo(catGrupoInstance.getNombreGrupo());
        catGrupoBD.setCatCarrera(catGrupoInstance.getCatCarrera());
        catGrupoBD.setCatSemestre(catGrupoInstance.getCatSemestre());
        return this.save(catGrupoBD);
    }

    @Override
    public void deleteById(Long id) {
        CatGrupo catGrupo = this.findById(id);
        if (catGrupo != null) {
            catGrupoRepository.deleteById(id);
        }
    }

    @Override
    public CatGrupo obtenerNombreGrupo(Long idCarrera, Long idSemestre) {
        CatGrupo grupo = catGrupoRepository.findByCatCarrera_IdAndCatSemestre_Id(idCarrera, idSemestre);

        if (grupo != null) {
            return grupo;
        } else {
            throw new IllegalArgumentException("No existe un grupo con esa carrera y semestre.");
        }
    }
}
