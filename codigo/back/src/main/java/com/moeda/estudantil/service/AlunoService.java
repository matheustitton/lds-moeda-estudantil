package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.aluno.AlunoCreateRequestDTO;
import com.moeda.estudantil.dto.aluno.AlunoUpdateRequestDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.InstituicaoEnsino;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.InstituicaoEnsinoRepository;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private AlunoRepository repository;
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public AlunoService(AlunoRepository repository, InstituicaoEnsinoRepository instituicaoEnsinoRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.instituicaoEnsinoRepository = instituicaoEnsinoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void criar(AlunoCreateRequestDTO dto) {
        InstituicaoEnsino instituicaoEnsino = instituicaoEnsinoRepository.findById(dto.instituicaoEnsino()).orElseThrow(() -> new RuntimeException("Instituição de ensino não encontrada."));
        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Aluno aluno = new Aluno(dto.nome(), dto.rg(), dto.cpf(), dto.curso(), instituicaoEnsino, dto.email(), senhaCriptografada);
        repository.save(aluno);
    }

    // @Transactional
    // public void atualizar(Long id, AlunoUpdateRequestDTO dto) {
           
    // }
}
