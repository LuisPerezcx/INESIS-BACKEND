package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FechasRegistradasRepository extends JpaRepository<FechasRegistradas, Long> {
    Optional<FechasRegistradas> findByCarrera_Id(Long carrera);
    List<FechasRegistradas> findByCarrera_IdIn(Set<Long> ids);
}
