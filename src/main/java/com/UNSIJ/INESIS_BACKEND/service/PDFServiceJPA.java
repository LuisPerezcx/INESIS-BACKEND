package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.utils.PDF;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class PDFServiceJPA {

    public static void main(String[] args) {
        Alumno alumno = new Alumno();
        generarPdf(alumno);
    }

    public static String valorSeguro(String valor, String valorPorDefecto) {
        return (valor != null && !valor.trim().isEmpty()) ? valor : valorPorDefecto;
    }

    public static String nombreCompletoSeguro(String nombre, String apellido) {
        String nombreSeguro = (nombre != null && !nombre.trim().isEmpty()) ? nombre.trim() : " ";
        String apellidoSeguro = (apellido != null && !apellido.trim().isEmpty()) ? apellido.trim() : " ";
        return (nombreSeguro + " " + apellidoSeguro).trim();
    }

    public static void generarPdf(Alumno alumno){
        try {
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
            form.setField(PDF.ESE.nombreAlumno, nombreCompletoSeguro(alumno.getNombre(), alumno.getApellido()), true);
            form.setField(PDF.ESE.carreraAlumno, valorSeguro(alumno.getCarrera().getNombreCarrera(), " "), true);
            form.setField(PDF.ESE.semestreAlumno,valorSeguro(alumno.getSemestre().getNombreSemestre(), ""), true);
            form.setField(PDF.ESE.dependeSi, "X", true);
            form.setField(PDF.ESE.dependeNo, "X", true);
            form.setField(PDF.ESE.domicilioActualAlumno, " ", true);
            form.setField(PDF.ESE.becaAlimenticiaSi, "X", true);
            form.setField(PDF.ESE.becaAlimenticiaNo, "X", true);
            form.setField(PDF.ESE.gastoMensual, " ", true);
            form.setField(PDF.ESE.rentaCuarto, "X", true);
            form.setField(PDF.ESE.rentaCasa, "X", true);
            form.setField(PDF.ESE.viveFamiliares, "X", true);
            form.setField(PDF.ESE.numPersonaComparte, "2", true);
            form.setField(PDF.ESE.rentaMensual, " ", true);
            form.setField(PDF.ESE.nietoComuneroSi, "X", true);
            form.setField(PDF.ESE.nietoComuneroNo, "X", true);
            form.setField(PDF.ESE.llevaCarroSi, "X", true);
            form.setField(PDF.ESE.llevaCarroNo, "X", true);
            form.setField(PDF.ESE.llevaMotocicletaSi, "X", true);
            form.setField(PDF.ESE.llevaMotocicletaNo, "X", true);
            form.setField(PDF.ESE.marcaMotocicleta, " ", true);
            form.setField(PDF.ESE.utilizaTelefonoSi, "X", true);
            form.setField(PDF.ESE.utilizaTelefonoNo, "X", true);
            form.setField(PDF.ESE.tieneComputadoraSi, "X", true);
            form.setField(PDF.ESE.tieneComputadoraNo, "X", true);
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


            form.setField(PDF.ESE.apellidoP, valorSeguro(alumno.getApellido()," "), true);
            form.setField(PDF.ESE.apellidoM, " ", true);
            form.setField(PDF.ESE.nombreAlum, valorSeguro(alumno.getNombre()," "), true);
            form.setField(PDF.ESE.sexo, valorSeguro(alumno.getSexo().getNombreSexo()," "), true);
            form.setField(PDF.ESE.estadoCivil, " ", true);
            form.setField(PDF.ESE.carrera, valorSeguro(alumno.getCarrera().getNombreCarrera()," "), true);
            form.setField(PDF.ESE.telefonoAlumno, valorSeguro(alumno.getTelefono()," "), true);
            form.setField(PDF.ESE.emailAlumno, valorSeguro(alumno.getCorreo()," "), true);
            form.setField(PDF.ESE.lenguajeDialecto, " ", true);
            form.setField(PDF.ESE.regionActualFamilia, " ", true);
            form.setField(PDF.ESE.distritoActualFamilia, " ", true);
            form.setField(PDF.ESE.municipioActualFamilia, " ", true);
            form.setField(PDF.ESE.localidadActualFamilia, " ", true);
            form.setField(PDF.ESE.estadoActualFamilia, " ", true);
            form.setField(PDF.ESE.telefonoActualFamilia, " ", true);
            form.setField(PDF.ESE.dependeEconomicamente, " ", true);
            form.setField(PDF.ESE.nombreEmpresa, " ", true);
            form.setField(PDF.ESE.ingresoMensual, " ", true);
            form.setField(PDF.ESE.domicilioTrabajo, " ", true);


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
            form.setField(PDF.ESE.comerciante, "X", true);
            form.setField(PDF.ESE.empleadoGobierno, "X", true);
            form.setField(PDF.ESE.empleadoPrivado, "X", true);
            form.setField(PDF.ESE.negocioPropio, "X", true);
            form.setField(PDF.ESE.jubilado, "X", true);
            form.setField(PDF.ESE.laborCampo, "X", true);
            form.setField(PDF.ESE.empleadoComunal, "X", true);
            form.setField(PDF.ESE.otroOcupacion, " ", true);
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
            form.setField(PDF.ESE.recursosSi, "X", true);
            form.setField(PDF.ESE.recursosNo, "X", true);
            form.setField(PDF.ESE.mototaxi, "X", true);
            form.setField(PDF.ESE.bicicleta, "X", true);
            form.setField(PDF.ESE.motocicleta, "X", true);
            form.setField(PDF.ESE.caminando, "X", true);
            form.setField(PDF.ESE.autoPropio, "X", true);
            form.setField(PDF.ESE.autoFamiliar, "X", true);
            form.setField(PDF.ESE.autoAmigos, "X", true);
            form.setField(PDF.ESE.firmaAlumno,nombreCompletoSeguro(alumno.getNombre(), alumno.getApellido()),true);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
