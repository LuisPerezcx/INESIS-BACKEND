package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "codigo_postal")
public class CodigoPostal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_postal")
    private Long id;

    @NotBlank
    @Column(name = "codigo_postal", nullable = false, length = 5)
    private String codigoPostal; // d_codigo

    @NotBlank
    @Column(name = "nombre_asentamiento", nullable = false)
    private String nombreAsentamiento; // d_asenta

    @NotBlank
    @Column(name = "nombre_municipio", nullable = false)
    private String nombreMunicipio; // d_mnpio

    @NotBlank
    @Column(name = "nombre_estado", nullable = false)
    private String nombreEstado; // d_estado

    @NotNull
    @Column(nullable = false)
    private boolean active = true;
}