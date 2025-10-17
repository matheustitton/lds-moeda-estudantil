package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.instituicao_ensino.InstituicaoEnsinoResponseDTO;
import com.moeda.estudantil.model.InstituicaoEnsino;
import com.moeda.estudantil.repository.InstituicaoEnsinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoEnsinoService {

    private InstituicaoEnsinoRepository repository;

    public InstituicaoEnsinoService(InstituicaoEnsinoRepository repository) {
        this.repository = repository;
    }

    public List<InstituicaoEnsinoResponseDTO> buscarTodas() {
        return repository.findAll().stream().map(InstituicaoEnsino::toDto).toList();
    }

}
