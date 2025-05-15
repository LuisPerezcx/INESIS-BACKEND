package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradasModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FechasRegistradasRepository extends JpaRepository<FechasRegistradasModel, Long> {
    Optional<FechasRegistradasModel> findByCarrera_Id(Long carrera);
}
