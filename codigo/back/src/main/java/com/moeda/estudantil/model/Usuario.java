package com.moeda.estudantil.model;

import com.moeda.estudantil.enums.ETipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    protected Long id;

    @Column(nullable = false, unique = true, length = 100)
    @Setter
    protected String email;

    @Column(nullable = false, length = 128)
    @Setter
    private String senha;

    @Column(name = "tipo_usuario", nullable = false)
    @Enumerated(EnumType.STRING)
    private ETipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String email, String senha, ETipoUsuario tipoUsuario) {
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isSenhaCorreta(String senhaEnviada, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(senhaEnviada, this.senha);
    }
}
