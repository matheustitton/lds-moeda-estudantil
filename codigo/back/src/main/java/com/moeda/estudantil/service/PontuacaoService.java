package com.moeda.estudantil.service;

import org.springframework.stereotype.Service;

import com.moeda.estudantil.dto.pontuacao.PontuacaoCreateRequestDTO;
import com.moeda.estudantil.dto.pontuacao.PontuacaoDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.Pontuacao;
import com.moeda.estudantil.model.Professor;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.PontuacaoRepository;
import com.moeda.estudantil.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class PontuacaoService {
    private PontuacaoRepository pontuacaoRepository;
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;

    public PontuacaoService(PontuacaoRepository pontuacaoRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.pontuacaoRepository = pontuacaoRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void criar(PontuacaoCreateRequestDTO dto){
        Professor professor = professorRepository.findById(dto.doadorId()).orElseThrow(() -> new RuntimeException("Professor não encontrado."));
        Aluno aluno = alunoRepository.findById(dto.recebedorId()).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        Pontuacao pontuacao = new Pontuacao(dto.valor(), professor, aluno, dto.motivo());
        if (dto.valor() < 0)
            throw new RuntimeException("Valor da pontuação deve ser positivo.");
        aluno.atualizarSaldo(dto.valor());
        aluno.adicionarTransacao(pontuacao);
        alunoRepository.save(aluno);
        if(professor.getSaldo() < dto.valor())
            throw new RuntimeException("Saldo insuficiente para realizar a doação.");
        professor.adicionarTransacao(pontuacao);
        professorRepository.save(professor);
        pontuacaoRepository.save(pontuacao);
    }

    @Transactional
    public PontuacaoDTO buscar (Long id) {
        Pontuacao pontuacao = pontuacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pontuação não encontrada."));
        return new PontuacaoDTO(pontuacao.getId().intValue(), pontuacao.getValor(), pontuacao.getMotivo(), pontuacao.getDoador().getId(), pontuacao.getRecebedor().getId());
    }

    @Transactional
    public PontuacaoDTO[] listarPorAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        return aluno.getTransacoes().stream()
                .map(pontuacao -> new PontuacaoDTO(pontuacao.getId().intValue(), pontuacao.getValor(), pontuacao.getMotivo(), pontuacao.getDoador().getId(), pontuacao.getRecebedor().getId()))
                .toArray(PontuacaoDTO[]::new);
    }

    @Transactional
    public PontuacaoDTO[] listarPorProfessor(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado."));
        return professor.getTransacoes().stream()
                .map(pontuacao -> new PontuacaoDTO(pontuacao.getId().intValue(), pontuacao.getValor(), pontuacao.getMotivo(), pontuacao.getDoador().getId(), pontuacao.getRecebedor().getId()))
                .toArray(PontuacaoDTO[]::new);
    }

}
