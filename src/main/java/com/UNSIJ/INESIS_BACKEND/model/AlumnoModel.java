package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "alumno")
public class AlumnoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "apellido")
    private String apellido;

    @NotNull
    @Column(name = "curp", unique = true) 
    private String curp;

    @NotNull
    @Column(name = "correo")
    private String correo;

    @NotNull
    @Column(name = "telefono")
    private String telefono;

    @NotNull
    @Column(name = "matricula")
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "id_cat_carrera", referencedColumnName = "id_cat_carrera")
    @NotNull
    private CatCarreraModel carrera;

    @ManyToOne
    @JoinColumn(name = "id_semestre", referencedColumnName = "id_cat_semestre")
    @NotNull
    private CatSemestreModel semestre;

    @ManyToOne
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_cat_sexo")
    @NotNull
    private CatSexoModel sexo;

    @ManyToOne
    @JoinColumn(name = "id_cat_grupo")
    private CatGrupoModel grupo;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioModel usuario;
}