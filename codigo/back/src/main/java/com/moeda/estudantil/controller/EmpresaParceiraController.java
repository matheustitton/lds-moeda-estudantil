package com.moeda.estudantil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraCreateRequestDTO;
import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraResponseDTO;
import com.moeda.estudantil.service.EmpresaParceiraService;

@Controller
@RequestMapping("/api/empresas-parceiras")
public class EmpresaParceiraController {

    private final EmpresaParceiraService empresaFiliadaService;

    public EmpresaParceiraController(EmpresaParceiraService empresaFiliadaService) {
        this.empresaFiliadaService = empresaFiliadaService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmpresaParceiraCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        empresaFiliadaService.create(empresaFiliadaCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaParceiraResponseDTO> update(Long id, @RequestBody EmpresaParceiraCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        EmpresaParceiraResponseDTO updated = empresaFiliadaService.update(id, empresaFiliadaCreateRequestDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaParceiraResponseDTO> delete(Long id) {
        EmpresaParceiraResponseDTO deleted = empresaFiliadaService.delete(id);
        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<EmpresaParceiraResponseDTO[]> findAll() {
        EmpresaParceiraResponseDTO[] empresas = empresaFiliadaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaParceiraResponseDTO> findById(Long id) {
        EmpresaParceiraResponseDTO empresa = empresaFiliadaService.findById(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

}
