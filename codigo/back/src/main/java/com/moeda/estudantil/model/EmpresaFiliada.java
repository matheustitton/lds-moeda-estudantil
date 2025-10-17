package com.moeda.estudantil.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "empresa_filiada")
@Getter
public class EmpresaFiliada extends Juridica {

    public EmpresaFiliada(String cnpj, String razaoSocial) {
        super(cnpj, razaoSocial);
    }

    public EmpresaFiliada() {
        super("", "");
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

}
