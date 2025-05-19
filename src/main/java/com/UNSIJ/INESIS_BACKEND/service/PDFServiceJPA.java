package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.AlumnoModel;
import com.UNSIJ.INESIS_BACKEND.utils.PDF;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;

public class PDFServiceJPA {

    public static void main(String[] args) {
        generarPdf();
    }

    public static String valorSeguro(String valor, String valorPorDefecto) {
        return (valor != null && !valor.trim().isEmpty()) ? valor : valorPorDefecto;
    }

    public static String nombreCompletoSeguro(String nombre, String apellido) {
        String nombreSeguro = (nombre != null && !nombre.trim().isEmpty()) ? nombre.trim() : " ";
        String apellidoSeguro = (apellido != null && !apellido.trim().isEmpty()) ? apellido.trim() : " ";
        return (nombreSeguro + " " + apellidoSeguro).trim();
    }

    public static void generarPdf(AlumnoModel alumno){
        try {
            // Ruta del PDF base (con campos de formulario)
            PdfReader reader = new PdfReader("estudioSocioEconomico.pdf");

            // Archivo de salida
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("src/main/resources/pdfsGenerados/prueba.pdf"));

            // Campos del formulario
            AcroFields form = stamper.getAcroFields();
            form.setGenerateAppearances(true);

            // Rellenar campos
            form.setField(PDF.ESE.nombreAlumno, nombreCompletoSeguro(alumno.getNombre(), alumno.getApellido()), true);
            form.setField(PDF.ESE.carreraAlumno, valorSeguro(alumno.getCarrera().getNombreCarrera(), " "), true);
            form.setField(PDF.ESE.semestreAlumno,valorSeguro(alumno.getSemestre().getNombreSemestre(), ""), true);
            form.setField(PDF.ESE.dependeSi, "X", true);
            form.setField(PDF.ESE.dependeNo, "X", true);
            form.setField(PDF.ESE.domicilioActualAlumno, "Av. Universidad 123", true);
            form.setField(PDF.ESE.becaAlimenticiaSi, "X", true);
            form.setField(PDF.ESE.becaAlimenticiaNo, "X", true);
            form.setField(PDF.ESE.gastoMensual, "3000", true);
            form.setField(PDF.ESE.rentaCuarto, "X", true);
            form.setField(PDF.ESE.rentaCasa, "X", true);
            form.setField(PDF.ESE.viveFamiliares, "X", true);
            form.setField(PDF.ESE.numPersonaComparte, "2", true);
            form.setField(PDF.ESE.rentaMensual, "1500", true);
            form.setField(PDF.ESE.nietoComuneroSi, "X", true);
            form.setField(PDF.ESE.nietoComuneroNo, "X", true);
            form.setField(PDF.ESE.llevaCarroSi, "X", true);
            form.setField(PDF.ESE.llevaCarroNo, "X", true);
            form.setField(PDF.ESE.llevaMotocicletaSi, "X", true);
            form.setField(PDF.ESE.llevaMotocicletaNo, "X", true);
            form.setField(PDF.ESE.marcaMotocicleta, "Italika", true);
            form.setField(PDF.ESE.utilizaTelefonoSi, "X", true);
            form.setField(PDF.ESE.utilizaTelefonoNo, "X", true);
            form.setField(PDF.ESE.tieneComputadoraSi, "X", true);
            form.setField(PDF.ESE.tieneComputadoraNo, "X", true);
            form.setField(PDF.ESE.nombreTutor, "Pedro López", true);
            form.setField(PDF.ESE.parentesco, "Padre", true);
            form.setField(PDF.ESE.telOCorreo, "correo@ejemplo.com", true);
            form.setField(PDF.ESE.trabajadorSuneo, "No", true);
            form.setField(PDF.ESE.domicilioCompletoTutor, "Col. Centro, Oaxaca", true);
            form.setField(PDF.ESE.observaciones, "Ninguna", true);
            form.setField(PDF.ESE.apellidoP, "López", true);
            form.setField(PDF.ESE.apellidoM, "García", true);
            form.setField(PDF.ESE.nombreAlum, "Laura", true);
            form.setField(PDF.ESE.sexo, "Femenino", true);
            form.setField(PDF.ESE.estadoCivil, "Soltera", true);
            form.setField(PDF.ESE.carrera, "Sistemas", true);
            form.setField(PDF.ESE.telefonoAlumno, "9511234567", true);
            form.setField(PDF.ESE.emailAlumno, "laura@email.com", true);
            form.setField(PDF.ESE.lenguajeDialecto, "Zapoteco", true);
            form.setField(PDF.ESE.regionActualFamilia, "Mixteca", true);
            form.setField(PDF.ESE.distritoActualFamilia, "Nochixtlán", true);
            form.setField(PDF.ESE.municipioActualFamilia, "Asunción", true);
            form.setField(PDF.ESE.localidadActualFamilia, "Yucuquimi", true);
            form.setField(PDF.ESE.estadoActualFamilia, "Oaxaca", true);
            form.setField(PDF.ESE.telefonoActualFamilia, "9519876543", true);
            form.setField(PDF.ESE.dependeEconomicamente, "Sí", true);
            form.setField(PDF.ESE.nombreEmpresa, "Café Internet", true);
            form.setField(PDF.ESE.ingresoMensual, "2000", true);
            form.setField(PDF.ESE.domicilioTrabajo, "Centro", true);
            form.setField(PDF.ESE.aportanGasto, "Sí", true);
            form.setField(PDF.ESE.ingresoMensualPromedio, "2500", true);
            form.setField(PDF.ESE.numDependeMencionado, "3", true);
            form.setField(PDF.ESE.alimentacion, "1200", true);
            form.setField(PDF.ESE.renta, "1500", true);
            form.setField(PDF.ESE.servicios, "500", true);
            form.setField(PDF.ESE.gastoEscolar, "800", true);
            form.setField(PDF.ESE.ropa, "300", true);
            form.setField(PDF.ESE.transporte, "400", true);
            form.setField(PDF.ESE.otros, "200", true);
            form.setField(PDF.ESE.total, "4900", true);
            form.setField(PDF.ESE.propia, "No", true);
            form.setField(PDF.ESE.alquilada, "Sí", true);
            form.setField(PDF.ESE.otra, "No", true);
            form.setField(PDF.ESE.casaSola, "Sí", true);
            form.setField(PDF.ESE.condominio, "No", true);
            form.setField(PDF.ESE.vecindad, "No", true);
            form.setField(PDF.ESE.departamento, "Sí", true);
            form.setField(PDF.ESE.mamposteria, "Sí", true);
            form.setField(PDF.ESE.madera, "No", true);
            form.setField(PDF.ESE.lamina, "No", true);
            form.setField(PDF.ESE.concreto, "Sí", true);
            form.setField(PDF.ESE.otrosMaterial, "No", true);
            form.setField(PDF.ESE.agua, "Sí", true);
            form.setField(PDF.ESE.luz, "Sí", true);
            form.setField(PDF.ESE.drenaje, "Sí", true);
            form.setField(PDF.ESE.telefono, "Sí", true);
            form.setField(PDF.ESE.otrosServicios, "Internet", true);
            form.setField(PDF.ESE.numHabitan, "4", true);
            form.setField(PDF.ESE.sinEstudiosM, "No", true);
            form.setField(PDF.ESE.primariaM, "Sí", true);
            form.setField(PDF.ESE.secundariaM, "No", true);
            form.setField(PDF.ESE.bachilleratoM, "No", true);
            form.setField(PDF.ESE.tecnicoM, "No", true);
            form.setField(PDF.ESE.licenciaturaM, "No", true);
            form.setField(PDF.ESE.posgradoM, "No", true);
            form.setField(PDF.ESE.sinEstudiosP, "No", true);
            form.setField(PDF.ESE.primariaP, "Sí", true);
            form.setField(PDF.ESE.secundariaP, "Sí", true);
            form.setField(PDF.ESE.bachilleratoP, "No", true);
            form.setField(PDF.ESE.tecnicoP, "No", true);
            form.setField(PDF.ESE.licenciaturaP, "No", true);
            form.setField(PDF.ESE.posgradoP, "No", true);
            form.setField(PDF.ESE.temporal, "No", true);
            form.setField(PDF.ESE.permanente, "Sí", true);
            form.setField(PDF.ESE.comerciante, "Sí", true);
            form.setField(PDF.ESE.empleadoGobierno, "No", true);
            form.setField(PDF.ESE.empleadoPrivado, "Sí", true);
            form.setField(PDF.ESE.negocioPropio, "No", true);
            form.setField(PDF.ESE.jubilado, "No", true);
            form.setField(PDF.ESE.laborCampo, "No", true);
            form.setField(PDF.ESE.empleadoComunal, "No", true);
            form.setField(PDF.ESE.otroOcupacion, "Vendedor ambulante", true);
            form.setField(PDF.ESE.numHermanos, "3", true);
            form.setField(PDF.ESE.numHermanosEstudiando, "2", true);
            form.setField(PDF.ESE.numHermanosNoEstudian, "1", true);
            form.setField(PDF.ESE.numHermanosLicenciatura, "1", true);
            form.setField(PDF.ESE.aguaCaliente, "Sí", true);
            form.setField(PDF.ESE.refrigerador, "Sí", true);
            form.setField(PDF.ESE.estufa, "Sí", true);
            form.setField(PDF.ESE.televisor, "Sí", true);
            form.setField(PDF.ESE.lavadora, "No", true);
            form.setField(PDF.ESE.aireAcondicionado, "No", true);
            form.setField(PDF.ESE.automovilPropio, "No", true);
            form.setField(PDF.ESE.microondas, "No", true);
            form.setField(PDF.ESE.espacioEstudio, "Sí", true);
            form.setField(PDF.ESE.computadora, "Sí", true);
            form.setField(PDF.ESE.impresora, "No", true);
            form.setField(PDF.ESE.librero, "No", true);
            form.setField(PDF.ESE.mesa, "Sí", true);
            form.setField(PDF.ESE.libros, "Sí", true);
            form.setField(PDF.ESE.diccionario, "No", true);
            form.setField(PDF.ESE.caluladora, "Sí", true);
            form.setField(PDF.ESE.recursosSi, "Sí", true);
            form.setField(PDF.ESE.recursosNo, "No", true);
            form.setField(PDF.ESE.mototaxi, "No", true);
            form.setField(PDF.ESE.bicicleta, "Sí", true);
            form.setField(PDF.ESE.motocicleta, "Sí", true);
            form.setField(PDF.ESE.caminando, "No", true);
            form.setField(PDF.ESE.autoPropio, "No", true);
            form.setField(PDF.ESE.autoFamiliar, "Sí", true);
            form.setField(PDF.ESE.autoAmigos, "No", true);


            //imprime los campos encontrados en el pdf
            for (String campo : form.getFields().keySet()) {
                System.out.println("Campo encontrado: " + campo);
            }

            // Opcional: hacer los campos no editables
            stamper.setFormFlattening(true);

            stamper.close();
            reader.close();

            System.out.println("PDF generado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
