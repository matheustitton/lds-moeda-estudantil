package com.moeda.estudantil.repository;

import com.moeda.estudantil.model.Troca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrocaRepository extends JpaRepository<Troca, Long> {
    List<Troca> findByAluno_Id(Long id);
    Troca findByAluno_IdAndVantagem_Id(Long alunoId, Long vantagemId);
}
