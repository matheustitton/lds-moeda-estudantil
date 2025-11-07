package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.merito.MeritoProfessorDTO;
import com.moeda.estudantil.dto.professor.ProfessorDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.Merito;
import com.moeda.estudantil.model.Professor;
import com.moeda.estudantil.repository.MeritoRepository;
import com.moeda.estudantil.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private ProfessorRepository repository;
    private MeritoRepository meritoRepository;

    public ProfessorService(ProfessorRepository repository, MeritoRepository meritoRepository) {
        this.repository = repository;
        this.meritoRepository = meritoRepository;
    }

    private Professor buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado."));
    }

    public ProfessorDTO buscar(Long id) {
        return buscarPorId(id).toDto();
    }

    public List<MeritoProfessorDTO> buscarMeritos(Long id) {
        List<Merito> meritos = meritoRepository.findByProfessor_Id(id);
        return meritos.stream().map(Merito::toDtoProfessor).toList();
    }

    /*@Transactional
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
    }*/
}
