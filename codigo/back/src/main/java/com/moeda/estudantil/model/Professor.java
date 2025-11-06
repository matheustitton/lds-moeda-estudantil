package com.moeda.estudantil.model;
import java.util.List;

// import com.moeda.estudantil.dto.professor.ProfessorDTO;
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

    // @Column(nullable = false)
    // private int MAX_PONTOS = 1000;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private InstituicaoEnsino instituicao;

    // @OneToMany(mappedBy = "professor_id", cascade = CascadeType.ALL)
    // private List<Pontuacao> transacoes;

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
}
