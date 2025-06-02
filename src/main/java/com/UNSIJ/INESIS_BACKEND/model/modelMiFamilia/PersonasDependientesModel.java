package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "personas_dependientes")
public class PersonasDependientesModel {

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
    private MiFamiliaModel miFamilia;
}
