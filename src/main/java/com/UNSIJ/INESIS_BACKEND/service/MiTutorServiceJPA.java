package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.*;
import com.UNSIJ.INESIS_BACKEND.repository.CatParentescoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatTipoTrabajoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.MiTutorRepository;
import com.UNSIJ.INESIS_BACKEND.repository.OcupacionRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMiTutorService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OcupacionRepository ocupacionRepository;

    @Autowired
    AlumnoServiceJPA alumnoServiceJPA;

    @Autowired
    FechasRegistradasServiceJPA fechasRegistradasServiceJPA;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MiTutor> findAll() {
        return miTutorRepository.findAll();
    }

    @Override
    public MiTutor findById(Long id) {
        return miTutorRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public MiTutor save(MiTutor miTutor) throws Exception {
        Alumno alumno = miTutor.getAlumno();
        if(!fechasRegistradasServiceJPA.permitirRegistro(alumno.getCarrera().getId()))
            throw new IllegalArgumentException("No es posible registrar tus datos en este momento. " +
                    "El periodo de registro para tu carrera no está activo actualmente.");
        return miTutorRepository.save(miTutor);
    }

    @Override
    @Transactional
    public MiTutor create(Map<String, Object> params) throws Exception {
        MiTutor miTutor = new MiTutor();
        try {
            Long idAlumno = JsonUtils.obtLong(params, "alumnoId");
            if (idAlumno == null) throw new IllegalArgumentException("El campo 'idAlumno' es obligatorio.");
            Alumno alumno = alumnoServiceJPA.findById(idAlumno);
            miTutor.setAlumno(alumno);
            this.build(params, miTutor);
            miTutor.setModuloCompleto(true);
            miTutor = this.save(miTutor);
            alumno.setMiTutor(miTutor);
            alumnoServiceJPA.save(alumno);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return miTutor;
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    @Transactional
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
    @Transactional
    public MiTutor build(Map<String, Object> params, MiTutor miTutor) {
        try {
            String nombreTutor = JsonUtils.obtString(params, "nombreTutor");
            if (nombreTutor == null) throw new IllegalArgumentException("El campo nombre completo es obligatorio");
            miTutor.setNombreTutor(nombreTutor);

            Long idCatParentesco = JsonUtils.obtLong(params, "parentesco");
            if (idCatParentesco == null) {
                throw new IllegalArgumentException("El campo 'idCatParentesco' es obligatorio.");
            }
            CatParentesco catParentesco = catParentescoRepository.findById(idCatParentesco)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Tipo de transporte no encontrado con el ID: " + idCatParentesco));
            miTutor.setParentesco(catParentesco);

            String telefono = JsonUtils.obtString(params, "telefono");
            if (telefono == null) throw new IllegalArgumentException("El campo telefono es obligatorio");
            miTutor.setTelefono(telefono);

            String correo = JsonUtils.obtString(params, "correo");
            if (correo == null) throw new IllegalArgumentException("El campo correo es obligatorio");
            miTutor.setCorreo(correo);

            String trabajadorSuneoString = JsonUtils.obtString(params, "trabajadorSuneo");
            Boolean trabajadorSuneo = JsonUtils.obtBoolean(params, "trabajadorSuneo");
            if (trabajadorSuneoString != null) {
                if ("Si".equalsIgnoreCase(trabajadorSuneoString)) {
                    trabajadorSuneo = true;
                } else if ("No".equalsIgnoreCase(trabajadorSuneoString)) {
                    trabajadorSuneo = false;
                } else {
                    throw new IllegalArgumentException("El valor de trabajador suneo debe ser Si o No");
                }
            }
            if (trabajadorSuneo == null)
                throw new IllegalArgumentException("El campo trabajador suneo es obligatorio");
            miTutor.setTrabajadorSuneo(trabajadorSuneo);

            miTutor.setComparteVivienda(JsonUtils.parseBooleanFlexible(params.get("comparteVivienda"), "comparteVivienda"));

            Long idTrabajoTipo = JsonUtils.obtLong(params, "trabajoTipo");
            if (idTrabajoTipo == null) throw new IllegalArgumentException("El campo 'idTrabajoTipo' es obligatorio.");
            CatTipoTrabajo catTipoTrabajo = catTipoTrabajoRepository.findById(idTrabajoTipo)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Tipo trabajo no encontrado con el ID: " + idTrabajoTipo));
            miTutor.setCatTipoTrabajo(catTipoTrabajo);

            Long idOcupacion = JsonUtils.obtLong(params, "ocupacion");
            if (idOcupacion == null) throw new IllegalArgumentException("El campo 'idOcupacion' es obligatorio.");
            Ocupacion ocupacion = ocupacionRepository.findById(idOcupacion)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Ocupacion no encontrado con el ID: " + idOcupacion));
            miTutor.setOcupacion(ocupacion);

            String ocupacionOtro = JsonUtils.obtString(params, "ocupacionOtro");
            miTutor.setOcupacionOtro(ocupacionOtro);

            miTutor = this.save(miTutor);

            Map<String, Object> domicilioParams = (Map<String, Object>) params.get("datosDomicilio");
            if (domicilioParams != null) {
                Long idDomicilio = domicilioParams.get("idDomicilio") != null
                        ? Long.valueOf(domicilioParams.get("idDomicilio").toString())
                        : null;

                if (idDomicilio == null) {
                    // Si se va a crear un nuevo domicilio y ya había uno, lo desvincula
                    Domicilio domicilioAntiguo = miTutor.getDomicilio();
                    if (domicilioAntiguo != null) {
                        miTutor.setDomicilio(null);
                        this.save(miTutor);
                        entityManager.flush(); // Forzar sincronización con la base de datos
                        entityManager.clear();  // Limpia contexto para forzar que la próxima consulta lea desde BD


                        // Verifica si el domicilio ya no está relacionado con ningún otro registro
                        boolean enUso = domicilioServiceJPA.isDomicilioUsado(domicilioAntiguo.getId());

                        if (!domicilioServiceJPA.isDomicilioUsado(domicilioAntiguo.getId())) {
                            domicilioServiceJPA.deleteById(domicilioAntiguo.getId());
                        }
                    }
                    Domicilio nuevo = domicilioServiceJPA.create(domicilioParams);
                    miTutor.setDomicilio(nuevo);
                } else {
                    Domicilio existente = domicilioServiceJPA.findById(idDomicilio);
                    // Si el domicilio existente es el mismo que el del alumno, elimina el anterior
                    Domicilio domicilioAntiguo = miTutor.getDomicilio();
                    if (domicilioAntiguo != null && !domicilioAntiguo.equals(existente)) {
                        miTutor.setDomicilio(null);
                        this.save(miTutor);
                        entityManager.flush();
                        entityManager.clear();

                        boolean enUso = domicilioServiceJPA.isDomicilioUsado(domicilioAntiguo.getId());
                        if (!enUso) {
                            domicilioServiceJPA.deleteById(domicilioAntiguo.getId());
                        }
                    }
                    miTutor.setDomicilio(existente);
                }
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
        if (miTutor != null) {
            miTutorRepository.deleteById(id);
        }
    }
}
