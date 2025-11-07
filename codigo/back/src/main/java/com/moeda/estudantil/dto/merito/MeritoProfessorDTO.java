package com.moeda.estudantil.dto.merito;

import com.moeda.estudantil.dto.aluno.AlunoDTO;

import java.time.OffsetDateTime;

public record MeritoProfessorDTO(
        Long id,
        int valor,
        String motivo,
        AlunoDTO aluno,
        OffsetDateTime data
) {
}
