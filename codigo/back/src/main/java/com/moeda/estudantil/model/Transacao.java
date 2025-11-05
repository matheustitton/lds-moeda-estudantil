package com.moeda.estudantil.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "transacao")
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
@Getter
@Setter
public abstract class Transacao {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private int valor;

    @ManyToOne
    @JoinColumn (name = "doador_id", nullable = false)
    private Usuario doador;
    
    @ManyToOne
    @JoinColumn (name = "recebedor_id", nullable = false)
    private Usuario recebedor;

    @Column (nullable = false)
    private LocalDateTime data;

    public Transacao(int valor, Usuario doador, Usuario recebedor) {
        this.valor = valor;
        this.doador = doador;
        this.recebedor = recebedor;
    }

    @PrePersist
    protected void onCreate() {
        this.data = LocalDateTime.now();
    }

}
