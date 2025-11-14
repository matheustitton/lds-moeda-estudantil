package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.vantagem.VantagemCreateRequestDTO;
import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.dto.vantagem.VantagemUpdateRequestDTO;
import com.moeda.estudantil.service.VantagemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/vantagens")
public class VantagemController {

    private VantagemService service;

    public VantagemController(VantagemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid VantagemCreateRequestDTO dto) {
        service.criar(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid VantagemUpdateRequestDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VantagemDTO> buscar(@PathVariable Long id) {
        VantagemDTO resposta = service.buscarPorId(id);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<VantagemDTO>> buscarTodas() {
        List<VantagemDTO> resposta = service.buscarTodas();
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
