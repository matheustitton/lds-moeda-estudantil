package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.aluno.AlunoDTO;
import com.moeda.estudantil.dto.instituicao_ensino.InstituicaoEnsinoResponseDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.InstituicaoEnsino;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.InstituicaoEnsinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoEnsinoService {

    private InstituicaoEnsinoRepository repository;
    private AlunoRepository alunoRepository;

    public InstituicaoEnsinoService(InstituicaoEnsinoRepository repository, AlunoRepository alunoRepository) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
    }

    public List<InstituicaoEnsinoResponseDTO> buscarTodas() {
        return repository.findAll().stream().map(InstituicaoEnsino::toDto).toList();
    }

    public List<AlunoDTO> buscarAlunos(Long id) {
        List<Aluno> alunos = alunoRepository.findByInstituicao_Id(id);
        return alunos.stream().map(Aluno::toDto).toList();
    }
}
