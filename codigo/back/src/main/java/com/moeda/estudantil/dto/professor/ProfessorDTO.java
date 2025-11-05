package com.moeda.estudantil.dto.professor;

import com.moeda.estudantil.dto.instituicao_ensino.InstituicaoEnsinoResponseDTO;

public record ProfessorDTO(Long id,
    String nome,
    String departamento,
    String cpf,
    InstituicaoEnsinoResponseDTO instituicao,
    int saldo,
    String email
) {
    
}
