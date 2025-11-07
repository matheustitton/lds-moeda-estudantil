package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.merito.MeritoCreateRequestDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.Merito;
import com.moeda.estudantil.model.Professor;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.MeritoRepository;
import com.moeda.estudantil.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MeritoService {
    private MeritoRepository meritoRepository;
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;

    public MeritoService(MeritoRepository meritoRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.meritoRepository = meritoRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void criar(MeritoCreateRequestDTO dto) {
        Professor professor = professorRepository.findById(dto.doadorId()).orElseThrow(() -> new RuntimeException("Professor não encontrado."));
        Aluno aluno = alunoRepository.findById(dto.recebedorId()).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));

        Merito merito = professor.novoMerito(aluno, dto.valor(), dto.motivo());
        meritoRepository.save(merito);
        professorRepository.save(professor);
        alunoRepository.save(aluno);
    }

    /*@Transactional
    public MeritoAlunoDTO buscar (Long id) {
        Merito pontuacao = meritoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pontuação não encontrada."));
        return new MeritoAlunoDTO(pontuacao.getId().intValue(), pontuacao.getValor(), pontuacao.getMotivo(), pontuacao.getProfessor().getId(), pontuacao.getAluno().getId());
    }

    @Transactional
    public MeritoAlunoDTO[] listarPorAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        return aluno.getTransacoes().stream()
                .map(pontuacao -> new MeritoAlunoDTO(pontuacao.getId().intValue(), pontuacao.getValor(), pontuacao.getMotivo(), pontuacao.getProfessor().getId(), pontuacao.getAluno().getId()))
                .toArray(MeritoAlunoDTO[]::new);
    }

    @Transactional
    public MeritoAlunoDTO[] listarPorProfessor(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado."));
        return professor.getTransacoes().stream()
                .map(pontuacao -> new MeritoAlunoDTO(pontuacao.getId().intValue(), pontuacao.getValor(), pontuacao.getMotivo(), pontuacao.getProfessor().getId(), pontuacao.getAluno().getId()))
                .toArray(MeritoAlunoDTO[]::new);
    }*/

}
