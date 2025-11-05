package com.moeda.estudantil.dto.pontuacao;

public record PontuacaoCreateRequestDTO(int valor, String motivo, Long doadorId, Long recebedorId) {
}
