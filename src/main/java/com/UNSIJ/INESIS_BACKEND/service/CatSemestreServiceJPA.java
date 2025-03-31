package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.CatSemestreModel;
import com.UNSIJ.INESIS_BACKEND.repository.CatSemestreRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatSemestreService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

public class CatSemestreServiceJPA implements ICatSemestreService{
    
    
    @Autowired
    private CatSemestreRepository catSemestreRepository;

    @Override
    public List<CatSemestreModel> findAll() {
        return catSemestreRepository.findAll();
    }

    @Override
    public CatSemestreModel findById(Long id) {
        return catSemestreRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional 
    public CatSemestreModel save(CatSemestreModel catSemestreModel) throws Exception {
        return catSemestreRepository.save(catSemestreModel);
    }

    @Override
    public CatSemestreModel create(Map<String, Object> params) throws Exception {
        CatSemestreModel catSemestreModel = new CatSemestreModel();
        try {
            //AQUI ASIGNAMOS VALORES QUE SOLO SE NECESITAN AL CREAR POR PRIMERA VEZ UN REGISTRO
            //POR EJEMPLO EL CAMPO ACTIVO
        
            //AHORA LLAMAMOS AL METODO QUE SE OCUPA DE CONSTRUIR EL OBJETO
            this.build(params, catSemestreModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catSemestreModel);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public CatSemestreModel update(CatSemestreModel catSemestreModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catSemestreModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catSemestreModel);
    }

    @Override
    public CatSemestreModel build(Map<String, Object> params, CatSemestreModel catSemestreModel){
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
        return catSemestreModel;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public CatSemestreModel updateInstance(CatSemestreModel catSemestreInstance) throws Exception {
        CatSemestreModel catSemestreBD = this.findById(catSemestreInstance.getId());
       /*  catCarreraBD.setActive(catCarreraInstance.isActive());
        catCarreraBD.setNumeroEjemplo(catCarreraInstance.getNumeroEjemplo());
        catCarreraBD.setNombreEjemplo(catCarreraInstance.getNombreEjemplo());*/
        return this.save(catSemestreBD);
    }

    @Override
    public void deleteById(Long id) {
        CatSemestreModel catSemestreModel = this.findById(id);
        if (catSemestreModel!= null){
            catSemestreRepository.deleteById(id);
        }
    }
}
