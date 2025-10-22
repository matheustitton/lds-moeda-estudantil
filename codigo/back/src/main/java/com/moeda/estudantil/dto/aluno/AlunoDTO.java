package com.moeda.estudantil.dto.aluno;

import com.moeda.estudantil.dto.instituicao_ensino.InstituicaoEnsinoResponseDTO;

public record AlunoDTO(
    Long id,
    String nome,
    String rg,
    String cpf,
    String curso,
    InstituicaoEnsinoResponseDTO instituicao,
    int saldo,
    String email
) {
  
}
