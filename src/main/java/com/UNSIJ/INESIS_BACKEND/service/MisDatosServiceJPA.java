package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.MisDatos;
import com.UNSIJ.INESIS_BACKEND.repository.MisDatosRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMisDatosService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

public class MisDatosServiceJPA implements IMisDatosService{
    @Autowired
    private MisDatosRepository misDatosRepository;

    @Override
    public List<MisDatos> findAll() {
        return misDatosRepository.findAll();
    }

    @Override
    public MisDatos findById(Long id) {
        return misDatosRepository.findById(id).orElseThrow( () ->
            new IllegalArgumentException("MisDatos no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public MisDatos save(MisDatos misDatos) throws Exception {
        return misDatosRepository.save(misDatos);
    }

    @Override
    public MisDatos create(Map<String, Object> params) throws Exception {
        MisDatos misDatos = new MisDatos();
        try {
            //AQUI ASIGNAMOS VALORES QUE SOLO SE NECESITAN AL CREAR POR PRIMERA VEZ UN REGISTRO
            //POR EJEMPLO EL CAMPO ACTIVO
            misDatos.setCarrera("hola"); //ESTE ES UN CASO DE USO
            //AHORA LLAMAMOS AL METODO QUE SE OCUPA DE CONSTRUIR EL OBJETO
            this.build(params, misDatos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir misDatos");
        }
        return this.save(misDatos);
    }

    @Override
    public MisDatos update(MisDatos misDatos, Map<String, Object> params) throws Exception {
        try {
            this.build(params, misDatos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir a misDatos");
        }
        return this.save(misDatos);
    }

    @Override
    public MisDatos build(Map<String, Object> params, MisDatos misDatos) throws IllegalArgumentException {
        try {
            //PRIMERO DEBEMOS VERIFICAR QUE LOS VALORES QUE SON NOT NULL EN LA BASE EXISTAN EN EL JSON
            //AQUI SE DEBEN VALIDAR LOS DATOS QUE SE ESTAN RECIBIENDO Y SE LANZAN LAS EXCEPCIONES CORRESPONDIENTES
            //SI TOD0 ESTA BIEN, SE LLENAN LOS CAMPOS DE LA CLASE CON LOS VALORES DEL JSON
            //POR EJEMLO EL CAMPO NUMERO ES OBLIGATORIO

            //JsonUtils recibe el mapa y el nombre del parametro a extraer
            Integer numero = JsonUtils.obtInteger(params,"numeroEjemplo");
            //VERIFICACION DEL CAMPO NUMERO
            if(numero == null) throw new IllegalArgumentException("El campo numero es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            misDatos.setCarrera("carreranumero");
            misDatos.setIdioma(JsonUtils.obtString(params,"idioma")); //TAMBIEN SE PUEDE HACER DE ESTA FORMA DIRECTA
            //... Y SE REPITIRÁ ESTA SECCIÓN PARA CADA CAMPO EN EL JSON
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return misDatos;
    }

    @Override
    public MisDatos updateInstance(MisDatos misDatosInstance) throws Exception {
        MisDatos misDatosBD = this.findById(misDatosInstance.getId());
        misDatosBD.setCarrera(misDatosInstance.getCarrera());
        return this.save(misDatosBD);
    }

    @Override
    public void deleteById(Long id) {
        MisDatos misDatos = this.findById(id);
        if (misDatos!= null){
            misDatosRepository.deleteById(id);
        }
    }
    
}
