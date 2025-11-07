package com.moeda.estudantil.model;
import java.util.List;

<<<<<<< HEAD
import com.moeda.estudantil.dto.professor.ProfessorDTO;
=======
// import com.moeda.estudantil.dto.professor.ProfessorDTO;
>>>>>>> 817da6e7962984c34477cddd659fd7ac177dadff
import com.moeda.estudantil.enums.ETipoUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professor")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
<<<<<<< HEAD
public class Professor extends Usuario {
=======
public class Professor extends Usuario { 
>>>>>>> 817da6e7962984c34477cddd659fd7ac177dadff
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 150)
    private String departamento;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

<<<<<<< HEAD
    @Column(nullable = false)
    private int MAX_PONTOS = 1000;
=======
    // @Column(nullable = false)
    // private int MAX_PONTOS = 1000;
>>>>>>> 817da6e7962984c34477cddd659fd7ac177dadff

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private InstituicaoEnsino instituicao;

<<<<<<< HEAD
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Merito> transacoes;
=======
    // @OneToMany(mappedBy = "professor_id", cascade = CascadeType.ALL)
    // private List<Pontuacao> transacoes;
>>>>>>> 817da6e7962984c34477cddd659fd7ac177dadff

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
<<<<<<< HEAD
        this.saldo = MAX_PONTOS;
        this.transacoes = List.of();
    }

    public ProfessorDTO toDto() {
        return new ProfessorDTO(
            id, nome, departamento, cpf, instituicao.toDto(), saldo, email
        );
    }

    public void adicionarTransacao(Merito pontuacao) {
        this.transacoes.add(pontuacao);
        this.saldo -= pontuacao.getValor();
    }

=======
        // this.saldo = MAX_PONTOS;
        // this.transacoes = List.of();
    }

    // public ProfessorDTO toDto() {
    //     return new ProfessorDTO(
    //         id, nome, departamento, cpf, instituicao.toDto(), saldo, email
    //     );
    // }

    // public void adicionarTransacao(Pontuacao transacao) {
    //     this.transacoes.add(transacao);
    //     this.saldo -= transacao.getValor();
>>>>>>> 817da6e7962984c34477cddd659fd7ac177dadff
}
