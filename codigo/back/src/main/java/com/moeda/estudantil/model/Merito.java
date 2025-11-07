package com.moeda.estudantil.model;

import java.time.OffsetDateTime;

import com.moeda.estudantil.dto.merito.MeritoAlunoDTO;
import com.moeda.estudantil.dto.merito.MeritoProfessorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "merito")
@Getter
@Setter
@AllArgsConstructor
public class Merito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Column(nullable = false, length = 255)
    private String motivo;

    @Column(nullable = false)
    private int valor;

    @Column(nullable = false)
    private OffsetDateTime data = OffsetDateTime.now();

    public Merito() {}

    public Merito(int valor, Professor professor, Aluno aluno, String motivo) {
        this.valor = valor;
        this.professor = professor;
        this.aluno = aluno;
        this.motivo = motivo;

        aluno.atualizarSaldo(valor);
    }

    public MeritoAlunoDTO toDtoAluno() {
        return new MeritoAlunoDTO(id, valor, motivo, professor.toDto(), data);
    }

    public MeritoProfessorDTO toDtoProfessor() {
        return new MeritoProfessorDTO(id, valor, motivo, aluno.toDto(), data);
    }
}
