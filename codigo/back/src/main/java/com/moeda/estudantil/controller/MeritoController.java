package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.merito.MeritoCreateRequestDTO;
import com.moeda.estudantil.service.MeritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meritos")
public class MeritoController {

    private MeritoService service;

    public MeritoController(MeritoService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Void> criar(@RequestBody @Valid MeritoCreateRequestDTO dto) {
        service.criar(dto);
        return ResponseEntity.ok().build();
    }
}
