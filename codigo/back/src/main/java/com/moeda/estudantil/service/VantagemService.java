package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.vantagem.VantagemCreateRequestDTO;
import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.dto.vantagem.VantagemUpdateRequestDTO;
import com.moeda.estudantil.model.EmpresaParceira;
import com.moeda.estudantil.model.Vantagem;
import com.moeda.estudantil.repository.EmpresaParceiraRepository;
import com.moeda.estudantil.repository.VantagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VantagemService {

    private VantagemRepository repository;
    private EmpresaParceiraRepository empresaParceiraRepository;

    public VantagemService(VantagemRepository repository, EmpresaParceiraRepository empresaParceiraRepository) {
        this.repository = repository;
        this.empresaParceiraRepository = empresaParceiraRepository;
    }

    private Vantagem buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Vantagem não encontrada."));
    }

    private EmpresaParceira buscarEmpresaParceira(Long id) {
        return empresaParceiraRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa parceira não encontrada."));
    }

    @Transactional
    public void criar(VantagemCreateRequestDTO dto) {
        EmpresaParceira empresaParceira = buscarEmpresaParceira(dto.idEmpresaParceira());
        Vantagem vantagem = new Vantagem(dto.descricao(), dto.custo(), dto.tipo(), empresaParceira);
        repository.save(vantagem);
    }

    @Transactional
    public VantagemDTO buscarPorId(Long id) {
        return buscar(id).toDto();
    }

    @Transactional
    public List<VantagemDTO> buscarTodas() {
        List<Vantagem> vantagens = repository.findAll();
        return vantagens.stream().map(Vantagem::toDto).toList();
    }

    @Transactional
    public void atualizar(Long id, VantagemUpdateRequestDTO dto) {
        Vantagem vantagem = buscar(id);

        vantagem.setDescricao(dto.descricao());
        vantagem.setCusto(dto.custo());
        vantagem.setTipo(dto.tipo());

        repository.save(vantagem);
    }

    @Transactional
    public void excluir(Long id) {
        Vantagem vantagem = buscar(id);
        repository.delete(vantagem);
    }
}
