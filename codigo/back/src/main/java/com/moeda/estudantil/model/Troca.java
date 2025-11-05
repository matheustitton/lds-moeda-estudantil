package com.moeda.estudantil.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "troca")
@Getter
@Setter
public class Troca extends Transacao {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private int valor;

    @Column (nullable = false)
    private LocalDateTime data;

    @ManyToAny
    @JoinColumn (name = "doador_id", nullable = false)
    private Usuario doador;

    @ManyToAny
    @JoinColumn (name = "recebedor_id", nullable = false)
    private Usuario recebedor;

    // @Column (nullable = false, length = 255)
    // @Getter @Setter
    // private Vantagem vantagem;

    public Troca(int valor, Usuario doador, Usuario recebedor) {
        super(valor, doador, recebedor);
        // this.vantagem = vantagem;
    }

    @PrePersist
    protected void onCreate() {
        this.data = LocalDateTime.now();
    }

}
