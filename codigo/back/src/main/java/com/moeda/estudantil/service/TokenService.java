package com.moeda.estudantil.service;

import com.moeda.estudantil.model.Usuario;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    private static final long EXPIRACAO_TOKEN_ACESSO = 604800;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    private JwtClaimsSet gerarClaims(Usuario usuario, long expiracao) {
        Instant agora = Instant.now();

        return JwtClaimsSet.builder()
                .issuer("lds-moeda-estudantil")
                .subject(usuario.getId().toString())
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(expiracao))
                .claim("tipo", usuario.getTipoUsuario())
                .build();
    }

    public String gerarTokenAcesso(Usuario usuario) {
        var claims = gerarClaims(usuario, EXPIRACAO_TOKEN_ACESSO);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public long getExpiracaoTokenAcesso() {
        return EXPIRACAO_TOKEN_ACESSO;
    }
}
