package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import com.UNSIJ.INESIS_BACKEND.model.CatParentesco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "personas_dependientes")
public class PersonasDependientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personas_dependientes")
    private Long id;

    @Column(name = "nombre_persona")
    private String nombrePersona;

    @Column(name = "edad")
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "id_cat_parentesco")
    private CatParentesco parentesco;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "id_mi_familia")
    @JsonIgnore
    private MiFamilia miFamilia;
}
