package com.moeda.estudantil.model;

import com.moeda.estudantil.enums.ETipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "alunos")
public class Aluno extends Usuario {

    @Column(nullable = false, length = 150)
    @Getter
    private String nome;

    @Column(nullable = false, length = 14)
    private String rg;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String curso;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private InstituicaoEnsino instituicao;

    @Column(nullable = false)
    private int saldo;

    public Aluno() {
        super();
    }

    public Aluno(String nome, String rg, String cpf, String curso, InstituicaoEnsino instituicao, String email, String senha) {
        super(email, senha, ETipoUsuario.ALUNO);
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.curso = curso;
        this.instituicao = instituicao;
    }
}
