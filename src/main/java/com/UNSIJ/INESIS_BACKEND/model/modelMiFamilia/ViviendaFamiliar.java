package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ViviendaFamiliar")
public class ViviendaFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vivienda_familiar")
    private Long id;

    @Column(name = "num_personas_habitan")
    private Integer numPersonasHabitan;

    @Column(name = "servicios_otro")
    private String serviciosOtro;

    @Column(name = "region")
    private String region;

    @Column(name = "distrito")
    private String distrito;

    @OneToMany(mappedBy = "viviendaFamiliar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiciosVivienda> serviciosVivienda = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_cat_situacion_vivienda")
    private CatSituacionVivienda situacionVivienda;

    @ManyToOne
    @JoinColumn(name = "id_cat_tipo_vivienda")
    private CatTipoVivienda tipoVivienda;

    @ManyToOne
    @JoinColumn(name = "id_cat_material_vivienda")
    private CatMaterialVivienda materialVivienda;

    @OneToOne(mappedBy = "viviendaFamiliar")
    @JsonIgnore
    private MiFamilia miFamilia;
}
