package com.moeda.estudantil.dto.empresa_parceira;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record EmpresaParceiraCreateRequestDTO(

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
