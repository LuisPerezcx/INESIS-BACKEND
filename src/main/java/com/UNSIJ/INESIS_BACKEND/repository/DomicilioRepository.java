package com.UNSIJ.INESIS_BACKEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.Domicilio;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
    
}
