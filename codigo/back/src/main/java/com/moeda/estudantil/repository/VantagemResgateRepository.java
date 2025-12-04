package com.moeda.estudantil.repository;

import com.moeda.estudantil.model.VantagemResgate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VantagemResgateRepository extends JpaRepository<VantagemResgate, Long> {
    Optional<VantagemResgate> findByToken(String token);
}
