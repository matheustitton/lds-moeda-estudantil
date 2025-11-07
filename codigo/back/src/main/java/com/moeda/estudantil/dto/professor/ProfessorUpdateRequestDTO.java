package com.moeda.estudantil.dto.professor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorUpdateRequestDTO(
    @NotBlank(message = "O nome é obrigatório.")
    String nome,

    @NotBlank(message = "O departamento é obrigatório.")
    String departamento,

    @NotNull(message = "A instituição de ensino é obrigatória.")
    Long instituicaoEnsino,

    @NotBlank(message = "O e-mail é obrigatório.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    String senha
) {
    
}
