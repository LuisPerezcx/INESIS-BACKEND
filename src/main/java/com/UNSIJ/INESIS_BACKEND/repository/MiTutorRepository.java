package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.MiTutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiTutorRepository extends JpaRepository<MiTutor, Long> {
    boolean existsByDomicilio_Id(Long idDomicilio);
}
