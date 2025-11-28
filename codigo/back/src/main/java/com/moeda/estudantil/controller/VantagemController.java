package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.vantagem.VantagemCreateRequestDTO;
import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.dto.vantagem.VantagemUpdateRequestDTO;
import com.moeda.estudantil.service.VantagemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/api/vantagens")
public class VantagemController {

    private VantagemService service;

    public VantagemController(VantagemService service) {
        this.service = service;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Void> criar(
            @RequestPart("dto") @Valid VantagemCreateRequestDTO dto,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem
    ) {
        VantagemCreateRequestDTO dtoComImagem = new VantagemCreateRequestDTO(
                dto.descricao(),
                dto.custo(),
                dto.tipo(),
                dto.idEmpresaParceira(),
                imagem
        );
        service.criar(dtoComImagem, imagem);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestPart("dto") @Valid VantagemUpdateRequestDTO dto,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem
    ) {
        VantagemUpdateRequestDTO dtoComImagem = new VantagemUpdateRequestDTO(
                dto.descricao(),
                dto.custo(),
                dto.tipo(),
                imagem
        );
        service.atualizar(id, dtoComImagem);
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
