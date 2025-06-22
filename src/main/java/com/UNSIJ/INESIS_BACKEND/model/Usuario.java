package com.UNSIJ.INESIS_BACKEND.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "estatus")
    private Boolean estatus;

    @ManyToOne
    @JoinColumn(name = "id_cat_rol")
    private CatRol rol;

    @OneToOne(mappedBy = "usuario")
    @JsonManagedReference
    private Alumno alumno;

    @OneToOne(mappedBy = "usuario")
    @JsonManagedReference
    private Revisor revisor;
}
