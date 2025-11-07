package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.merito.MeritoProfessorDTO;
import com.moeda.estudantil.dto.professor.ProfessorDTO;
import com.moeda.estudantil.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscar(@PathVariable Long id) {
        ProfessorDTO resposta = service.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping("/{id}/meritos")
    public ResponseEntity<List<MeritoProfessorDTO>> buscarMeritos(@PathVariable Long id) {
        List<MeritoProfessorDTO> resposta = service.buscarMeritos(id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
