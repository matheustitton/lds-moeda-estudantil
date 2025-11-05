package com.moeda.estudantil.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moeda.estudantil.dto.pontuacao.PontuacaoCreateRequestDTO;
import com.moeda.estudantil.service.PontuacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
    
    private PontuacaoService service;

    public TransacaoController(PontuacaoService service) {
        this.service = service;
    }

    @PostMapping("/doar")
    public void criarPontuacao(@RequestBody @Valid PontuacaoCreateRequestDTO dto) {
        service.criar(dto);
    }

}
