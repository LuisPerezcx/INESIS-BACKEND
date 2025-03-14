package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import org.springframework.data.jpa.repository.JpaRepository;

//LA INTERFAZ SIEMPRE DEBE EXTENDER DE JpaRepository
public interface EjemploRepository extends JpaRepository<Ejemplo, Long> {
}
