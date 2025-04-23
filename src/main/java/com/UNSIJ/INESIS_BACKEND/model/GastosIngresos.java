package com.UNSIJ.INESIS_BACKEND.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import jakarta.persistence.*;
import lombok.Data;

@Data  
@Entity
@Table(name = "gatos_ingresos")
public class GastosIngresos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gatos_ingresos")
    private Long idGatosIngresos;

    @Column(name = "gasto_mensual")
    private Double gastoMensual;

    @Column (name = "depende_economicamente")
    private String dependeEconomicamente;

    @Column(name = "nombre_quien_dependes")
    private String nombreQuienDependes;

    // @Column(name = "ingreso_mensual")
    // private Double ingresoMensual;

    @Column(name = "solicita_beca_alimenticia")
    private Boolean solicitaBecaAlimenticia;

    @Column(name = "trabajo_temporal")
    private Boolean trabajoTemporal;

    @Column(name = "ocupacion")
    private String ocupacion;

    @Column(name = "otro")
    private String otro;

    @OneToMany(mappedBy = "gastosIngresos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trabajo> trabajos = new ArrayList<>();

}
