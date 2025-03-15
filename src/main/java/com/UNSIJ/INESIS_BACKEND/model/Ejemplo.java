package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
EJEMPLO DE COMO DEBERAN CREEAR SUS MODELOS
SIEMPRE SIGUIENDO ESTA ESTRUCTURA Y SUGERENCIAS
*/


@Data
@Entity
@Table(name = "ejemplo")
/*SIEMPRE DEFINIR EL NOMBRE DE LA TABLA, EL NOMBRE SIEMPRE SERA EN MINUSCULAS
EN CASO QUE SEA UN NOMBRE DE 2 PALABRAS SE SEPARA CON "_" EJEMPLO:
(name = "ejemplo_nombre")
*/
public class Ejemplo {
    /*DE LA MISMA FORMA COMO SE MENCIONÓ ARRIBA SE NOMBRARAN LOS NOMBRES DE LAS COLUMNAS
    EL NOMBRE DE LAS COLUMNAS LO DEBEN DEFINIR TAL CUAL COMO ESTA EN EL DIAGRAMA ER
    (name = "campo_ejemplo") siempre especificar el nombre de la columna cuando el nombre sea mas de 1 palabra
        *TODOS LOS IDS SE MANEJARAN COMO LONG
        *LAS RELACIONES CON OTRAS TABLAS SIEMPRE SERAN CON UNA INSTANCIA DEL MODELO DE LA TABLA NUNCA CON IDS
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejemplo")
    private Long id;

    @NotNull
    private boolean active=false;

    @NotNull
    @Column(name = "numero_ejemplo")
    private Integer numeroEjemplo;

    @Column(name = "nombre_ejemplo")
    private String nombreEjemplo;

    /*
        EJEMPLO DE RELACION CON OTRA TABLA MEDIANTE UNA INSTANCIA DE CLASE
        @NotNull
        @ManyToOne
        @JoinColumn(name = "id_tipo_producto", referencedColumnName = "id_tipo_producto")
        TipoProducto tipoProducto;
    */

}
