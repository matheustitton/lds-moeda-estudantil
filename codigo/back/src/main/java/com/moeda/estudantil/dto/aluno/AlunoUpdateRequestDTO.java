package com.moeda.estudantil.dto.aluno;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoUpdateRequestDTO(

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "O curso é obrigatório.")
        String curso,

        @NotNull(message = "A instituição de ensino é obrigatória.")
        Long instituicaoEnsino,

        @NotBlank(message = "O e-mail é obrigatório.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String senha
) {
}
