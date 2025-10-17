package com.moeda.estudantil.dto.empresaFiliada;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record EmpresaFiliadaCreateRequestDTO(

        @NotBlank(message = "O CNPJ é obrigatório.")
        @CNPJ
        String cnpj,

        @NotBlank(message = "A razão social é obrigatória.")
        String razaoSocial,

        @NotBlank(message = "O e-mail é obrigatório.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String senha) {
}
