package com.moeda.estudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moeda.estudantil.model.EmpresaFiliada;

@Repository
public interface EmpresaFiliadaRepository extends JpaRepository<EmpresaFiliada, Long> {
    
}
