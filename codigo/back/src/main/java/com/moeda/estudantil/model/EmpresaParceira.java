package com.moeda.estudantil.model;

import com.moeda.estudantil.enums.ETipoUsuario;
import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "empresa_parceira")
@Getter
public class EmpresaParceira extends Usuario {

    @Column(nullable = false, unique = true, length = 14)
    @Setter
    protected String cnpj;

    @Column(name = "razao_social", nullable = false, length = 100)
    @Setter
    protected String razaoSocial;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vantagem> vantagens = new LinkedList<>();

    public EmpresaParceira() {
        super();
    }

    public EmpresaParceira(String cnpj, String razaoSocial, String email, String senha) {
        super(email, senha, ETipoUsuario.EMPRESA_PARCEIRA);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public EmpresaParceiraDTO toDto() {
        return new EmpresaParceiraDTO(
            id, cnpj, razaoSocial, email
        );
    }
}
