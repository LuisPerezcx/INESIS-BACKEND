package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.utils.PDF;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PDFServiceJPA {

    @Autowired
    private AlumnoServiceJPA alumnoServiceJPA;

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



    public String generarPdf(Long idAlumno){
        try {
            Alumno alumno = alumnoServiceJPA.findById(idAlumno);

            // Ruta del PDF base (con campos de formulario)
            PdfReader reader = new PdfReader("estudioSocioEconomico.pdf");

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
            form.setField(PDF.ESE.dependeSi, depende != null && depende ? "X" : "", true);
            form.setField(PDF.ESE.dependeNo, depende != null && depende ? "X" : "", true);

            form.setField(PDF.ESE.domicilioActualAlumno,domicilio(alumno.getMisDatos().getDomicilio().getCalle(), alumno.getMisDatos().getDomicilio().getNumero(),alumno.getMisDatos().getDomicilio().getColonia(), alumno.getMisDatos().getDomicilio().getColonia(),alumno.getMisDatos().getNombreCasaHuesped()), true);

            Boolean solicita = alumno.getMisDatos().getGastosIngresos().getSolicitaBecaAlimenticia();
            form.setField(PDF.ESE.becaAlimenticiaSi, solicita != null && solicita ? "X" : "", true);
            form.setField(PDF.ESE.becaAlimenticiaNo, solicita != null && solicita ? "X" : "", true);

            form.setField(PDF.ESE.gastoMensual, valorSeguro(String.valueOf(alumno.getMisDatos().getGastosIngresos().getGastoMensual())," "), true);
            form.setField(PDF.ESE.rentaCuarto, "X", true);
            form.setField(PDF.ESE.rentaCasa, "X", true);
            form.setField(PDF.ESE.viveFamiliares, "X", true);
            form.setField(PDF.ESE.numPersonaComparte, "2", true);
            form.setField(PDF.ESE.rentaMensual, " ", true);

            Boolean familiarComunero = alumno.getMisDatos().getFamiliarComunero();
            form.setField(PDF.ESE.nietoComuneroSi, familiarComunero != null && familiarComunero ? "X" : "", true);
            form.setField(PDF.ESE.nietoComuneroNo, familiarComunero != null && familiarComunero ? "X" : "", true);

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
            form.setField(PDF.ESE.utilizaTelefonoSi, celular != null && celular ? "X" : "", true);
            form.setField(PDF.ESE.utilizaTelefonoNo, celular != null && celular ? "X" : "", true);

            Boolean compu = alumno.getMisDatos().getTieneComputadora();
            form.setField(PDF.ESE.tieneComputadoraSi, compu != null && compu ? "X" : "", true);
            form.setField(PDF.ESE.tieneComputadoraNo, compu != null && compu ? "X" : "", true);

            form.setField(PDF.ESE.nombreTutor, " ", true);
            form.setField(PDF.ESE.parentesco, " ", true);
            form.setField(PDF.ESE.telOCorreo, " ", true);
            form.setField(PDF.ESE.trabajadorSuneo, " ", true);
            form.setField(PDF.ESE.domicilioCompletoTutor, " ", true);
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
            form.setField(PDF.ESE.reciboTitular," ",true);
            form.setField(PDF.ESE.reciboDomicilio," ",true);
            form.setField(PDF.ESE.periodoReportado," ",true);
            form.setField(PDF.ESE.promedioMes," ",true);
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


            form.setField(PDF.ESE.apellidoP, valorSeguro(alumno.getApellidoPaterno()," "), true);
            form.setField(PDF.ESE.apellidoM, " ", true);
            form.setField(PDF.ESE.nombreAlum, valorSeguro(alumno.getNombre()," "), true);
            form.setField(PDF.ESE.sexo, valorSeguro(alumno.getSexo().getNombreSexo()," "), true);
            form.setField(PDF.ESE.estadoCivil, valorSeguro(alumno.getMisDatos().getEstadoCivil().getNombreEstadoCivil()," "), true);
            form.setField(PDF.ESE.carrera, valorSeguro(alumno.getCarrera().getNombreCarrera()," "), true);
            form.setField(PDF.ESE.telefonoAlumno, valorSeguro(alumno.getTelefono()," "), true);
            form.setField(PDF.ESE.emailAlumno, valorSeguro(alumno.getCorreo()," "), true);
            form.setField(PDF.ESE.lenguajeDialecto, valorSeguro(alumno.getMisDatos().getIdioma()," "), true);
            form.setField(PDF.ESE.regionActualFamilia, " ", true);
            form.setField(PDF.ESE.distritoActualFamilia, " ", true);
            form.setField(PDF.ESE.municipioActualFamilia, " ", true);
            form.setField(PDF.ESE.localidadActualFamilia, " ", true);
            form.setField(PDF.ESE.estadoActualFamilia, " ", true);
            form.setField(PDF.ESE.telefonoActualFamilia, " ", true);
            form.setField(PDF.ESE.dependeEconomicamente, valorSeguro(alumno.getMisDatos().getGastosIngresos().getNombreQuienDependes()," "), true);
            form.setField(PDF.ESE.nombreEmpresa, valorSeguro(alumno.getMisDatos().getGastosIngresos().getTrabajo().getNombreTrabajo()," "), true);
            form.setField(PDF.ESE.ingresoMensual,valorSeguro(String.valueOf(alumno.getMisDatos().getGastosIngresos().getTrabajo().getIngresoMensual())," "), true);
            form.setField(PDF.ESE.domicilioTrabajo,domicilioTelefono(alumno.getMisDatos().getGastosIngresos().getTrabajo().getDomicilioTrabajo(),alumno.getMisDatos().getGastosIngresos().getTrabajo().getTelefonoTrabajo()), true);


            form.setField(PDF.ESE.aportanGasto, " ", true);
            form.setField(PDF.ESE.ingresoMensualPromedio, " ", true);
            form.setField(PDF.ESE.numDependeMencionado, " ", true);
            form.setField(PDF.ESE.promedioMensual," ",true);
            form.setField(PDF.ESE.alimentacion, "0", true);
            form.setField(PDF.ESE.renta, "0", true);
            form.setField(PDF.ESE.servicios, "0", true);
            form.setField(PDF.ESE.gastoEscolar, "0", true);
            form.setField(PDF.ESE.ropa, "0", true);
            form.setField(PDF.ESE.transporte, "0", true);
            form.setField(PDF.ESE.otros, "0", true);
            form.setField(PDF.ESE.total, "0", true);
            form.setField(PDF.ESE.propia, "X", true);
            form.setField(PDF.ESE.alquilada, "X", true);
            form.setField(PDF.ESE.otra, "X", true);
            form.setField(PDF.ESE.casaSola, "X", true);
            form.setField(PDF.ESE.condominio, "X", true);
            form.setField(PDF.ESE.vecindad, "X", true);
            form.setField(PDF.ESE.departamento, "X", true);
            form.setField(PDF.ESE.mamposteria, "X", true);
            form.setField(PDF.ESE.madera, "X", true);
            form.setField(PDF.ESE.lamina, "X", true);
            form.setField(PDF.ESE.concreto, "X", true);
            form.setField(PDF.ESE.otrosMaterial, "X", true);
            form.setField(PDF.ESE.agua, "X", true);
            form.setField(PDF.ESE.luz, "X", true);
            form.setField(PDF.ESE.drenaje, "X", true);
            form.setField(PDF.ESE.telefono, "X", true);
            form.setField(PDF.ESE.otrosServicios, " ", true);
            form.setField(PDF.ESE.numHabitan, " ", true);
            form.setField(PDF.ESE.sinEstudiosM, "X", true);
            form.setField(PDF.ESE.primariaM, "X", true);
            form.setField(PDF.ESE.secundariaM, "X", true);
            form.setField(PDF.ESE.bachilleratoM, "X", true);
            form.setField(PDF.ESE.tecnicoM, "X", true);
            form.setField(PDF.ESE.licenciaturaM, "X", true);
            form.setField(PDF.ESE.posgradoM, "X", true);
            form.setField(PDF.ESE.sinEstudiosP, "X", true);
            form.setField(PDF.ESE.primariaP, "X", true);
            form.setField(PDF.ESE.secundariaP, "X", true);
            form.setField(PDF.ESE.bachilleratoP, "X", true);
            form.setField(PDF.ESE.tecnicoP, "X", true);
            form.setField(PDF.ESE.licenciaturaP, "X", true);
            form.setField(PDF.ESE.posgradoP, "X", true);
            form.setField(PDF.ESE.temporal, "X", true);
            form.setField(PDF.ESE.permanente, "X", true);

            //Tipo de ocupacion
            Long ocupacionSeleccionada = alumno.getMisDatos().getGastosIngresos().getOcupacion().getId();
            form.setField(PDF.ESE.comerciante, ocupacionSeleccionada == 1 ? "X" : "", true);
            form.setField(PDF.ESE.empleadoGobierno, ocupacionSeleccionada == 2 ? "X" : "", true);
            form.setField(PDF.ESE.empleadoPrivado, ocupacionSeleccionada == 3 ? "X" : "", true);
            form.setField(PDF.ESE.negocioPropio, ocupacionSeleccionada == 4 ? "X" : "", true);
            form.setField(PDF.ESE.jubilado, ocupacionSeleccionada == 5 ? "X" : "", true);
            form.setField(PDF.ESE.laborCampo, ocupacionSeleccionada == 6 ? "X" : "", true);
            form.setField(PDF.ESE.empleadoComunal, ocupacionSeleccionada == 7 ? "X" : "", true);
            form.setField(PDF.ESE.otroOcupacion, ocupacionSeleccionada == 8 ? "X" : "", true);


            form.setField(PDF.ESE.numHermanos, " ", true);
            form.setField(PDF.ESE.numHermanosEstudiando, " ", true);
            form.setField(PDF.ESE.numHermanosNoEstudian, " ", true);
            form.setField(PDF.ESE.numHermanosLicenciatura, " ", true);


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
            form.setField(PDF.ESE.recursosSi, recursos != null && recursos ? "X" : "", true);
            form.setField(PDF.ESE.recursosNo, recursos != null && recursos ? "X" : "", true);

            List<Long> idsMediosSeleccionados = alumno.getMisDatos().getMediosTraslado().stream()
                    .map(medio -> medio.getCatMediosTransporte().getId())
                    .collect(Collectors.toList());


            if (idsMediosSeleccionados.contains(1L)) {
                form.setField(PDF.ESE.autoAmigos, "X", true);
            }
            if (idsMediosSeleccionados.contains(2L)) {
                form.setField(PDF.ESE.bicicleta, "X", true);
            }
            if (idsMediosSeleccionados.contains(3L)) {
                form.setField(PDF.ESE.mototaxi, "X", true);
            }
            if (idsMediosSeleccionados.contains(4L)) {
                form.setField(PDF.ESE.caminando, "X", true);
            }
            if (idsMediosSeleccionados.contains(5L)) {
                form.setField(PDF.ESE.autoFamiliar, "X", true);
            }
            if (idsMediosSeleccionados.contains(6L)) {
                form.setField(PDF.ESE.autoPropio, "X", true);
            }
            if (idsMediosSeleccionados.contains(7L)) {
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