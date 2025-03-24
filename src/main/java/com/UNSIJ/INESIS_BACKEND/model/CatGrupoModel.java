package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_grupo")
public class CatGrupoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_grupo")
    private Long id;

    @Column(name = "nombre_grupo")
    private String nombreGrupo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cat_carrera", referencedColumnName = "id_cat_carrera")
    CatCarreraModel catCarreraModel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cat_semestre", referencedColumnName = "id_cat_semestre")
    CatSemestreModel catSemestreModel;
}
