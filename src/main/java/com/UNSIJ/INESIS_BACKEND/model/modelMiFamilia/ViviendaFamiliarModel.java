package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "ViviendaFamiliar")
public class ViviendaFamiliarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vivienda_Familiar")
    private Long id;

    @Column(name = "num_personas_habitan")
    private Integer numPersonasHabitan;

    @Column(name = "servicios_otro")
    private String serviciosOtro;

    @ManyToOne
    @JoinColumn(name = "id_cat_situacion_vivienda")
    private CatSituacionViviendaModel situacionVivienda;

    @ManyToOne
    @JoinColumn(name = "id_cat_tipo_vivienda")
    private CatTipoViviendaModel tipoVivienda;

    @ManyToOne
    @JoinColumn(name = "id_cat_material_vivienda")
    private CatMaterialViviendaModel materialVivienda;
}
