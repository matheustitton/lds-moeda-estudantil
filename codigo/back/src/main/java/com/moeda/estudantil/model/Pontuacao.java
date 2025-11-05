package com.moeda.estudantil.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "pontuacao")
@Getter
@Setter
public class Pontuacao extends Transacao {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private int valor;

    @Column (nullable = false, length = 255)
    private String motivo;

    @Column (nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn (name = "doador_id", nullable = false)
    private Usuario doador;

    @ManyToOne
    @JoinColumn (name = "recebedor_id", nullable = false)
    private Usuario recebedor;

    public Pontuacao(int valor, Usuario doador, Usuario recebedor, String motivo) {
        super(valor, doador, recebedor);
        this.doador = doador;
        this.recebedor = recebedor;
        this.valor = valor;
        this.motivo = motivo;
    }

    @PrePersist
    protected void onCreate() {
        this.data = LocalDateTime.now();
    }

}
