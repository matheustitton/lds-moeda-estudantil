package com.moeda.estudantil.repository;

import com.moeda.estudantil.model.Vantagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Long> {
}
