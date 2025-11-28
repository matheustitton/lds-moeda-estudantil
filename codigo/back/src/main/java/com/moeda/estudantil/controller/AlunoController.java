package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.aluno.AlunoCreateRequestDTO;
import com.moeda.estudantil.dto.aluno.AlunoDTO;
import com.moeda.estudantil.dto.aluno.AlunoUpdateRequestDTO;
import com.moeda.estudantil.dto.merito.MeritoAlunoDTO;
import com.moeda.estudantil.dto.troca.TrocaAlunoDTO;
import com.moeda.estudantil.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestParam Long id, @RequestBody @Valid AlunoUpdateRequestDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/meritos")
    public ResponseEntity<List<MeritoAlunoDTO>> buscarMeritos(@PathVariable Long id) {
        List<MeritoAlunoDTO> resposta = service.buscarMeritos(id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping("/{id}/trocas")
    public ResponseEntity<List<TrocaAlunoDTO>> buscarTrocas(@PathVariable Long id) {
        List<TrocaAlunoDTO> resposta = service.buscarTrocas(id);
        return ResponseEntity.ok(resposta);
    }
}
