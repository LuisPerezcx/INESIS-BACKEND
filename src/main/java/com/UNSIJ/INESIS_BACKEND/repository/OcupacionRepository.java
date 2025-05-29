package com.UNSIJ.INESIS_BACKEND.repository;
import com.UNSIJ.INESIS_BACKEND.model.Ocupacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcupacionRepository extends JpaRepository<Ocupacion, Long> {
}

