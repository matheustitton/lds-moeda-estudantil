package com.moeda.estudantil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moeda.estudantil.dto.professor.ProfessorCreateRequestDTO;
import com.moeda.estudantil.dto.professor.ProfessorUpdateRequestDTO;
import com.moeda.estudantil.service.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    
    private ProfessorService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid ProfessorCreateRequestDTO dto) {
        service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid ProfessorUpdateRequestDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
