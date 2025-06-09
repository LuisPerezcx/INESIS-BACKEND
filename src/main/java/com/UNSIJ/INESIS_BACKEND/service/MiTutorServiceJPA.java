package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.*;
import com.UNSIJ.INESIS_BACKEND.repository.CatParentescoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatTipoTrabajoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.MiTutorRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMiTutorService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MiTutorServiceJPA implements IMiTutorService {
    @Autowired
    private MiTutorRepository miTutorRepository;

    @Autowired
    private DomicilioServiceJPA domicilioServiceJPA;

    @Autowired
    private CatParentescoRepository catParentescoRepository;

    @Autowired
    private CatTipoTrabajoRepository catTipoTrabajoRepository;

    @Override
    public List<MiTutor> findAll() {
        return miTutorRepository.findAll();
    }

    @Override
    public MiTutor findById(Long id) {
        return miTutorRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public MiTutor save(MiTutor miTutor) throws Exception {
        return miTutorRepository.save(miTutor);
    }

    @Override
    public MiTutor create(Map<String, Object> params) throws Exception {
        MiTutor miTutor = new MiTutor();
        try {
            this.build(params, miTutor);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        miTutor.setModuloCompleto(true);
        return this.save(miTutor);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public MiTutor update(MiTutor miTutor, Map<String, Object> params) throws Exception {
        try {
            this.build(params, miTutor);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(miTutor);
    }

    @Override
    public MiTutor build(Map<String, Object> params, MiTutor miTutor){
        try {
            String nombreTutor = JsonUtils.obtString(params,"nombreTutor");
            if(nombreTutor == null) throw new IllegalArgumentException("El campo nombre completo es obligatorio");
            miTutor.setNombreTutor(nombreTutor);

            Long idCatParentesco = JsonUtils.obtLong(params, "parentesco");
            if (idCatParentesco == null) {
                throw new IllegalArgumentException("El campo 'idCatParentesco' es obligatorio.");
            }
            CatParentesco catParentesco = catParentescoRepository.findById(idCatParentesco)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Tipo de transporte no encontrado con el ID: " + idCatParentesco));
            miTutor.setParentesco(catParentesco);

            String telefono = JsonUtils.obtString(params,"telefono");
            if(telefono == null) throw new IllegalArgumentException("El campo telefono es obligatorio");
            miTutor.setTelefono(telefono);

            String correo = JsonUtils.obtString(params,"correo");
            if(correo == null) throw new IllegalArgumentException("El campo correo es obligatorio");
            miTutor.setCorreo(correo);

            String trabajadorSuneoString = JsonUtils.obtString(params,"trabajadorSuneo");
            Boolean trabajadorSuneo = JsonUtils.obtBoolean(params,"trabajadorSuneo");
            if(trabajadorSuneoString != null){
                if("Si".equalsIgnoreCase(trabajadorSuneoString)){
                    trabajadorSuneo = true;
                } else if("No".equalsIgnoreCase(trabajadorSuneoString)) {
                    trabajadorSuneo = false;
                } else {
                    throw new IllegalArgumentException("El valor de trabajador suneo debe ser Si o No");
                }
            }
            if(trabajadorSuneo == null)
                throw new IllegalArgumentException("El campo trabajador suneo es obligatorio");
            miTutor.setTrabajadorSuneo(trabajadorSuneo);

            String comparteViviendaString = JsonUtils.obtString(params,"comparteVivienda");
            Boolean comparteVivienda = JsonUtils.obtBoolean(params,"comparteVivienda");
            if(comparteViviendaString != null){
                if("Si".equalsIgnoreCase(comparteViviendaString)){
                    comparteVivienda = true;
                } else if("No".equalsIgnoreCase(comparteViviendaString)) {
                    comparteVivienda = false;
                } else {
                    throw new IllegalArgumentException("El valor de comparte vivienda debe ser Si o No");
                }
            }
            if(comparteVivienda == null)
                throw new IllegalArgumentException("El campo comparte vivienda es obligatorio");
            miTutor.setTrabajadorSuneo(trabajadorSuneo);

            Long idTrabajoTipo = JsonUtils.obtLong(params, "trabajoTipo");
            if (idTrabajoTipo == null) throw new IllegalArgumentException("El campo 'idTrabajoTipo' es obligatorio.");
            CatTipoTrabajo catTipoTrabajo = catTipoTrabajoRepository.findById(idTrabajoTipo)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Tipo trabajo no encontrado con el ID: " + idTrabajoTipo));
            miTutor.setCatTipoTrabajo(catTipoTrabajo);

            String ocupacionOtro = JsonUtils.obtString(params,"ocupacionOtro");
            miTutor.setOcupacionOtro(ocupacionOtro);

            miTutor = this.save(miTutor);
            Map<String, Object> domicilioParams = (Map<String, Object>) params.get("domicilio");
            if (domicilioParams != null) {
                Domicilio domicilio = domicilioServiceJPA.create(domicilioParams);
                miTutor.setDomicilio(domicilio);
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return miTutor;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public MiTutor updateInstance(MiTutor miTutorInstance) throws Exception {
        MiTutor miTutorBD = this.findById(miTutorInstance.getIdTutor());

        return this.save(miTutorBD);
    }

    @Override
    public void deleteById(Long id) {
        MiTutor miTutor = this.findById(id);
        if (miTutor != null){
            miTutorRepository.deleteById(id);
        }
    }
}
