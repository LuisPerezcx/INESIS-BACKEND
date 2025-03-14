package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.repository.EjemploRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IEjemploService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class EjemploServiceJPA implements IEjemploService {
    @Autowired
    private EjemploRepository ejemploRepository;

    @Override
    public List<Ejemplo> findAll() {
        return ejemploRepository.findAll();
    }

    @Override
    public Ejemplo findById(Long id) {
        return ejemploRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public Ejemplo save(Ejemplo ejemplo) throws Exception {
        return ejemploRepository.save(ejemplo);
    }


    @Override
    public Ejemplo create(Map<String, Object> params) throws Exception {
        Ejemplo ejemplo = new Ejemplo();
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

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public Ejemplo update(Ejemplo ejemplo, Map<String, Object> params) throws Exception {
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
    public Ejemplo build(Map<String, Object> params, Ejemplo ejemplo){
        try {
            //PRIMERO DEBEMOS VERIFICAR QUE LOS VALORES QUE SON NOT NULL EN LA BASE EXISTAN EN EL JSON
            //AQUI SE DEBEN VALIDAR LOS DATOS QUE SE ESTAN RECIBIENDO Y SE LANZAN LAS EXCEPCIONES CORRESPONDIENTES
            //SI TOD0 ESTA BIEN, SE LLENAN LOS CAMPOS DE LA CLASE CON LOS VALORES DEL JSON
            //POR EJEMLO EL CAMPO NUMERO ES OBLIGATORIO
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return ejemplo;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public Ejemplo updateInstance(Ejemplo ejemploInstance) throws Exception {
        Ejemplo ejemploBD = this.findById(ejemploInstance.getId());
        ejemploBD.setActive(ejemploInstance.isActive());
        ejemploBD.setNumeroEjemplo(ejemploInstance.getNumeroEjemplo());
        ejemploBD.setNombreEjemplo(ejemploInstance.getNombreEjemplo());
        return this.save(ejemploBD);
    }

    @Override
    public void deleteById(Long id) {
        Ejemplo ejemplo = this.findById(id);
        if (ejemplo!= null){
            ejemploRepository.deleteById(id);
        }
    }
}
