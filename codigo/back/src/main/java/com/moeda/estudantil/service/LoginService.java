package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.login.LoginRequestDTO;
import com.moeda.estudantil.dto.login.LoginResponseDTO;
import com.moeda.estudantil.dto.token.TokenDTO;
import com.moeda.estudantil.model.Usuario;
import com.moeda.estudantil.repository.UsuarioRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginService(UsuarioRepository repository, BCryptPasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Optional<Usuario> usuario = repository.findByEmail(dto.email());

        if (usuario.isEmpty() || !usuario.get().isSenhaCorreta(dto.senha(), passwordEncoder))
            throw new BadCredentialsException("Usuário ou senha incorretos.");

        String tokenAcesso = tokenService.gerarTokenAcesso(usuario.get());

        return new LoginResponseDTO(
                new TokenDTO(tokenAcesso, tokenService.getExpiracaoTokenAcesso())
        );
    }
}
