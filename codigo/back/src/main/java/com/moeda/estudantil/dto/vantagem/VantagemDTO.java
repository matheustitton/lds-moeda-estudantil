package com.moeda.estudantil.dto.vantagem;

import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraDTO;
import com.moeda.estudantil.enums.ETipoVantagem;

public record VantagemDTO(
        Long id,
        String descricao,
        int custo,
        ETipoVantagem tipo,
        EmpresaParceiraDTO empresaParceira
) {
}
