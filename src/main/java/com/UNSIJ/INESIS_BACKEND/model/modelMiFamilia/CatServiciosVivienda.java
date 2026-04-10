/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_servicio_otro")

/**
 *
 * @author Alumnos
 */
public class CatServiciosVivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicios_vivienda")
    private Long id;

    @Column(name = "nombre_servicio", nullable = false, unique = true)
    private String nombreServicio;
}
