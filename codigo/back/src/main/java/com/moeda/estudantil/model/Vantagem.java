package com.moeda.estudantil.model;

import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.enums.ETipoVantagem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vantagens")
@Getter
@Setter
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false)
    private int custo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ETipoVantagem tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmpresaParceira empresaParceira;

    @Column(length = 1000)
    private String imagemUrl; 

    public Vantagem() {}

    public Vantagem(String descricao, int custo, ETipoVantagem tipo, EmpresaParceira empresaParceira, String imagemUrl) {
        this.descricao = descricao;
        this.custo = (custo <= 0) ? 0 : custo;
        this.tipo = tipo;
        this.empresaParceira = empresaParceira;
        this.imagemUrl = imagemUrl;
    }

    public void setCusto(int custo) {
        if (custo <= 0)
            throw new IllegalArgumentException("O custo da vantagem não pode ser negativo");
        this.custo = custo;
    }

    public VantagemDTO toDto() {
        return new VantagemDTO(id, descricao, custo, tipo, empresaParceira.toDto(), imagemUrl);
    }
}
