package com.UNSIJ.INESIS_BACKEND.repository;
import com.UNSIJ.INESIS_BACKEND.model.GastosIngresosFamiliares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GastosIngresosFamiliaresRepository extends JpaRepository<GastosIngresosFamiliares, Long> {
}
