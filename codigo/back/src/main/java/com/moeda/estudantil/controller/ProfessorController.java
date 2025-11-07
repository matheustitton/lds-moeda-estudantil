package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.merito.MeritoProfessorDTO;
import com.moeda.estudantil.dto.professor.ProfessorCreateRequestDTO;
import com.moeda.estudantil.dto.professor.ProfessorUpdateRequestDTO;
import com.moeda.estudantil.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping("/{id}/meritos")
    public ResponseEntity<List<MeritoProfessorDTO>> buscarMeritos(@PathVariable Long id){
        List<MeritoProfessorDTO> resposta = service.buscarMeritos(id);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
}
