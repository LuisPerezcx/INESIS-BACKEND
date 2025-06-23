package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatParentesco;
import com.UNSIJ.INESIS_BACKEND.repository.CatParentescoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.IngresoFamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.model.IngresoFamiliarModel;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IIngresoFamiliar;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class IngresoFamiliarJPA implements IIngresoFamiliar {

    @Autowired
    IngresoFamiliarRepository ingresoFamiliarRepository;

    @Autowired
    CatParentescoServiceJPA parentescoServiceJPA;

    @Override
    public List<IngresoFamiliarModel> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public IngresoFamiliarModel findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public IngresoFamiliarModel save(IngresoFamiliarModel IngresoFamiliarModel) throws Exception {
        return ingresoFamiliarRepository.save(IngresoFamiliarModel);
    }

    @Override
    public IngresoFamiliarModel create(Map<String, Object> params) throws Exception {
        IngresoFamiliarModel ejemplo = new IngresoFamiliarModel();
        try {
            this.build(params, ejemplo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(ejemplo);
    }

    @Override
    public IngresoFamiliarModel update(IngresoFamiliarModel IngresoFamiliarModel, Map<String, Object> params)
            throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public IngresoFamiliarModel build(Map<String, Object> params, IngresoFamiliarModel IngresoFamiliarModel) {
        try {
            String nombrePersona = JsonUtils.obtString(params, "name");
            Double ingresoBruto = JsonUtils.obtDouble(params, "gross");
            Double ingresoNeto = JsonUtils.obtDouble(params, "net");
            String lugarTrabajo = JsonUtils.obtString(params, "company");
            String puestoTrabajo = JsonUtils.obtString(params, "job");
            Long idParentescoStr = JsonUtils.obtLong(params, "relationship");

            if (nombrePersona == null) throw new IllegalArgumentException("El campo 'name' es obligatorio");
            if (ingresoBruto == null) throw new IllegalArgumentException("El campo 'gross' es obligatorio");
            if (ingresoNeto == null) throw new IllegalArgumentException("El campo 'net' es obligatorio");
            if (idParentescoStr == null) throw new IllegalArgumentException("El campo 'relationship' es obligatorio");

            IngresoFamiliarModel.setNombrePersona(nombrePersona);
            IngresoFamiliarModel.setIngresoBruto(ingresoBruto);
            IngresoFamiliarModel.setIngresoNeto(ingresoNeto);
            IngresoFamiliarModel.setLugarTrabajo(lugarTrabajo);
            IngresoFamiliarModel.setPuestoTrabajo(puestoTrabajo);

            CatParentesco parentescoModel = parentescoServiceJPA.findById(idParentescoStr);
            IngresoFamiliarModel.setParentesco(parentescoModel);


        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el ingreso familiar");
        }
        return IngresoFamiliarModel;
    }

    @Override
    public IngresoFamiliarModel updateInstance(IngresoFamiliarModel IngresoFamiliarModel) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInstance'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
