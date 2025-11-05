package com.moeda.estudantil.service;

import org.springframework.stereotype.Service;

import com.moeda.estudantil.dto.professor.ProfessorCreateRequestDTO;
import com.moeda.estudantil.dto.professor.ProfessorDTO;
import com.moeda.estudantil.dto.professor.ProfessorUpdateRequestDTO;
import com.moeda.estudantil.model.InstituicaoEnsino;
import com.moeda.estudantil.model.Pontuacao;
import com.moeda.estudantil.model.Professor;
import com.moeda.estudantil.repository.InstituicaoEnsinoRepository;
import com.moeda.estudantil.repository.ProfessorRepository;
import com.moeda.estudantil.util.CriptografiaUtil;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {
    private ProfessorRepository repository;
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    public ProfessorService(ProfessorRepository repository, InstituicaoEnsinoRepository instituicaoEnsinoRepository) {
        this.repository = repository;
        this.instituicaoEnsinoRepository = instituicaoEnsinoRepository;
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
    public ProfessorDTO atualizar(Long id, ProfessorUpdateRequestDTO dto) {
        Professor professor = buscarPorId(id);
        InstituicaoEnsino instituicaoEnsino = buscarInstituicaoEnsino(dto.instituicaoEnsino());
        String senhaCriptografada = CriptografiaUtil.criptografar(dto.senha());

        professor.setNome(dto.nome());
        professor.setDepartamento(dto.departamento());
        professor.setInstituicao(instituicaoEnsino);
        professor.setEmail(dto.email());
        professor.setSenha(senhaCriptografada);
        repository.save(professor);

        return professor.toDto();
    }

    @Transactional
    public void excluir(Long id) {
        Professor professor = buscarPorId(id);
        repository.delete(professor);
    }

    @Transactional
    public void doarMoedas(Long doadorId, Pontuacao pontuacao) {
        Professor doador = buscarPorId(doadorId);
        if (doador.getSaldo() < pontuacao.getValor()) {
            throw new RuntimeException("Saldo insuficiente para realizar a doação.");
        }
        if (pontuacao.getValor() <= 0) {
            throw new RuntimeException("O valor da doação deve ser maior que zero.");
        }
        doador.adicionarTransacao(pontuacao);
        repository.save(doador);
    }

    @Transactional
    public void resetarSaldo(Long professorId) {
        Professor professor = buscarPorId(professorId);
        professor.setSaldo(professor.getMAX_PONTOS());
        repository.save(professor);
    }

}
