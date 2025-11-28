package com.moeda.estudantil.dto.troca;

import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.enums.EStatusTroca;

import java.time.OffsetDateTime;

public record TrocaAlunoDTO(
        Long id,
        VantagemDTO vantagem,
        EStatusTroca status,
        OffsetDateTime dataHora
) {
}
