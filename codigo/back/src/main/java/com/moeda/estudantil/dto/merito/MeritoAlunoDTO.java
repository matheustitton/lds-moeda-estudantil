package com.moeda.estudantil.dto.merito;

import com.moeda.estudantil.dto.professor.ProfessorDTO;

import java.time.OffsetDateTime;

public record MeritoAlunoDTO(
        Long id,
        int valor,
        String motivo,
        ProfessorDTO professor,
        OffsetDateTime data) {
}
