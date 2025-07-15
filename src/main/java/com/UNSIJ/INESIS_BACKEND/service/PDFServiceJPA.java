package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.controller.DomicilioController;
import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.utils.PDF;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PDFServiceJPA {

    @Autowired
    private AlumnoServiceJPA alumnoServiceJPA;
    @Autowired
    private DomicilioController domicilioController;

    @Transient
    private String estado;
    @Transient
    private String municipio;
    @Transient
    private Alumno alumno;

    public static String valorSeguro(String valor, String valorPorDefecto) {
        return (valor != null && !valor.trim().isEmpty()) ? valor : valorPorDefecto;
    }

    public static String domicilio(String calle, String numero, String colonia, String localidad, String casaHuesped) {
        String calleSeguro = (calle != null && !calle.trim().isEmpty() ? calle.trim() : " ");
        String numeroSeguro = (numero != null && !numero.trim().isEmpty() ? numero.trim() : " ");
        String coloniaSeguro = (colonia != null && !colonia.trim().isEmpty() ? colonia.trim() : " ");
        String localidadSeguro = (localidad != null && !localidad.trim().isEmpty() ? localidad.trim() : " ");
        String casaHuspedSeguro = (casaHuesped != null && !casaHuesped.trim().isEmpty() ? casaHuesped.trim() : " ");
        return (calleSeguro + " " + numeroSeguro + " " + coloniaSeguro + " " + localidadSeguro + " " + casaHuspedSeguro).trim();
    }


    public static String nombreCompletoSeguro(String nombre, String apellidoP, String apellidoM) {
        String nombreSeguro = (nombre != null && !nombre.trim().isEmpty()) ? nombre.trim() : " ";
        String apellidopSeguro = (apellidoP != null && !apellidoP.trim().isEmpty()) ? apellidoP.trim() : " ";
        String apellidoMSeguro = (apellidoM != null && !apellidoM.trim().isEmpty()) ? apellidoM.trim() : " ";
        return (nombreSeguro + " " + apellidopSeguro + " " + apellidoMSeguro).trim();
    }

    public static String domicilioTelefono(String domicilio, String telefono) {
        String domSeguro = (domicilio != null && !domicilio.trim().isEmpty()) ? domicilio.trim() : " ";
        String telSeguro = (telefono != null && !telefono.trim().isEmpty()) ? telefono.trim() : " ";
        return (domSeguro + " " + telSeguro).trim();
    }

    public static String marcaTransporte(String marca, String modelo, Integer anio) {
        String marcaSeguro = (marca != null && !marca.trim().isEmpty() ? marca.trim() : "");
        String modeloSeguro = (modelo != null && !modelo.trim().isEmpty() ? modelo.trim() : "");
        String anioSeguro = (anio != null) ? anio.toString() : "";
        return String.join(" ", marcaSeguro, modeloSeguro, anioSeguro).trim();
    }

    public static String telCorreo (String tel, String correo){
        String telSeguro = (tel != null && !tel.trim().isEmpty()) ? tel.trim() : " ";
        String correoSeguro = (correo != null && !correo.trim().isEmpty()) ? correo.trim() : " ";
        return String.join(" ",telSeguro,correoSeguro).trim();
    }

    public static String periodoReportado (String inicio, String fin){
        String inicioSeguro = (inicio != null && !inicio.trim().isEmpty()) ? inicio.trim() : " ";
        String finSeguro = (fin != null && !fin.trim().isEmpty()) ? fin.trim() : " ";
        return String.join("-",inicioSeguro,finSeguro).trim();
    }
    public  String domicilioTutor(String calle, String numero, String colonia, String localidad, String cp) {
        String calleSeguro = (calle != null && !calle.trim().isEmpty() ? calle.trim() : " ");
        String numeroSeguro = (numero != null && !numero.trim().isEmpty() ? numero.trim() : " ");
        String coloniaSeguro = (colonia != null && !colonia.trim().isEmpty() ? colonia.trim() : " ");
        String localidadSeguro = (localidad != null && !localidad.trim().isEmpty() ? localidad.trim() : " ");

        String estado = "";
        String municipio = "";

        try {
            ResponseEntity<String> response = domicilioController.obtenerColoniasPorCP(cp); // <- usas cp directamente
            String respuestaJson = response.getBody(); // <- Extraes el JSON real

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(respuestaJson);

            estado = root.path("codigo_postal").path("estado").asText();
            municipio = root.path("codigo_postal").path("municipio").asText();

            System.out.println("Estado: " + estado);
            System.out.println("Municipio: " + municipio);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al procesar el JSON del CP");
        }

        return calleSeguro + " " + numeroSeguro + ", " + coloniaSeguro + ", " + localidadSeguro + ", " + municipio + ", " + estado + ", " + cp;
    }




    public String generarPdf(Long idAlumno){
        try {
            this.alumno = alumnoServiceJPA.findById(idAlumno);

            // Ruta del PDF base (con campos de formulario)
            PdfReader reader = new PdfReader("BECA-COLEGIATURA_final.pdf");

            // Archivo de salida
            //PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("src/main/resources/pdfsGenerados/prueba.pdf"));

            // Generar el PDF en memoria
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, baos);

            // Campos del formulario
            AcroFields form = stamper.getAcroFields();
            form.setGenerateAppearances(true);

            // Rellenar campos
            form.setField(PDF.ESE.nombreAlumno, nombreCompletoSeguro(alumno.getNombre(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno()), true);
            form.setField(PDF.ESE.carreraAlumno, valorSeguro(alumno.getCarrera().getNombreCarrera(), " "), true);
            form.setField(PDF.ESE.semestreAlumno,valorSeguro(alumno.getSemestre().getNombreSemestre(), ""), true);

            Boolean depende = alumno.getMisDatos().getGastosIngresos().getDependeEconomicamente();
            if (depende != null && depende){
                form.setField(PDF.ESE.dependeSi,"X", true);
                form.setField(PDF.ESE.dependeNo, "", true);
            }else {
                form.setField(PDF.ESE.dependeSi, "", true);
                form.setField(PDF.ESE.dependeNo,  "X", true);
            }


            form.setField(PDF.ESE.domicilioActualAlumno,domicilio(alumno.getMisDatos().getDomicilio().getCalle(), alumno.getMisDatos().getDomicilio().getNumero(),alumno.getMisDatos().getDomicilio().getColonia(), alumno.getMisDatos().getDomicilio().getLocalidad(),alumno.getMisDatos().getNombreCasaHuesped()), true);

            Boolean solicita = alumno.getMisDatos().getGastosIngresos().getSolicitaBecaAlimenticia();
            if (solicita != null && solicita){
                form.setField(PDF.ESE.becaAlimenticiaSi, "X" , true);
                form.setField(PDF.ESE.becaAlimenticiaNo,  "", true);
            }else{
                form.setField(PDF.ESE.becaAlimenticiaSi, "" , true);
                form.setField(PDF.ESE.becaAlimenticiaNo,  "X", true);
            }


            form.setField(PDF.ESE.gastoMensual, valorSeguro(String.valueOf(alumno.getMisDatos().getGastosIngresos().getGastoMensual())," "), true);

            Long situacionId =alumno.getMisDatos().getSituacionVivienda().getId();
            if (situacionId == 3L){
                form.setField(PDF.ESE.rentaCuarto, "X", true);

            } else if (situacionId == 4L) {
                form.setField(PDF.ESE.rentaCasa, "X", true);
            }else if (situacionId == 5L){
                form.setField(PDF.ESE.viveFamiliares, "X", true);

            }



            form.setField(PDF.ESE.numPersonaComparte, " ", true);
            form.setField(PDF.ESE.rentaMensual, " ", true);

            Boolean familiarComunero = alumno.getMisDatos().getFamiliarComunero();
            if (familiarComunero != null && familiarComunero){
                form.setField(PDF.ESE.nietoComuneroSi,  "X", true);
                form.setField(PDF.ESE.nietoComuneroNo,  "", true);
            }else{
                form.setField(PDF.ESE.nietoComuneroSi,  "", true);
                form.setField(PDF.ESE.nietoComuneroNo,  "X", true);
            }


            Boolean llevaCarro = alumno.getMisDatos().getLlevaAutomovil();
            if (llevaCarro != null && llevaCarro){
                form.setField(PDF.ESE.llevaCarroSi, "X", true);
                form.setField(PDF.ESE.llevaCarroNo, " ", true);
            }else {
                // No lleva carro
                form.setField(PDF.ESE.llevaCarroSi, "", true);
                form.setField(PDF.ESE.llevaCarroNo, "X", true);
            }


            Boolean llevaMotocicleta = alumno.getMisDatos().getLlevamotocicleta();
            if (llevaMotocicleta != null && llevaMotocicleta){
                form.setField(PDF.ESE.llevaMotocicletaSi, "X", true);
                form.setField(PDF.ESE.llevaMotocicletaNo, " ", true);
                form.setField(PDF.ESE.marcaMotocicleta,marcaTransporte(alumno.getMisDatos().getTransporteMotocicleta().getMarca(), alumno.getMisDatos().getTransporteMotocicleta().getModelo(),alumno.getMisDatos().getTransporteMotocicleta().getAnio()), true);
            }else {
                form.setField(PDF.ESE.llevaMotocicletaSi, " ", true);
                form.setField(PDF.ESE.llevaMotocicletaNo, "X", true);
                form.setField(PDF.ESE.marcaMotocicleta, "     ", true);
            }



            Boolean celular = alumno.getMisDatos().getUtilizaCelular();
            if(celular != null && celular){
                form.setField(PDF.ESE.utilizaTelefonoSi, "X", true);
                form.setField(PDF.ESE.utilizaTelefonoNo, " " , true);
            }else{
                form.setField(PDF.ESE.utilizaTelefonoSi," ", true);
                form.setField(PDF.ESE.utilizaTelefonoNo, "X", true);
            }


            Boolean compu = alumno.getMisDatos().getTieneComputadora();
            if(compu != null && compu){
                form.setField(PDF.ESE.tieneComputadoraSi, "X", true);
                form.setField(PDF.ESE.tieneComputadoraNo, " ", true);
            }else{
                form.setField(PDF.ESE.tieneComputadoraSi,  " " , true);
                form.setField(PDF.ESE.tieneComputadoraNo, "X", true);
            }

            if(alumno.getMiTutor() != null ){
                form.setField(PDF.ESE.nombreTutor, valorSeguro(alumno.getMiTutor().getNombreTutor()," "), true);
                form.setField(PDF.ESE.parentesco, valorSeguro(alumno.getMiTutor().getParentesco().getNombreParentesco()," "), true);
                form.setField(PDF.ESE.telOCorreo, telCorreo(alumno.getMiTutor().getTelefono(), alumno.getMiTutor().getCorreo()), true);

                Boolean suneo = alumno.getMiTutor().getTrabajadorSuneo();
                if (suneo != null && suneo){
                    form.setField(PDF.ESE.trabajadorSuneo, "Si", true);
                }else{
                    form.setField(PDF.ESE.trabajadorSuneo, "No", true);
                }
                form.setField(PDF.ESE.domicilioCompletoTutor, domicilioTutor(alumno.getMiTutor().getDomicilio().getCalle(),alumno.getMiTutor().getDomicilio().getNumero(),alumno.getMiTutor().getDomicilio().getColonia(),alumno.getMiTutor().getDomicilio().getLocalidad(),alumno.getMiTutor().getDomicilio().getCp()), true);
            }

            form.setField(PDF.ESE.nombreParentesco1," ",true);
            form.setField(PDF.ESE.nombreParentesco2," ",true);
            form.setField(PDF.ESE.nombreParentesco3," ",true);
            form.setField(PDF.ESE.empresaLugar1," ",true);
            form.setField(PDF.ESE.empresaLugar2," ",true);
            form.setField(PDF.ESE.empresaLugar3," ",true);
            form.setField(PDF.ESE.puestoTipo1," ",true);
            form.setField(PDF.ESE.puestoTipo2," ",true);
            form.setField(PDF.ESE.puestoTipo3," ",true);
            form.setField(PDF.ESE.bruto1," ",true);
            form.setField(PDF.ESE.bruto2," ",true);
            form.setField(PDF.ESE.bruto3," ",true);
            form.setField(PDF.ESE.brutoTotal," ",true);
            form.setField(PDF.ESE.neto1," ",true);
            form.setField(PDF.ESE.neto2," ",true);
            form.setField(PDF.ESE.neto3," ",true);
            form.setField(PDF.ESE.netoTotal," ",true);
            //datos del recibo de luz
            form.setField(PDF.ESE.reciboTitular,valorSeguro(alumno.getGastosIngresosFamiliares().getReciboLuzModel().getTitular()," "),true);
            form.setField(PDF.ESE.reciboDomicilio," ",true);
            form.setField(PDF.ESE.periodoReportado,periodoReportado(alumno.getGastosIngresosFamiliares().getReciboLuzModel().getPeriodoInicio(),alumno.getGastosIngresosFamiliares().getReciboLuzModel().getPeriodoFin()),true);
            form.setField(PDF.ESE.promedioMes,valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getReciboLuzModel().getPromedioPago())," "),true);
            //datos del recibo de luz

            form.setField(PDF.ESE.dependiente1," ",true);
            form.setField(PDF.ESE.dependiente2," ",true);
            form.setField(PDF.ESE.dependiente3," ",true);
            form.setField(PDF.ESE.dependiente4," ",true);
            form.setField(PDF.ESE.dependiente5," ",true);
            form.setField(PDF.ESE.edad1," ",true);
            form.setField(PDF.ESE.edad2," ",true);
            form.setField(PDF.ESE.edad3," ",true);
            form.setField(PDF.ESE.edad4," ",true);
            form.setField(PDF.ESE.edad5," ",true);
            form.setField(PDF.ESE.parentescoDependiente1," ",true);
            form.setField(PDF.ESE.parentescoDependiente2," ",true);
            form.setField(PDF.ESE.parentescoDependiente3," ",true);
            form.setField(PDF.ESE.parentescoDependiente4," ",true);
            form.setField(PDF.ESE.parentescoDependiente5," ",true);
            form.setField(PDF.ESE.tipoComprobante1," ",true);
            form.setField(PDF.ESE.tipoComprobante2," ",true);
            form.setField(PDF.ESE.tipoComprobante3," ",true);
            form.setField(PDF.ESE.tipoComprobante4," ",true);
            form.setField(PDF.ESE.tipoComprobante5," ",true);
            form.setField(PDF.ESE.observaciones, " ", true);

            //datos del alumno
            form.setField(PDF.ESE.apellidoP, valorSeguro(alumno.getApellidoPaterno()," "), true);
            form.setField(PDF.ESE.apellidoM,valorSeguro(alumno.getApellidoMaterno(), " "), true);
            form.setField(PDF.ESE.nombreAlum, valorSeguro(alumno.getNombre()," "), true);
            form.setField(PDF.ESE.sexo, valorSeguro(alumno.getSexo().getNombreSexo()," "), true);
            form.setField(PDF.ESE.estadoCivil, valorSeguro(alumno.getMisDatos().getEstadoCivil().getNombreEstadoCivil()," "), true);
            form.setField(PDF.ESE.carrera, valorSeguro(alumno.getCarrera().getNombreCarrera()," "), true);
            form.setField(PDF.ESE.telefonoAlumno, valorSeguro(alumno.getTelefono()," "), true);
            form.setField(PDF.ESE.emailAlumno, valorSeguro(alumno.getCorreo()," "), true);
            form.setField(PDF.ESE.lenguajeDialecto, valorSeguro(alumno.getMisDatos().getIdioma()," "), true);
            //datos del alumno

            form.setField(PDF.ESE.regionActualFamilia, " ", true);
            form.setField(PDF.ESE.distritoActualFamilia, " ", true);
            form.setField(PDF.ESE.municipioActualFamilia, " ", true);
            form.setField(PDF.ESE.localidadActualFamilia, " ", true);
            form.setField(PDF.ESE.estadoActualFamilia, " ", true);
            form.setField(PDF.ESE.telefonoActualFamilia, " ", true);
            form.setField(PDF.ESE.dependeEconomicamente, valorSeguro(alumno.getMisDatos().getGastosIngresos().getNombreQuienDependes()," "), true);

            if(alumno.getMisDatos().getGastosIngresos().getTrabajo() != null ){
                form.setField(PDF.ESE.nombreEmpresa, valorSeguro(alumno.getMisDatos().getGastosIngresos().getTrabajo().getNombreTrabajo()," "), true);
                form.setField(PDF.ESE.ingresoMensual,valorSeguro(String.valueOf(alumno.getMisDatos().getGastosIngresos().getTrabajo().getIngresoMensual())," "), true);
                form.setField(PDF.ESE.domicilioTrabajo,domicilioTelefono(alumno.getMisDatos().getGastosIngresos().getTrabajo().getDomicilioTrabajo(),alumno.getMisDatos().getGastosIngresos().getTrabajo().getTelefonoTrabajo()), true);

            }

            form.setField(PDF.ESE.aportanGasto, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getNummeroPersonasAportan())," "), true);
            form.setField(PDF.ESE.ingresoMensualPromedio, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getIngresoTotal())," "), true);
            form.setField(PDF.ESE.numDependeMencionado, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getNumeroPersonasDependen())," "), true);
            form.setField(PDF.ESE.promedioMensual," ",true);

            form.setField(PDF.ESE.alimentacion, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getGastoAlimentacion()),"0"), true);
            form.setField(PDF.ESE.renta, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getGastoRenta()),"0"), true);
            form.setField(PDF.ESE.servicios, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getGastoServicios()),"0"), true);
            form.setField(PDF.ESE.gastoEscolar, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getGastoEscolares()),"0"), true);
            form.setField(PDF.ESE.ropa, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getGastoRopa()),"0"), true);
            form.setField(PDF.ESE.transporte, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getGastoTransporte()),"0"), true);
            form.setField(PDF.ESE.otros, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getGastoOtros()),"0"), true);
            form.setField(PDF.ESE.total, valorSeguro(String.valueOf(alumno.getGastosIngresosFamiliares().getGastoFamiliarModel().getTotalGastos()),"0"), true);

            Long situacionViviendaId =alumno.getMiFamilia().getViviendaFamiliar().getSituacionVivienda().getId();
            if (situacionViviendaId == 1L){
                form.setField(PDF.ESE.propia, "X", true);

            } else if (situacionViviendaId == 2L) {
                form.setField(PDF.ESE.alquilada, "X", true);
            }else if (situacionViviendaId == 6L){
                form.setField(PDF.ESE.otra, "X", true);

            }

            Long tipoViviendaId =alumno.getMiFamilia().getViviendaFamiliar().getTipoVivienda().getId();
            if (tipoViviendaId == 1L){
                form.setField(PDF.ESE.casaSola, "X", true);
            } else if (tipoViviendaId == 2L) {
                form.setField(PDF.ESE.condominio, "X", true);
            }else if (tipoViviendaId == 3L){
                form.setField(PDF.ESE.vecindad, "X", true);
            }else if (tipoViviendaId == 4L){
                form.setField(PDF.ESE.departamento, "X", true);
            }

            Long materialViviendaId =alumno.getMiFamilia().getViviendaFamiliar().getMaterialVivienda().getId();
            if (materialViviendaId == 1L){
                form.setField(PDF.ESE.mamposteria, "X", true);
            } else if (materialViviendaId == 2L) {
                form.setField(PDF.ESE.madera, "X", true);
            }else if (materialViviendaId == 3L){
                form.setField(PDF.ESE.lamina, "X", true);
            }else if (materialViviendaId == 4L){
                form.setField(PDF.ESE.concreto, "X", true);
            }else if (materialViviendaId == 5L){
                form.setField(PDF.ESE.otrosMaterial, "X", true);
            }

            form.setField(PDF.ESE.agua, "X", true);
            form.setField(PDF.ESE.luz, "X", true);
            form.setField(PDF.ESE.drenaje, "X", true);
            form.setField(PDF.ESE.telefono, "X", true);
            form.setField(PDF.ESE.otrosServicios, " ", true);

            form.setField(PDF.ESE.numHabitan, valorSeguro(String.valueOf(alumno.getMiFamilia().getViviendaFamiliar().getNumPersonasHabitan())," "), true);
           // estudios madre
            Long escolaidarMadre = alumno.getMiFamilia().getEscolaridadMadre().getId();
            if (escolaidarMadre == 1L){
                form.setField(PDF.ESE.sinEstudiosM, "X", true);
            } else if (escolaidarMadre == 2L) {
                form.setField(PDF.ESE.primariaM, "X", true);
            }else if (escolaidarMadre == 3L) {
                form.setField(PDF.ESE.secundariaM, "X", true);
            }else if (escolaidarMadre == 4L) {
                form.setField(PDF.ESE.bachilleratoM, "X", true);
            }else if (escolaidarMadre == 5L) {
                form.setField(PDF.ESE.tecnicoM, "X", true);
            }else if (escolaidarMadre == 6L) {
                form.setField(PDF.ESE.licenciaturaM, "X", true);
            }else if (escolaidarMadre == 7L) {
                form.setField(PDF.ESE.posgradoM, "X", true);
            }
            // estudios madre

            //estudios padre
            Long escolaridadPadre = alumno.getMiFamilia().getEscolaridadPadre().getId();
            if (escolaridadPadre == 1L){
                form.setField(PDF.ESE.sinEstudiosP, "X", true);
            }else if (escolaridadPadre == 2L){
                form.setField(PDF.ESE.primariaP, "X", true);
            }else if (escolaridadPadre == 3L){
                form.setField(PDF.ESE.secundariaP, "X", true);
            }else if (escolaridadPadre == 4L){
                form.setField(PDF.ESE.bachilleratoP, "X", true);
            }else if (escolaridadPadre == 5L){
                form.setField(PDF.ESE.tecnicoP, "X", true);
            }else if (escolaridadPadre == 6L){
                form.setField(PDF.ESE.licenciaturaP, "X", true);
            }else if (escolaridadPadre == 7L){
                form.setField(PDF.ESE.posgradoP, "X", true);
            }
            //estudios padre

            // temporal o permanente
            Long temporalPermanente = alumno.getMiTutor().getCatTipoTrabajo().getId();
            if (temporalPermanente == 1L){
                form.setField(PDF.ESE.temporal, "X", true);
            }else if (temporalPermanente == 2L){
                form.setField(PDF.ESE.permanente, "X", true);
            }
            // temporal o permanente


            //Tipo de ocupacion
            Long ocupacionSeleccionada = alumno.getMisDatos().getGastosIngresos().getOcupacion().getId();
            form.setField(PDF.ESE.comerciante, ocupacionSeleccionada == 1 ? "X" : "", true);
            form.setField(PDF.ESE.empleadoGobierno, ocupacionSeleccionada == 3 ? "X" : "", true);
            form.setField(PDF.ESE.empleadoPrivado, ocupacionSeleccionada == 5 ? "X" : "", true);
            form.setField(PDF.ESE.negocioPropio, ocupacionSeleccionada == 7 ? "X" : "", true);
            form.setField(PDF.ESE.jubilado, ocupacionSeleccionada == 2 ? "X" : "", true);
            form.setField(PDF.ESE.laborCampo, ocupacionSeleccionada == 4 ? "X" : "", true);
            form.setField(PDF.ESE.empleadoComunal, ocupacionSeleccionada == 6 ? "X" : "", true);
            form.setField(PDF.ESE.otroOcupacion, ocupacionSeleccionada == 8 ? "X" : "", true);


            form.setField(PDF.ESE.numHermanos, valorSeguro(String.valueOf(alumno.getMiFamilia().getNumHermanos())," "), true);
            form.setField(PDF.ESE.numHermanosEstudiando, valorSeguro(String.valueOf(alumno.getMiFamilia().getNumHermanosEstudiando())," "), true);
            form.setField(PDF.ESE.numHermanosNoEstudian, valorSeguro(String.valueOf(alumno.getMiFamilia().getNumHermanosNoEstudiando())," "), true);
            form.setField(PDF.ESE.numHermanosLicenciatura, valorSeguro(String.valueOf(alumno.getMiFamilia().getNumHermanosLicenciatura())," "), true);


            form.setField(PDF.ESE.aguaCaliente, "X", true);
            form.setField(PDF.ESE.refrigerador, "X", true);
            form.setField(PDF.ESE.estufa, "X", true);
            form.setField(PDF.ESE.televisor, "X", true);
            form.setField(PDF.ESE.lavadora, "X", true);
            form.setField(PDF.ESE.aireAcondicionado, "X", true);
            form.setField(PDF.ESE.automovilPropio, "X", true);
            form.setField(PDF.ESE.microondas, "X", true);
            form.setField(PDF.ESE.espacioEstudio, "X", true);
            form.setField(PDF.ESE.computadora, "X", true);
            form.setField(PDF.ESE.impresora, "X", true);
            form.setField(PDF.ESE.librero, "X", true);
            form.setField(PDF.ESE.mesa, "X", true);
            form.setField(PDF.ESE.libros, "X", true);
            form.setField(PDF.ESE.diccionario, "X", true);
            form.setField(PDF.ESE.caluladora, "X", true);

            Boolean recursos = alumno.getMisDatos().getRecursosSuficientes();
            if (recursos != null && recursos){
                form.setField(PDF.ESE.recursosSi, "X", true);
                form.setField(PDF.ESE.recursosNo,   "", true);
            }else {
                form.setField(PDF.ESE.recursosSi, "", true);
                form.setField(PDF.ESE.recursosNo,   "X", true);
            }


            List<Long> idsMediosSeleccionados = alumno.getMisDatos().getMediosTraslado().stream()
                    .map(medio -> medio.getCatMediosTransporte().getId())
                    .collect(Collectors.toList());


            if (idsMediosSeleccionados.contains(3L)) {
                form.setField(PDF.ESE.autoAmigos, "X", true);
            }
            if (idsMediosSeleccionados.contains(2L)) {
                form.setField(PDF.ESE.bicicleta, "X", true);
            }
            if (idsMediosSeleccionados.contains(7L)) {
                form.setField(PDF.ESE.mototaxi, "X", true);
            }
            if (idsMediosSeleccionados.contains(5L)) {
                form.setField(PDF.ESE.caminando, "X", true);
            }
            if (idsMediosSeleccionados.contains(6L)) {
                form.setField(PDF.ESE.autoFamiliar, "X", true);
            }
            if (idsMediosSeleccionados.contains(1L)) {
                form.setField(PDF.ESE.autoPropio, "X", true);
            }
            if (idsMediosSeleccionados.contains(4L)) {
                form.setField(PDF.ESE.motocicleta, "X", true);
            }


            form.setField(PDF.ESE.firmaAlumno,nombreCompletoSeguro(alumno.getNombre(), alumno.getApellidoPaterno(),alumno.getApellidoMaterno()),true);

            //imprime los campos encontrados en el pdf
            for (String campo : form.getFields().keySet()) {
                System.out.println("Campo encontrado: " + campo);
            }

            // Opcional: hacer los campos no editables
            stamper.setFormFlattening(true);

            stamper.close();
            reader.close();

            // Convertir a Base64
            byte[] pdfBytes = baos.toByteArray();
            String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

            // Imprimir el Base64 (puedes devolverlo en una API REST, por ejemplo)
            System.out.println(base64Pdf);

            System.out.println("PDF generado con éxito.");
            return base64Pdf;
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el pdf "+ e.getMessage(), e);
        }

    }
}