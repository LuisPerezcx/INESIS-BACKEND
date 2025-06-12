package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

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

    @Column(name = "parentesco")
    private String parentesco;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @ManyToOne
    @JoinColumn(name = "id_mi_familia")
    private MiFamilia miFamilia;
}
