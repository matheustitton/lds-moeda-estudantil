package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.aluno.AlunoCreateRequestDTO;
import com.moeda.estudantil.dto.aluno.AlunoDTO;
import com.moeda.estudantil.dto.aluno.AlunoUpdateRequestDTO;
import com.moeda.estudantil.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid AlunoCreateRequestDTO dto) {
        service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<AlunoDTO> buscar(@RequestParam Long id) {
        AlunoDTO resposta = service.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid AlunoUpdateRequestDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
