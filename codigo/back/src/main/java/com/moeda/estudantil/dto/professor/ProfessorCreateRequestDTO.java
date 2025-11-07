package com.moeda.estudantil.dto.professor;

public record ProfessorCreateRequestDTO(
    String nome,
    String departamento,
    String cpf,
    Long instituicaoEnsino,
    String email,
    String senha) {
}
