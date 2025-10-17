package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.instituicao_ensino.InstituicaoEnsinoResponseDTO;
import com.moeda.estudantil.service.InstituicaoEnsinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/instituicoes-ensino")
public class InstituicaoEnsinoController {

    private InstituicaoEnsinoService service;

    public InstituicaoEnsinoController(InstituicaoEnsinoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoEnsinoResponseDTO>> buscarTodas() {
        List<InstituicaoEnsinoResponseDTO> resposta = service.buscarTodas();
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
