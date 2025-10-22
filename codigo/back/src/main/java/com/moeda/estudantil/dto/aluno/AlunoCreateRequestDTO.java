package com.moeda.estudantil.dto.aluno;

import com.moeda.estudantil.enums.ETipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record AlunoCreateRequestDTO(

    @NotBlank(message = "O nome é obrigatório.")
    String nome,

    @NotBlank(message = "O RG é obrigatório.")
    String rg,

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "O CPF informado é inválido.")
    String cpf,

    @NotBlank(message = "O curso é obrigatório.")
    String curso,

    @NotNull(message = "A instituição de ensino é obrigatória.")
    Long instituicaoEnsino,

    @NotBlank(message = "O e-mail é obrigatório.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    String senha,

    @NotNull(message = "O tipo de usuário é obrigatório.")
    ETipoUsuario tipo
) {
}
