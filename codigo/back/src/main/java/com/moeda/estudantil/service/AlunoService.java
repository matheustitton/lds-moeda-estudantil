package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.aluno.AlunoCreateRequestDTO;
import com.moeda.estudantil.dto.aluno.AlunoDTO;
import com.moeda.estudantil.dto.aluno.AlunoUpdateRequestDTO;
import com.moeda.estudantil.dto.pontuacao.PontuacaoDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.InstituicaoEnsino;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.InstituicaoEnsinoRepository;
import com.moeda.estudantil.util.CriptografiaUtil;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private AlunoRepository repository;
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    public AlunoService(AlunoRepository repository, InstituicaoEnsinoRepository instituicaoEnsinoRepository) {
        this.repository = repository;
        this.instituicaoEnsinoRepository = instituicaoEnsinoRepository;
    }

    private Aluno buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }

    private InstituicaoEnsino buscarInstituicaoEnsino(Long id) {
        return instituicaoEnsinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Instituição de ensino não encontrada."));    
    }

    @Transactional
    public void criar(AlunoCreateRequestDTO dto) {
        InstituicaoEnsino instituicaoEnsino = buscarInstituicaoEnsino(dto.instituicaoEnsino());
        String senhaCriptografada = CriptografiaUtil.criptografar(dto.senha());

        Aluno aluno = new Aluno(dto.nome(), dto.rg(), dto.cpf(), dto.curso(), instituicaoEnsino, dto.email(), senhaCriptografada);
        repository.save(aluno);
    }

    @Transactional
    public AlunoDTO buscar(Long id) {
        return buscarPorId(id).toDto();
    }

    @Transactional
    public void atualizar(Long id, AlunoUpdateRequestDTO dto) {
        Aluno aluno = buscarPorId(id);
        InstituicaoEnsino instituicaoEnsino = buscarInstituicaoEnsino(dto.instituicaoEnsino());
        String senhaCriptografada = CriptografiaUtil.criptografar(dto.senha());

        aluno.setNome(dto.nome());
        aluno.setCurso(dto.curso());
        aluno.setInstituicao(instituicaoEnsino);
        aluno.setEmail(dto.email());
        aluno.setSenha(senhaCriptografada);

        repository.save(aluno);
    }

    @Transactional
    public void excluir(Long id) {
        Aluno aluno = buscarPorId(id);
        repository.delete(aluno);
    }

    @Transactional
    public int receberDoacao(Long recebedorId, PontuacaoDTO dto) {
        Aluno recebedor = buscarPorId(recebedorId);
        recebedor.atualizarSaldo(dto.valor());
        repository.save(recebedor);
        return recebedor.getSaldo();       
    }

}
