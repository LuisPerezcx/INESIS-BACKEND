package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
     boolean existsByCurp(String curp);

    boolean existsByMatricula(String matricula);

    boolean existsByCorreo(String correo);
}
