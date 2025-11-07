package com.moeda.estudantil.repository;

import com.moeda.estudantil.model.Merito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeritoRepository extends JpaRepository<Merito, Long> {
    List<Merito> findByAluno_Id(Long id);

    List<Merito> findByProfessor_Id(Long id);
}
