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

import com.moeda.estudantil.dto.empresaFiliada.EmpresaFiliadaCreateRequestDTO;
import com.moeda.estudantil.dto.empresaFiliada.EmpresaFiliadaResponseDTO;
import com.moeda.estudantil.service.EmpresaFiliadaService;

@Controller
@RequestMapping("/api/empresas-filiadas")
public class EmpresaFiliadaController {

    private final EmpresaFiliadaService empresaFiliadaService;

    public EmpresaFiliadaController(EmpresaFiliadaService empresaFiliadaService) {
        this.empresaFiliadaService = empresaFiliadaService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmpresaFiliadaCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        empresaFiliadaService.create(empresaFiliadaCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaFiliadaResponseDTO> update(Long id, @RequestBody EmpresaFiliadaCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        EmpresaFiliadaResponseDTO updated = empresaFiliadaService.update(id, empresaFiliadaCreateRequestDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaFiliadaResponseDTO> delete(Long id) {
        EmpresaFiliadaResponseDTO deleted = empresaFiliadaService.delete(id);
        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping
    public ResponseEntity<EmpresaFiliadaResponseDTO[]> findAll() {
        EmpresaFiliadaResponseDTO[] empresas = empresaFiliadaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaFiliadaResponseDTO> findById(Long id) {
        EmpresaFiliadaResponseDTO empresa = empresaFiliadaService.findById(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

}
