package com.moeda.estudantil.dto.troca;

import com.moeda.estudantil.dto.aluno.AlunoDTO;
import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.enums.EStatusTroca;

import java.time.OffsetDateTime;

public record TrocaDTO(
        Long id,
        AlunoDTO aluno,
        VantagemDTO vantagem,
        EStatusTroca status,
        OffsetDateTime dataHora
) {
}
