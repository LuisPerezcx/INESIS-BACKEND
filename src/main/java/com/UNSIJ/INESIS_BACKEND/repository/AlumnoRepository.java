package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    boolean existsByCurp(String curp);

    boolean existsByMatricula(String matricula);

    boolean existsByCorreo(String correo);

    List<Alumno> findByCarrera_Id(Long idCarrera);

    Optional<Alumno> findByCurp(String curp);

    Optional<Alumno> findByMatricula(String matricula);
}
