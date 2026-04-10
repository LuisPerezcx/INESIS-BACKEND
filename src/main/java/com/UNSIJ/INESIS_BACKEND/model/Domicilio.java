package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "domicilio")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domicilio")
    private Long id;

    @NotNull
    private String localidad;

    private String colonia;

    @NotNull
    private String calle;

    @NotNull
    private String numero;

    @NotNull
    private String cp;
}
