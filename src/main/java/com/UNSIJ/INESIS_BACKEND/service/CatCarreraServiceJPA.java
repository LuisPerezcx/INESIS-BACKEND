package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.CatCarreraModel;
import com.UNSIJ.INESIS_BACKEND.repository.CatCarreraRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatCarreraService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class CatCarreraServiceJPA implements ICatCarreraService{
    
    @Autowired
    private CatCarreraRepository catCarreraRepository;

    @Override
    public List<CatCarreraModel> findAll() {
        return catCarreraRepository.findAll();
    }

    @Override
    public CatCarreraModel findById(Long id) {
        return catCarreraRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional 
    public CatCarreraModel save(CatCarreraModel catCarreraModel) throws Exception {
        return catCarreraRepository.save(catCarreraModel);
    }

    @Override
    public CatCarreraModel create(Map<String, Object> params) throws Exception {
        CatCarreraModel catCarreraModel = new CatCarreraModel();
        try {
            //AQUI ASIGNAMOS VALORES QUE SOLO SE NECESITAN AL CREAR POR PRIMERA VEZ UN REGISTRO
            //POR EJEMPLO EL CAMPO ACTIVO
        
            //AHORA LLAMAMOS AL METODO QUE SE OCUPA DE CONSTRUIR EL OBJETO
            this.build(params, catCarreraModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catCarreraModel);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public CatCarreraModel update(CatCarreraModel catCarreraModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catCarreraModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catCarreraModel);
    }

    @Override
    public CatCarreraModel build(Map<String, Object> params, CatCarreraModel catCarreraModel){
        try {
            //PRIMERO DEBEMOS VERIFICAR QUE LOS VALORES QUE SON NOT NULL EN LA BASE EXISTAN EN EL JSON
            //AQUI SE DEBEN VALIDAR LOS DATOS QUE SE ESTAN RECIBIENDO Y SE LANZAN LAS EXCEPCIONES CORRESPONDIENTES
            //SI TOD0 ESTA BIEN, SE LLENAN LOS CAMPOS DE LA CLASE CON LOS VALORES DEL JSON
            //POR EJEMLO EL CAMPO NUMERO ES OBLIGATORIO

            //JsonUtils recibe el mapa y el nombre del parametro a extraer
            Integer numero = JsonUtils.obtInteger(params,"numeroEjemplo");
            //VERIFICACION DEL CAMPO NUMERO
            if(numero == null) throw new IllegalArgumentException("El campo numero es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            /*catCarreraModel.setNumeroEjemplo(numero);
            catCarreraModel.setNombreEjemplo(JsonUtils.obtString(params,"nombreEjemplo")); //TAMBIEN SE PUEDE HACER DE ESTA FORMA DIRECTA
            *///... Y SE REPITIRÁ ESTA SECCIÓN PARA CADA CAMPO EN EL JSON
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return catCarreraModel;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public CatCarreraModel updateInstance(CatCarreraModel catCarreraInstance) throws Exception {
        CatCarreraModel catCarreraBD = this.findById(catCarreraInstance.getId());
       /*  catCarreraBD.setActive(catCarreraInstance.isActive());
        catCarreraBD.setNumeroEjemplo(catCarreraInstance.getNumeroEjemplo());
        catCarreraBD.setNombreEjemplo(catCarreraInstance.getNombreEjemplo());*/
        return this.save(catCarreraBD);
    }

    @Override
    public void deleteById(Long id) {
        CatCarreraModel catCarreraModel = this.findById(id);
        if (catCarreraModel!= null){
            catCarreraRepository.deleteById(id);
        }
    }
}
