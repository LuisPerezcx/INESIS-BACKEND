package com.UNSIJ.INESIS_BACKEND.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "mis_datos")
public class MisDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mis_datos")  
    private Long id;
    
    @NotNull
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @NotNull
    @Column(name = "carrera")
    private String carrera;
    
    @NotNull
    @Column(name = "semestre")
    private String semestre;

    @NotNull
    @Column(name = "recursos_suficientes")
    private Boolean recursosSuficientes;

    @NotNull
    @Column(name = "familiar_comunero")
    private Boolean familiarComunero;

    @NotNull
    @Column(name = "utiliza_celular")
    private Boolean utilizaCelular;

    @NotNull
    @Column(name = "tiene_computadora")
    private Boolean tieneComputadora;

    @NotNull
    @Column(name = "idioma")
    private String idioma;


    @OneToMany
    @JoinColumn(name = "idTransporte")
    private List<Transporte> transporte;
}
