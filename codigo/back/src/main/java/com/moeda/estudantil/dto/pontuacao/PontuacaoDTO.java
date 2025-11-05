package com.moeda.estudantil.dto.pontuacao;

public record PontuacaoDTO(int id, int valor, String motivo, Long doadorId, Long recebedorId) {
}
