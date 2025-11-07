package com.moeda.estudantil.model;

import com.moeda.estudantil.dto.professor.ProfessorDTO;
import com.moeda.estudantil.enums.ETipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "professor")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Professor extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 150)
    private String departamento;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private InstituicaoEnsino instituicao;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Merito> transacoes;

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
        this.saldo = 1000;
        this.transacoes = List.of();
    }

    public ProfessorDTO toDto() {
        return new ProfessorDTO(
                id, nome, departamento, cpf, instituicao.toDto(), saldo, email
        );
    }

    /*public void adicionarTransacao(Merito pontuacao) {
        this.transacoes.add(pontuacao);
        this.saldo -= pontuacao.getValor();
    }*/

    public Merito novoMerito(Aluno aluno, int valor, String motivo) {
        if (valor > saldo)
            throw new RuntimeException("Saldo insuficiente.");

        if (valor < 0)
            throw new RuntimeException("Saldo inválido.");

        Merito merito = new Merito(valor, this, aluno, motivo);
        saldo -= valor;

        return merito;
    }

}
