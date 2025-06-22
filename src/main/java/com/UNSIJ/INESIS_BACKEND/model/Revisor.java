package com.UNSIJ.INESIS_BACKEND.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "revisor")
public class Revisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revisor")
    private Long id;

    @NotNull
    @Column(name = "matricula", nullable = false)
    private String matricula;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @NotNull
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @NotNull
    @Column(name = "departamento", nullable = false)
    private String departamento;

    @NotNull
    @Column(name = "curp", unique = true)
    private String curp;

    @NotNull
    @Column(name = "correo")
    private String correo;

    @NotNull
    @Column(name = "telefono")
    private String telefono;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

}
