package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatSexo;
import com.UNSIJ.INESIS_BACKEND.repository.CatSexoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatSexoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CatSexoServiceJPA implements ICatSexoService {

    @Autowired
    private CatSexoRepository catSexoRepository;

    @Override
    public List<CatSexo> findAll() {
        return catSexoRepository.findAll();
    }

    @Override
    public CatSexo findById(Long id) {
        return catSexoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Sexo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public CatSexo save(CatSexo catSexo) throws Exception {
        return catSexoRepository.save(catSexo);
    }

    @Override
    public CatSexo create(Map<String, Object> params) throws Exception {
        CatSexo catSexo = new CatSexo();
        try {
            this.build(params, catSexo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el sexo");
        }
        return this.save(catSexo);
    }

    @Override
    public CatSexo update(CatSexo catSexo, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catSexo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el sexo");
        }
        return this.save(catSexo);
    }

    @Override
    public CatSexo build(Map<String, Object> params, CatSexo catSexo) {
        try {
            String nombre = JsonUtils.obtString(params, "nombreSexo");
            if (nombre == null || nombre.isEmpty())
                throw new IllegalArgumentException("El campo nombre es obligatorio");
            catSexo.setNombreSexo(nombre);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el sexo");
        }
        return catSexo;
    }

    @Override
    public CatSexo updateInstance(CatSexo catSexoInstance) throws Exception {
        CatSexo catSexoBD = this.findById(catSexoInstance.getId());
        catSexoBD.setNombreSexo(catSexoInstance.getNombreSexo());
        return this.save(catSexoBD);
    }

    @Override
    public void deleteById(Long id) {
        CatSexo catSexo = this.findById(id);
        if (catSexo != null) {
            catSexoRepository.deleteById(id);
        }
    }
}
