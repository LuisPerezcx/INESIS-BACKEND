package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
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
        for (Alumno alumno : alumnos) {
            if (alumno.getCarrera() != null) {
                try {
                    alumno.setFechaRegistrada(fechasRegistradasServiceJPA.findByCarreraId(alumno.getCarrera().getId()));
                } catch (Exception e) {
                }
            }
        }
        return alumnos;
    }

    @Override
    public Alumno findById(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con el ID: " + id));
        try {
            alumno.setFechaRegistrada(fechasRegistradasServiceJPA.findByCarreraId(alumno.getCarrera().getId()));
        } catch (Exception e) {
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
                Map<String, Object> usuarioParams = new HashMap<>();
                usuarioParams.put("usuario", JsonUtils.obtString(params, "usuario"));
                usuarioParams.put("contrasenia", JsonUtils.obtString(params, "contrasenia"));
                usuarioParams.put("estatus", params.getOrDefault("estatus", "Activo"));

                Long idRol = params.get("idCatRol") != null ? Long.parseLong(params.get("idCatRol").toString()) : 1L; // Valor
                                                                                                                      // predeterminado
                Map<String, Object> rolMap = new HashMap<>();
                rolMap.put("idCatRol", idRol);
                usuarioParams.put("rol", rolMap);

                // Actualizar el usuario existente
                usuarioServiceJPA.update(alumno.getUsuario(), usuarioParams);

            } else {
                // Si no existe un usuario, creamos uno nuevo
                Usuario usuario = usuarioServiceJPA.crearDesdeAlumno(alumno);
                alumno.setUsuario(usuario);
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el alumno");
        }
        return alumno;
    }

    @Transactional
    public void cambiarPasswordAlumno(Long idAlumno, String rawPassword) throws Exception {
        Alumno alumno = findById(idAlumno);
        if (alumno.getUsuario() == null) {
            throw new IllegalArgumentException("El alumno no tiene usuario asignado");
        }
        usuarioServiceJPA.actualizarPassword(alumno.getUsuario().getId(), rawPassword);
    }

    @Override
    public void deleteById(Long id) {
        Alumno alumno = this.findById(id);
        if (alumno != null) {
            alumnoRepository.deleteById(id);
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
}