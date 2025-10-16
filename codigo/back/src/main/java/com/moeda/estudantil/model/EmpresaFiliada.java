package com.moeda.estudantil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "empresa_filiada")
public class EmpresaFiliada extends Juridica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(name = "razao_social", nullable = false, length = 100)
    private String razaoSocial;

    public EmpresaFiliada(String cnpj, String razaoSocial) {
    }


}
