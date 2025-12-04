package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.troca.TrocaCreateRequestDTO;
import com.moeda.estudantil.dto.troca.TrocaDTO;
import com.moeda.estudantil.service.TrocaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trocas")
public class TrocaController {

    private TrocaService service;

    public TrocaController(TrocaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody TrocaCreateRequestDTO dto) throws Exception {
        service.criar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrocaDTO> buscarPorId(@PathVariable Long id) {
        TrocaDTO resposta = service.buscar(id);
        return ResponseEntity.ok(resposta);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
