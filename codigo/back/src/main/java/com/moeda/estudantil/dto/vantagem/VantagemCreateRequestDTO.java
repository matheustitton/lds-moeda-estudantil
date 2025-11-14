package com.moeda.estudantil.dto.vantagem;

import com.moeda.estudantil.enums.ETipoVantagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VantagemCreateRequestDTO(

        @NotBlank(message = "A descrição é obrigatória.")
        String descricao,

        @NotNull(message = "O custo é obrigatório.")
        int custo,

        @NotNull(message = "O tipo é obrigatório.")
        ETipoVantagem tipo,

        @NotNull
        Long idEmpresaParceira
) {
}
