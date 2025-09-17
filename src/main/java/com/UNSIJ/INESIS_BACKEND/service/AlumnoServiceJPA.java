package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.model.CatCarrera;
import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradas;
import com.UNSIJ.INESIS_BACKEND.model.Usuario;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IAlumnoService;
import com.UNSIJ.INESIS_BACKEND.utils.ArchivoUtil;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
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
        int filasASaltar = 6;
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

    public String exportarFinalizados(){
        Workbook workbook = new XSSFWorkbook();

        List<Alumno> alumnos = alumnoRepository.findAll();
        //agrupar alumnos por carrera
        Map<Long, List<Alumno>> alumnosPorCarrera = alumnos.stream()
                .filter(a -> a.getCarrera() != null && a.getEstudioCompleto() != null && a.getEstudioCompleto())
                .collect(Collectors.groupingBy(a -> a.getCarrera().getId()));

        //CREAR UNA HOJA POR CARRERRA
        for (Map.Entry<Long, List<Alumno>> entry : alumnosPorCarrera.entrySet()) {
            Long carreraId = entry.getKey();
            List<Alumno> lista = entry.getValue();
            String nombreCarrera = " ";

            switch (carreraId.intValue()){
                case 1 -> nombreCarrera = "AMBIENTAL";
                case 2 -> nombreCarrera = "FORESTAL";
                case 3 -> nombreCarrera = "INFORMATICA";
                case 4 -> nombreCarrera = "ING SW";
                case 5 -> nombreCarrera = "BIOLOGIA";
                case 6 -> nombreCarrera = "LAT";
                case 7 -> nombreCarrera = "M.C.R.F";
                case 8 -> nombreCarrera = "M.G.A";
            }

            Sheet sheet = workbook.createSheet(nombreCarrera);

            // Encabezados
            Row header = sheet.createRow(0);
            header.setHeightInPoints(100f); // Altura mayor para el encabezado
            header.createCell(0).setCellValue("N/P");
            header.createCell(1).setCellValue("NOMBRE");
            header.createCell(2).setCellValue("CARRERA");
            header.createCell(3).setCellValue("CAMPUS");
            header.createCell(4).setCellValue("SEM");
            header.createCell(5).setCellValue("CALIF");
            header.createCell(6).setCellValue("EXTRAORDINARIOS");
            header.createCell(7).setCellValue("CASO\nESPECIAL (#\nDE MATERIAS\nNO\nACREDITADAS)");
            header.createCell(8).setCellValue("MANUTENCIÓN");
            header.createCell(9).setCellValue("ESTATUS");
            header.createCell(10).setCellValue("OBSERVACIONES\nESTATUS");
            header.createCell(11).setCellValue("ASISTENCIA %");
            header.createCell(12).setCellValue("DEPENDE\nECONOMICAMENTE (1)");
            header.createCell(13).setCellValue("SOLICITA\nBECA\nALIMENTARIA (2)");
            header.createCell(14).setCellValue("GASTOS DE\nMANUTENCIÓN (3)");
            header.createCell(15).setCellValue("VIVIENDA (4)");
            header.createCell(16).setCellValue("NO. DE\nPERSONAS\nCON QUIEN\nCOMPARTE\nCOSTO RENTA\n(5)");
            header.createCell(17).setCellValue("COSTO RENTA (6)");
            header.createCell(18).setCellValue("VEHÍCULO (7)");
            header.createCell(19).setCellValue("INGRESO\nMENSUAL\nBRUTO\nREPORTADO\n(8)");
            header.createCell(20).setCellValue("INGRESO\nMENSUAL\nNETO\nREPORTADO\n(8)");
            header.createCell(21).setCellValue("DIFERENCIA\nENTRE\nINGRESO\nMENSUAL\nBRUTO E\nINGRESO\nMENSUAL\nNETO\nREPORTADO\n(9)");
            header.createCell(22).setCellValue("GASTOS DE\nMANUTENCION\nCOMO % DE\nINGRESO\nNETO\nREPORTADO\n(10)");
            header.createCell(23).setCellValue("GASTOS LUZ\n(11)");
            header.createCell(24).setCellValue("NÚMERO DE\nDEPENDIENTES\nECONÓMICOS\n(12)");
            header.createCell(25).setCellValue("FECHA EN QUE\nENTREGÓ\nSOLICITUD\nDE BECA\n(13)");
            header.createCell(26).setCellValue("HIJO O NIETO\nDE COMUNERO\nDE IXTLÁN\n(14)");
            header.createCell(27).setCellValue("DEPENDIENTE\nDE\nTRABAJADOR\nUNSIJ (15)");
            header.createCell(28).setCellValue("DESCUENTO\nPOR NÚMERO\nDE\nEXTRAORDINARIOS (16)");
            header.createCell(29).setCellValue("PORCENTAJE\nDE BECA\nCOLEGIATURA\nRECOMENDADO\n(17)");
            header.createCell(30).setCellValue("BECA\nALIMENTARIA\nRECOMENDADA\n(18)");
            header.createCell(31).setCellValue("OBSERVACIONES (19)");


            //ESTILOS ENCABEZADO
            CellStyle borderedStyle = workbook.createCellStyle();
            borderedStyle.setBorderTop(BorderStyle.THIN);
            borderedStyle.setBorderBottom(BorderStyle.THIN);
            borderedStyle.setBorderLeft(BorderStyle.THIN);
            borderedStyle.setBorderRight(BorderStyle.THIN);
            borderedStyle.setWrapText(true);

            for (int i = 0; i <= 31; i++) {
                Cell cell = header.getCell(i);
                if (cell != null) {
                    cell.setCellStyle(borderedStyle);
                }
            }


            int rowNum = 1;
            for(Alumno a : lista){
                Row row = sheet.createRow(rowNum++);
                CellStyle borderedStyleData = workbook.createCellStyle();
                borderedStyleData.setBorderTop(BorderStyle.THIN);
                borderedStyleData.setBorderBottom(BorderStyle.THIN);
                borderedStyleData.setBorderLeft(BorderStyle.THIN);
                borderedStyleData.setBorderRight(BorderStyle.THIN);

                Cell cell0 = row.createCell(0); cell0.setCellValue(rowNum - 1); cell0.setCellStyle(borderedStyleData);
                Cell cell1 = row.createCell(1); cell1.setCellValue(a.getNombreCompleto()); cell1.setCellStyle(borderedStyleData);
                Cell cell2 = row.createCell(2); cell2.setCellValue(a.getCarrera() != null ? a.getCarrera().getNombreCarrera() : ""); cell2.setCellStyle(borderedStyleData);
                Cell cell3 = row.createCell(3); cell3.setCellValue("IXTLAN"); cell3.setCellStyle(borderedStyleData);
                Cell cell4 = row.createCell(4); cell4.setCellValue(a.getSemestre() != null ? a.getSemestre().getNombreSemestre() : ""); cell4.setCellStyle(borderedStyleData);
                // Celdas 5-11 vacías pero con borde
                for (int i = 5; i <= 11; i++) { Cell c = row.createCell(i); c.setCellStyle(borderedStyleData); }
                Cell cell12 = row.createCell(12); cell12.setCellValue(a.getMisDatos().getGastosIngresos().getDependeEconomicamente() ? "SI" : "NO"); cell12.setCellStyle(borderedStyleData);
                Cell cell13 = row.createCell(13); cell13.setCellValue(a.getMisDatos().getGastosIngresos().getSolicitaBecaAlimenticia() ? "SI" : "NO"); cell13.setCellStyle(borderedStyleData);
                Cell cell14 = row.createCell(14); cell14.setCellValue(Math.round(a.getMisDatos().getGastosIngresos().getGastoMensual())); cell14.setCellStyle(borderedStyleData);
                long idSituacion = a.getMisDatos().getSituacionVivienda() != null ? a.getMisDatos().getSituacionVivienda().getId() : 0L;
                Cell cell15 = row.createCell(15);
                switch ((int) idSituacion){
                    case 3 -> cell15.setCellValue("RU");
                    case 4 -> cell15.setCellValue("RA");
                    case 5 -> cell15.setCellValue("VF");
                    default -> cell15.setCellValue("");
                }
                cell15.setCellStyle(borderedStyleData);
                Cell cell16 = row.createCell(16); cell16.setCellValue(Math.round(a.getMisDatos().getGastosIngresos().getPersonasComparteRenta())); cell16.setCellStyle(borderedStyleData);
                Cell cell17 = row.createCell(17); cell17.setCellValue(Math.round(a.getMisDatos().getGastosIngresos().getPagoRentaMensual())); cell17.setCellStyle(borderedStyleData);
                String valorVehiculo = "NO";
                if(a.getMisDatos().getLlevaAutomovil()) valorVehiculo = "C";
                if(a.getMisDatos().getLlevamotocicleta() && a.getMisDatos().getTransporteMotocicleta() != null) {
                    valorVehiculo = "M (" + a.getMisDatos().getTransporteMotocicleta().getMarca() + " " + a.getMisDatos().getTransporteMotocicleta().getModelo()+ ")";
                }
                Cell cell18 = row.createCell(18); cell18.setCellValue(valorVehiculo); cell18.setCellStyle(borderedStyleData);
                int ingresoBruto = Math.toIntExact(Math.round(a.getGastosIngresosFamiliares().getIngresoBrutoTotal()));
                int ingresoNeto = Math.toIntExact(Math.round(a.getGastosIngresosFamiliares().getIngresoTotal()));
                Cell cell19 = row.createCell(19); cell19.setCellValue(Math.round(a.getGastosIngresosFamiliares().getIngresoBrutoTotal())); cell19.setCellStyle(borderedStyleData);
                Cell cell20 = row.createCell(20); cell20.setCellValue(Math.round(a.getGastosIngresosFamiliares().getIngresoTotal())); cell20.setCellStyle(borderedStyleData);
                int diferencia = ingresoBruto - ingresoNeto;
                Cell cell21 = row.createCell(21); cell21.setCellValue(diferencia); cell21.setCellStyle(borderedStyleData);
                double porcentajeGasto = ingresoNeto != 0 ? (a.getMisDatos().getGastosIngresos().getGastoMensual() / ingresoNeto) * 100 : 0;
                Cell cell22 = row.createCell(22); cell22.setCellValue(Math.round(porcentajeGasto)); cell22.setCellStyle(borderedStyleData);
                Cell cell23 = row.createCell(23); cell23.setCellValue(Math.round(a.getGastosIngresosFamiliares().getReciboLuzModel().getPromedioPago())); cell23.setCellStyle(borderedStyleData);
                Cell cell24 = row.createCell(24); cell24.setCellValue(a.getMiFamilia().getNumPersonasDependen()); cell24.setCellStyle(borderedStyleData);
                String fechaSolicitud = a.getFechaEnvio() != null ? new SimpleDateFormat("dd/MM/yyyy").format(a.getFechaEnvio()) : "";
                Cell cell25 = row.createCell(25); cell25.setCellValue(fechaSolicitud); cell25.setCellStyle(borderedStyleData);
                Cell cell26 = row.createCell(26); cell26.setCellValue(a.getMisDatos().getFamiliarComunero() ? "SI" : "NO"); cell26.setCellStyle(borderedStyleData);
                Cell cell27 = row.createCell(27); cell27.setCellValue(a.getMiTutor().getTrabajadorSuneo() ? "SI" : "NO"); cell27.setCellStyle(borderedStyleData);
                // Celdas 28-31 vacías pero con borde
                for (int i = 28; i <= 31; i++) { Cell c = row.createCell(i); c.setCellStyle(borderedStyleData); }
            }
            
            // Autoajustar el ancho de cada columna al texto
            for (int i = 0; i <= 31; i++) {
                sheet.autoSizeColumn(i);
            }

        }

        // Convertir a base64
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            workbook.close();
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}