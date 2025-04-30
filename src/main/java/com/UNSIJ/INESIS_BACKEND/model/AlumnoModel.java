package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "alumno")
public class AlumnoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "curp")
    private String curp;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "matricula")
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "id_cat_carrera", referencedColumnName = "id_cat_carrera")
    private CatCarreraModel carrera;

    @ManyToOne
    @JoinColumn(name = "id_semestre", referencedColumnName = "id_cat_semestre")
    private CatSemestreModel semestre;

    @ManyToOne
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_cat_sexo")
    private CatSexoModel sexo;

    @Column(name = "grupo")
    private String grupo;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioModel usuario;
}
