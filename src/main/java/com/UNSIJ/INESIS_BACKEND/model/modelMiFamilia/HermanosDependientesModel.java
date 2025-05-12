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
@Table(name = "hermanos_dependientes")
public class HermanosDependientesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hermanos_dependientes")
    private Long id;

    @Column(name = "nombre_hermano")
    private String nombreHermano;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "nombre_escuela")
    private String nombreEscuela;

    @Column(name = "grado")
    private String grado;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @ManyToOne
    @JoinColumn(name = "id_mi_familia")
    private MiFamiliaModel miFamilia;
}
