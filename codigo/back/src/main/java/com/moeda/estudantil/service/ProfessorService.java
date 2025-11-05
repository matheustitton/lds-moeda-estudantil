package com.moeda.estudantil.service;

import org.springframework.stereotype.Service;

import com.moeda.estudantil.dto.professor.ProfessorCreateRequestDTO;
import com.moeda.estudantil.dto.professor.ProfessorDTO;
import com.moeda.estudantil.dto.professor.ProfessorUpdateRequestDTO;
import com.moeda.estudantil.model.InstituicaoEnsino;
import com.moeda.estudantil.model.Professor;
import com.moeda.estudantil.repository.InstituicaoEnsinoRepository;
import com.moeda.estudantil.repository.ProfessorRepository;
import com.moeda.estudantil.util.CriptografiaUtil;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {
    private ProfessorRepository repository;
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;
    //private TrancacaoRepository transacaoRepository;

    public ProfessorService(ProfessorRepository repository, InstituicaoEnsinoRepository instituicaoEnsinoRepository/*, TrancacaoRepository transacaoRepository*/) {
        this.repository = repository;
        this.instituicaoEnsinoRepository = instituicaoEnsinoRepository;
        //this.transacaoRepository = transacaoRepository;
    }

    private InstituicaoEnsino buscarInstituicaoEnsino(Long id) {
        return instituicaoEnsinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Instituição de ensino não encontrada."));    
    }

    private Professor buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado."));
    }

    @Transactional
    public void criar(ProfessorCreateRequestDTO dto) {
        InstituicaoEnsino instituicaoEnsino = buscarInstituicaoEnsino(dto.instituicaoEnsino());
        String senhaCriptografada = CriptografiaUtil.criptografar(dto.senha());

        Professor professor = new Professor(dto.nome(), dto.departamento(), dto.cpf(), instituicaoEnsino, dto.email(), senhaCriptografada);
        repository.save(professor);
    }

    @Transactional
    public ProfessorDTO buscar(Long id) {
        return buscarPorId(id).toDto();
    }

    @Transactional
    public void atualizar(Long id, ProfessorUpdateRequestDTO dto) {
        Professor professor = buscarPorId(id);
        InstituicaoEnsino instituicaoEnsino = buscarInstituicaoEnsino(dto.instituicaoEnsino());
        String senhaCriptografada = CriptografiaUtil.criptografar(dto.senha());

        professor.setNome(dto.nome());
        professor.setDepartamento(dto.departamento());
        professor.setInstituicao(instituicaoEnsino);
        professor.setEmail(dto.email());
        professor.setSenha(senhaCriptografada);
    }

    @Transactional
    public void excluir(Long id) {
        Professor professor = buscarPorId(id);
        repository.delete(professor);
    }


}
