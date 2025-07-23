package com.UNSIJ.INESIS_BACKEND.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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
    private Boolean dependeEconomicamente;

    @Column(name = "personas_comparteCosto")
    private Double personasComparteRenta;

    @Column(name = "pago_renta_mensual")
    private Double pagoRentaMensual;

    @Column(name = "nombre_quien_dependes")
    private String nombreQuienDependes;

    @Column(name = "solicita_beca_alimenticia")
    private Boolean solicitaBecaAlimenticia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cat_tipo_trabajo")
    private CatTipoTrabajo catTipoTrabajo;

    @ManyToOne
    @JoinColumn(name = "id_cat_ocupacion")
    private Ocupacion ocupacion;

    @Column(name = "otro")
    private String otro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trabajo")
    private Trabajo trabajo;

    @OneToOne(mappedBy = "gastosIngresos")
    @ToString.Exclude
    @JsonIgnore
    private MisDatos misDatos;

}
