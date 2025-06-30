package com.UNSIJ.INESIS_BACKEND.model;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @NotNull
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

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
    private CatCarrera carrera;

    @ManyToOne
    @JoinColumn(name = "id_semestre", referencedColumnName = "id_cat_semestre")
    @NotNull
    private CatSemestre semestre;

    @ManyToOne
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_cat_sexo")
    @NotNull
    private CatSexo sexo;

    @ManyToOne
    @JoinColumn(name = "id_cat_grupo")
    private CatGrupo grupo;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

    @OneToOne(mappedBy = "alumno")
    private MisDatos misDatos;

    @OneToOne(mappedBy = "alumno")
    private MiTutor miTutor;

    @OneToOne(mappedBy = "alumno")
    private GastosIngresosFamiliares gastosIngresosFamiliares;

    @OneToOne(mappedBy = "alumno")
    private MiFamilia miFamilia;

    @Column(name = "estudio_completo")
    private Boolean estudioCompleto;

    @Column(name = "estado_revision")
    private Boolean estadoRevision;

    @Column(name = "observaciones")
    private String observaciones;

    @Transient
    private FechasRegistradas fechaRegistrada;

    public String getNombreCompleto() {
        return apellidoPaterno + " " + apellidoMaterno + " " + nombre;
    }
}