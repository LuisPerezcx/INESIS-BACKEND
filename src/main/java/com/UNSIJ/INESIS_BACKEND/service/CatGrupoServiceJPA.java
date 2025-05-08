package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.CatGrupoModel;
import com.UNSIJ.INESIS_BACKEND.model.CatCarreraModel;
import com.UNSIJ.INESIS_BACKEND.model.CatSemestreModel;
import com.UNSIJ.INESIS_BACKEND.repository.CatGrupoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatCarreraRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSemestreRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatGrupoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class CatGrupoServiceJPA implements ICatGrupoService {

    @Autowired
    private CatGrupoRepository catGrupoRepository;

    @Autowired
    private CatCarreraRepository catCarreraRepository;

    @Autowired
    private CatSemestreRepository catSemestreRepository;

    @Override
    public List<CatGrupoModel> findAll() {
        return catGrupoRepository.findAll();
    }

    @Override
    public CatGrupoModel findById(Long id) {
        return catGrupoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public CatGrupoModel save(CatGrupoModel catGrupoModel) throws Exception {
        return catGrupoRepository.save(catGrupoModel);
    }

    @Override
    public CatGrupoModel create(Map<String, Object> params) throws Exception {
        CatGrupoModel catGrupoModel = new CatGrupoModel();
        try {
            this.build(params, catGrupoModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el grupo");
        }
        return this.save(catGrupoModel);
    }

    @Override
    public CatGrupoModel update(CatGrupoModel catGrupoModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catGrupoModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el grupo");
        }
        return this.save(catGrupoModel);
    }

    @Override
    public CatGrupoModel build(Map<String, Object> params, CatGrupoModel catGrupoModel) {
        try {
            Long idCarrera = JsonUtils.obtLong(params, "idCarrera");
            Long idSemestre = JsonUtils.obtLong(params, "idSemestre");

            if (idCarrera == null || idSemestre == null) {
                throw new IllegalArgumentException("Los campos idCarrera y idSemestre son obligatorios");
            }

            // Obtener carrera y semestre
            CatCarreraModel carrera = catCarreraRepository.findById(idCarrera)
                    .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada con ID: " + idCarrera));
            CatSemestreModel semestre = catSemestreRepository.findById(idSemestre)
                    .orElseThrow(() -> new IllegalArgumentException("Semestre no encontrado con ID: " + idSemestre));

            // Establecer las relaciones
            catGrupoModel.setCatCarreraModel(carrera);
            catGrupoModel.setCatSemestreModel(semestre);

          
            // Establecer el nombre del grupo en el modelo
            catGrupoModel.setNombreGrupo("");

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el grupo");
        }
        return catGrupoModel;
    }

    @Override
    public CatGrupoModel updateInstance(CatGrupoModel catGrupoInstance) throws Exception {
        CatGrupoModel catGrupoBD = this.findById(catGrupoInstance.getId());
        catGrupoBD.setNombreGrupo(catGrupoInstance.getNombreGrupo());
        catGrupoBD.setCatCarreraModel(catGrupoInstance.getCatCarreraModel());
        catGrupoBD.setCatSemestreModel(catGrupoInstance.getCatSemestreModel());
        return this.save(catGrupoBD);
    }

    @Override
    public void deleteById(Long id) {
        CatGrupoModel catGrupoModel = this.findById(id);
        if (catGrupoModel != null) {
            catGrupoRepository.deleteById(id);
        }
    }

   

    @Override
public String obtenerNombreGrupo(Long idCarrera, Long idSemestre) {
    CatGrupoModel grupo = catGrupoRepository.findByCatCarreraModel_IdAndCatSemestreModel_Id(idCarrera, idSemestre);

    if (grupo != null) {
        return grupo.getNombreGrupo(); // Ejemplo: "107"
    } else {
        throw new IllegalArgumentException("No existe un grupo con esa carrera y semestre.");
    }
}

}
