package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "cat_grupo")
public class CatGrupoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_grupo")
    private Long id;

    @Column(name = "nombre_grupo", nullable = false)
    private String nombreGrupo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cat_carrera", referencedColumnName = "id_cat_carrera")
    private CatCarrera catCarreraModel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cat_semestre", referencedColumnName = "id_cat_semestre")
    private CatSemestre catSemestreModel;

    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @ToString.Exclude
    private Alumno alumnoModel;

}
