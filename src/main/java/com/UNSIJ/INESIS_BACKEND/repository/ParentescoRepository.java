package com.UNSIJ.INESIS_BACKEND.repository;
import com.UNSIJ.INESIS_BACKEND.model.ParentescoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentescoRepository extends JpaRepository<ParentescoModel, Long> {
}

