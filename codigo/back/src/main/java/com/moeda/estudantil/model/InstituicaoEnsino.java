package com.moeda.estudantil.model;

import com.moeda.estudantil.dto.instituicao_ensino.InstituicaoEnsinoResponseDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "instituicao_ensino")
public class InstituicaoEnsino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    public InstituicaoEnsino() {}

    public InstituicaoEnsinoResponseDTO toDto() {
        return new InstituicaoEnsinoResponseDTO(
                id,
                nome
        );
    }
}
