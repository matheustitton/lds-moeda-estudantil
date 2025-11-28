package com.moeda.estudantil.model;

import com.moeda.estudantil.dto.troca.TrocaAlunoDTO;
import com.moeda.estudantil.dto.troca.TrocaDTO;
import com.moeda.estudantil.enums.EStatusTroca;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "troca")
@Getter
@Setter
public class Troca {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vantagem_id", nullable = false)
    private Vantagem vantagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatusTroca status = EStatusTroca.AGUARDANDO_RESGATE;

    @Column(name = "data_hora")
    private OffsetDateTime dataHora = OffsetDateTime.now();

    public Troca() {
    }

    public Troca(Aluno aluno, Vantagem vantagem) {
        if (!aluno.novaTroca(vantagem.getCusto()))
            throw new RuntimeException("Saldo insuficiente.");

        this.aluno = aluno;
        this.vantagem = vantagem;
    }

    public boolean cancelar() {
        if (status == EStatusTroca.AGUARDANDO_RESGATE) {
            status = EStatusTroca.CANCELADA;
            return true;
        }

        return false;
    }

    public TrocaDTO toDto() {
        return new TrocaDTO(
                id,
                aluno.toDto(),
                vantagem.toDto(),
                status,
                dataHora
        );
    }

    public TrocaAlunoDTO toTrocaAlunoDto() {
        return new TrocaAlunoDTO(
                id,
                vantagem.toDto(),
                status,
                dataHora
        );
    }
}