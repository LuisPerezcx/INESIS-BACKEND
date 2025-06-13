package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ViviendaFamiliar")
public class ViviendaFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vivienda_Familiar")
    private Long id;

    @Column(name = "num_personas_habitan")
    private Integer numPersonasHabitan;

    @Column(name = "servicios_otro")
    private String serviciosOtro;

    @ManyToOne
    @JoinColumn(name = "id_servicio_otro")
    private CatServiciosOtro servicioOtro;

    @ManyToOne
    @JoinColumn(name = "id_cat_situacion_vivienda")
    private CatSituacionVivienda situacionVivienda;

    @ManyToOne
    @JoinColumn(name = "id_cat_tipo_vivienda")
    private CatTipoVivienda tipoVivienda;

    @ManyToOne
    @JoinColumn(name = "id_cat_material_vivienda")
    private CatMaterialVivienda materialVivienda;
}
