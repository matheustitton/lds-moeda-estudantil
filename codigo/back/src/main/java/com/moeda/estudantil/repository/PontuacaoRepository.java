package com.moeda.estudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moeda.estudantil.model.Merito;
import com.moeda.estudantil.model.Pontuacao;

public interface PontuacaoRepository extends JpaRepository<Merito, Long> {
}
