package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FechasRegistradasRepository extends JpaRepository<FechasRegistradas, Long> {
    Optional<FechasRegistradas> findByCarrera_Id(Long carrera);
}
