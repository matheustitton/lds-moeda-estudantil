package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.merito.MeritoCreateRequestDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.Merito;
import com.moeda.estudantil.model.Professor;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.MeritoRepository;
import com.moeda.estudantil.repository.ProfessorRepository;
import com.moeda.estudantil.util.EmailUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MeritoService {

    private MeritoRepository meritoRepository;
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;

    private EmailService emailService;

    public MeritoService(MeritoRepository meritoRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository, EmailService emailService) {
        this.meritoRepository = meritoRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void criar(MeritoCreateRequestDTO dto) {
        Professor professor = professorRepository.findById(dto.idProfessor()).orElseThrow(() -> new RuntimeException("Professor não encontrado."));
        Aluno aluno = alunoRepository.findById(dto.idAluno()).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));

        Merito merito = professor.novoMerito(aluno, dto.valor(), dto.motivo());
        meritoRepository.save(merito);
        professorRepository.save(professor);
        alunoRepository.save(aluno);

        String corpoEmailAluno = EmailUtils.gerarEmailReconhecimento(aluno.getNome(), professor.getNome(), dto.valor(), dto.motivo());
        emailService.enviarEmail(aluno.getEmail(), "Parabéns! Você ganhou novos pontos no Educa Coins",  corpoEmailAluno);

        String corpoEmailProfessor = EmailUtils.gerarEmailConfirmacaoProfessor(professor.getNome(), aluno.getNome(), dto.valor(), dto.motivo());
        emailService.enviarEmail(professor.getEmail(), "Reconhecimento enviado com sucesso!",  corpoEmailProfessor);
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
