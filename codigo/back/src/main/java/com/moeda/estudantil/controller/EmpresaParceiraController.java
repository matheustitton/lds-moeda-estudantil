package com.moeda.estudantil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraCreateRequestDTO;
import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraDTO;
import com.moeda.estudantil.service.EmpresaParceiraService;
import java.util.List;

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
    public ResponseEntity<EmpresaParceiraDTO> update(Long id, @RequestBody EmpresaParceiraCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        EmpresaParceiraDTO updated = empresaFiliadaService.update(id, empresaFiliadaCreateRequestDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        empresaFiliadaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmpresaParceiraDTO>> findAll() {
        List<EmpresaParceiraDTO> empresas = empresaFiliadaService.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaParceiraDTO> findById(@PathVariable Long id) {
        EmpresaParceiraDTO empresa = empresaFiliadaService.findById(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

}
