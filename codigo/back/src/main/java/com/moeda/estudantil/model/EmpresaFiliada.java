package com.moeda.estudantil.model;

import com.moeda.estudantil.enums.ETipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "empresa_filiada")
@Getter
public class EmpresaFiliada extends Usuario {

    @Column(nullable = false, unique = true, length = 14)
    protected String cnpj;

    @Column(name = "razao_social", nullable = false, length = 100)
    protected String razaoSocial;

    public EmpresaFiliada() {
        super();
    }

    public EmpresaFiliada(String cnpj, String razaoSocial, String email, String senha) {
        super(email, senha, ETipoUsuario.EMPRESA_PARCEIRA);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }
}
