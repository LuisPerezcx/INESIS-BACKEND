package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gastos_ingresos")
public class GastosIngresos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gatos_ingresos")
    private Long idGatosIngresos;

    @Column(name = "gasto_mensual")
    private Double gastoMensual;

    @Column(name = "depende_economicamente")
    private String dependeEconomicamente;

    @Column(name = "nombre_quien_dependes")
    private String nombreQuienDependes;

    @Column(name = "solicita_beca_alimenticia")
    private String solicitaBecaAlimenticia;

    @Column(name = "trabajo_tipo")
    private String trabajoTipo;

    @Column(name = "ocupacion")
    private String ocupacion;

    @Column(name = "otro")
    private String otro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trabajo")
    private Trabajo trabajo;

    @OneToOne(mappedBy = "gastosIngresos")
    private MisDatos misDatos;

}
