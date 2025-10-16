package com.moeda.estudantil.dto.login;

import com.moeda.estudantil.dto.token.TokenDTO;

public record LoginResponseDTO(String nome, String perfil, TokenDTO tokenAcesso) {
}
