package com.moeda.estudantil.dto.vantagem;

import com.moeda.estudantil.enums.ETipoVantagem;

public record VantagemUpdateRequestDTO(
        String descricao,
        int custo,
        ETipoVantagem tipo
) {
}
