package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.model.CatCarrera;
import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradas;
import com.UNSIJ.INESIS_BACKEND.model.Usuario;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IAlumnoService;
import com.UNSIJ.INESIS_BACKEND.utils.ArchivoUtil;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceJPA implements IAlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CatCarreraServiceJPA carreraServiceJPA;

    @Autowired
    private CatSemestreServiceJPA semestreServiceJPA;

    @Autowired
    private CatSexoServiceJPA sexoServiceJPA;

    @Autowired
    private CatGrupoServiceJPA grupoServiceJPA;

    @Autowired
    private UsuarioServiceJPA usuarioServiceJPA;

    @Autowired
    FechasRegistradasServiceJPA fechasRegistradasServiceJPA;

    @Override
    public List<Alumno> findAll() {
        List<Alumno> alumnos = alumnoRepository.findAll();

        Set<Long> carreraIds = alumnos.stream()
                .map(a -> a.getCarrera() != null ? a.getCarrera().getId() : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (!carreraIds.isEmpty()) {
            List<FechasRegistradas> fechas = fechasRegistradasServiceJPA.findByCarreraIds(carreraIds);
            Map<Long, FechasRegistradas> mapaFechas = fechas.stream()
                    .collect(Collectors.toMap(f -> f.getCarrera().getId(), f -> f));
            alumnos.forEach(a -> {
                if (a.getCarrera() != null) {
                    a.setFechaRegistrada(mapaFechas.get(a.getCarrera().getId()));
                }
            });
        }

        return alumnos;
    }

    @Override
    public Alumno findById(Long id) {
        Alumno alumno = null;
        try {
            alumno = alumnoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con el ID: " + id));
            verificarFechas(alumno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) throws Exception {
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional
    public Alumno create(Map<String, Object> params) throws Exception {
        System.out.println("Creando alumno con los siguientes parámetros: " + params);
        Alumno alumno = new Alumno();
        alumno.setEstadoRevision(0);
        try {
            this.build(params, alumno);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al crear el alumno");
        }
        return this.save(alumno);
    }

    @Override
    @Transactional
    public Alumno update(Alumno alumno, Map<String, Object> params) throws Exception {
        try {
            this.build(params, alumno);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al actualizar el alumno");
        }
        return save(alumno); // Guardar los cambios en el alumno
    }

    @Transactional
    @Override
    public Alumno build(Map<String, Object> params, Alumno alumno) {
        try {
            // Asignar campos del alumno
            alumno.setNombre(JsonUtils.obtString(params, "nombre"));
            alumno.setApellidoPaterno(JsonUtils.obtString(params, "apellidoPaterno"));
            alumno.setApellidoMaterno(JsonUtils.obtString(params, "apellidoMaterno"));
            alumno.setCurp(JsonUtils.obtString(params, "curp"));
            alumno.setCorreo(JsonUtils.obtString(params, "correo"));
            alumno.setTelefono(JsonUtils.obtString(params, "telefono"));
            alumno.setMatricula(JsonUtils.obtString(params, "matricula"));

            Long idGrupo = JsonUtils.obtLong(params, "grupo");
            alumno.setGrupo(grupoServiceJPA.findById(idGrupo));

            Long idCarrera = JsonUtils.obtLong(params, "carrera");
            alumno.setCarrera(carreraServiceJPA.findById(idCarrera));

            Long idSemestre = JsonUtils.obtLong(params, "semestre");
            alumno.setSemestre(semestreServiceJPA.findById(idSemestre));

            Long idSexo = JsonUtils.obtLong(params, "sexo");
            alumno.setSexo(sexoServiceJPA.findById(idSexo));

            // Guardar los datos del alumno
            alumno = save(alumno);

            // Verificar si el alumno ya tiene un usuario
            if (alumno.getUsuario() != null) {
                // Si ya existe un usuario, actualizamos su información
                String usuario = JsonUtils.obtString(params, "usuario");
                String contrasena = JsonUtils.obtString(params, "contrasenia");
                System.out.println("usuario: " + usuario + ", contrasena: " + contrasena);
                if(usuario != null && contrasena != null) {
                    Map<String, Object> usuarioParams = new HashMap<>();
                    usuarioParams.put("usuario", usuario);
                    usuarioParams.put("contrasenia", contrasena);
                    usuarioParams.put("estatus", params.getOrDefault("estatus", "Activo"));

                    Long idRol = params.get("idCatRol") != null ? Long.parseLong(params.get("idCatRol").toString()) : 1L; // Valor
                                                                                                                          // predeterminado
                    Map<String, Object> rolMap = new HashMap<>();
                    rolMap.put("idCatRol", idRol);
                    usuarioParams.put("rol", rolMap);

                    // Actualizar el usuario existente
                    usuarioServiceJPA.update(alumno.getUsuario(), usuarioParams);
                }

            } else {
                // Si no existe un usuario, creamos uno nuevo
                Usuario usuario = usuarioServiceJPA.crearDesdeAlumno(alumno);
                alumno.setUsuario(usuario);
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el alumno");
        }
        return alumno;
    }



    @Override
    public void deleteById(Long id) {
        Alumno alumno = this.findById(id);
        if (alumno != null) {
            alumnoRepository.deleteById(id);
        }
    }

    @Transactional
    public List<Alumno> saveAll(List<Alumno> alumnos) {
        return alumnoRepository.saveAll(alumnos);
    }

    private void verificarFechas(Alumno alumno) {
        if (alumno.getCarrera() == null) return;
        try {
            Optional<FechasRegistradas> opt = fechasRegistradasServiceJPA.findOptionalByCarreraId(alumno.getCarrera().getId());
            opt.ifPresent(alumno::setFechaRegistrada);
        } catch (Exception e) {
            //logger.error("Error al obtener fechas registradas para carrera {}: {}", alumno.getCarrera().getId(), e.getMessage(), e);
        }
    }

    public boolean checkIfExists(String curp, String matricula, String correo) {
        return alumnoRepository.existsByCurp(curp) ||
                alumnoRepository.existsByMatricula(matricula) ||
                alumnoRepository.existsByCorreo(correo);
    }

    @Transactional
    public List<Alumno> importarDesdeExcel(MultipartFile file) throws Exception {
        List<Alumno> alumnosImportados = new ArrayList<>();
        int filasASaltar = 5;
        int filaActual = 0;

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            // Omitir las 5 primeras filas de encabezados
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext() && filaActual < filasASaltar) {
                rowIterator.next();
                filaActual++;
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Validar que la fila no esté vacía
                if (ArchivoUtil.isEmptyRow(row))
                    continue;

                try {
                    // Crear un mapa con los datos de la fila
                    Map<String, Object> alumnoData = new HashMap<>();

                    // CAMPOS OBLIGATORIOS
                    alumnoData.put("nombre", ArchivoUtil.getCellValueAsString(row.getCell(0)));
                    alumnoData.put("apellidoPaterno", ArchivoUtil.getCellValueAsString(row.getCell(1)));
                    alumnoData.put("curp", ArchivoUtil.getCellValueAsString(row.getCell(3)));
                    String sexoTexto = ArchivoUtil.getCellValueAsString(row.getCell(4));
                    // buscar id grupo
                    String nombreGrupo = ArchivoUtil.getCellValueAsString(row.getCell(5));
                    Long idGrupo = grupoServiceJPA.findIdByNombreGrupo(nombreGrupo);
                    alumnoData.put("grupo", idGrupo);
                    // Buscar id carrera
                    Long idCarrera = grupoServiceJPA.findIdCarreraByGrupo(nombreGrupo);
                    alumnoData.put("carrera", idCarrera);
                    // extraer sexo
                    if (sexoTexto.equals("F") || sexoTexto.equals("f")) {
                        alumnoData.put("sexo", 2); // Femenino
                    } else if (sexoTexto.equals("M") || sexoTexto.equals("m")) {
                        alumnoData.put("sexo", 1); // Masculino
                    } else {
                        throw new IllegalArgumentException("Sexo no válido en la fila " + row.getRowNum());
                    }
                    Long idSemestre = grupoServiceJPA.findIdSemestreByGrupo(nombreGrupo);
                    alumnoData.put("semestre", idSemestre);
                    alumnoData.put("matricula", ArchivoUtil.getCellValueAsString(row.getCell(6)));

                    // CAMPOS OPCIONALES
                    alumnoData.put("apellidoMaterno", ArchivoUtil.getCellValueAsString(row.getCell(2)));

                    // campos not null no incluidos
                    alumnoData.put("correo", " ");
                    alumnoData.put("telefono", " ");

                    // Crear el alumno
                    Alumno alumno = create(alumnoData);
                    alumnosImportados.add(alumno);

                } catch (IllegalArgumentException e) {
                    // Opcionalmente, puedes manejar los errores por fila o acumularlos
                    throw new IllegalArgumentException("Error en la fila " + row.getRowNum() + ": " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace(); // Esto es opcional, sirve para depuración si ocurre algún error inesperado
                    throw new IllegalArgumentException(
                            "Error al importar el alumno en la fila " + row.getRowNum() + ": " + e.getMessage());
                }
            }
        }

        return alumnosImportados;
    }

    @Transactional
    public void actualizarMatricula(Long id, String nuevaMatricula) throws Exception {
        try {
            if (alumnoRepository.existsByMatricula(nuevaMatricula)) {
                throw new IllegalArgumentException("La matrícula ya está en uso: " + nuevaMatricula);
            }

            String matriculaTrim = nuevaMatricula.replaceAll("\\D", "");
            System.out.println("Validando matrícula: " + matriculaTrim);
            if (!matriculaTrim.matches("\\d{10}")) {
                throw new IllegalArgumentException("La matricula debe tener exactamente 10 dígitos numéricos");
            }
            Alumno alumno = this.findById(id);
            alumno.setMatricula(matriculaTrim);
            alumno.setMatriculaEditada(true);
            this.save(alumno);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al actualizar el alumno");
        }
    }

    public List<Alumno> findByCarreraId(Long id) {
        return alumnoRepository.findByCarrera_Id(id);
    }

    public void cambiarEstadoRevision(Alumno alumno,Map<String, Object> datos) throws Exception {
        try {
            String observaciones = JsonUtils.obtString(datos, "observaciones");
            Integer estado = JsonUtils.obtInteger(datos, "estado");
            alumno.setObservaciones(observaciones);
            alumno.setEstadoRevision(estado);
            if(estado != null && estado == 4 ) alumno.setObservaciones("");

            this.save(alumno);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al cambiar el estado de revisión del alumno");
        }
    }

    public void reiniciarProceso(FechasRegistradas fechasRegistradas) throws Exception {
        CatCarrera carrera = fechasRegistradas.getCarrera();
        try {
            List<Alumno> alumnos = this.findByCarreraId(carrera.getId());
            List<Alumno> alumnosModificados = new ArrayList<>();
            for (Alumno alumno : alumnos) {
                alumno.setEstudioCompleto(false);
                alumno.setEstadoRevision(0);
                alumno.getMisDatos().setModuloCompleto(false);
                alumno.getMiTutor().setModuloCompleto(false);
                alumno.getGastosIngresosFamiliares().setModuloCompleto(false);
                alumno.getMiFamilia().setModuloCompleto(false);
                alumnosModificados.add(alumno);
            }
            this.saveAll(alumnosModificados);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Opcional, ayuda en depuración si ocurre algún error inesperado
            throw new IllegalArgumentException("Error al construir la fecha registrada");
        }
    }
}