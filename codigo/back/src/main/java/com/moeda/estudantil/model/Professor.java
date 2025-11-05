package com.moeda.estudantil.model;
import com.moeda.estudantil.dto.professor.ProfessorDTO;
import com.moeda.estudantil.enums.ETipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professor")
@Inheritance(strategy = InheritanceType.JOINED)
public class Professor extends Usuario {
    
    @Column(nullable = false, length = 150)
    @Getter @Setter
    private String nome;

    @Column(nullable = false, length = 150)
    @Getter @Setter
    private String departamento;

    @Column(nullable = false, unique = true, length = 11)
    @Getter @Setter
    private String cpf;

    @Column(nullable = false)
    @Getter @Setter
    private static int MAX_PONTOS = 1000;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    @Setter
    private InstituicaoEnsino instituicao;

    // @ManyToOne
    // @JoinColumn(name = "transacao", nullable = false)
    // @Setter
    // private Trasacao[] transacoes;

    @Column(nullable = false)
    private int saldo;

    public Professor() {
        super();
    }

    public Professor(String nome, String departamento, String cpf, InstituicaoEnsino instituicao, String email, String senha) {
        super(email, senha, ETipoUsuario.ALUNO);
        this.nome = nome;
        this.departamento = departamento;
        this.cpf = cpf;
        this.instituicao = instituicao;
        this.saldo = MAX_PONTOS;
    }

    public ProfessorDTO toDto() {
        return new ProfessorDTO(
            id, nome, departamento, cpf, instituicao.toDto(), saldo, email
        );
    }

}
