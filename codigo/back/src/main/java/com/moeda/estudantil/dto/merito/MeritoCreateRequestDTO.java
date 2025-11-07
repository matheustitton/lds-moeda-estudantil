package com.moeda.estudantil.dto.merito;

public record MeritoCreateRequestDTO(
        int valor,
        String motivo,
        Long idProfessor,
        Long idAluno) {
}
