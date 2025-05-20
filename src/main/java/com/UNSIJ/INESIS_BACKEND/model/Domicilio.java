package com.UNSIJ.INESIS_BACKEND.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private Long idDomicilio;

    @NotNull
    private String estado;

    @NotNull
    private String municipio;

    @NotNull
    private String localidad;

    private String colonia;

    @NotNull
    private String calle;

    @NotNull
    private String numero;

    @NotNull
    private String cp;

    // @OneToOne(mappedBy = "domicilio", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private MisDatos misDatos;
}
