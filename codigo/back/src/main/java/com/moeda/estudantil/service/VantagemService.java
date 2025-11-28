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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
        return empresaParceiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa parceira não encontrada."));
    }

   private String salvarImagem(MultipartFile imagem) {
        try {
            // Define a pasta de uploads
            String pasta = "/tmp/uploads/vantagens";
            File dir = new File(pasta);
            if (!dir.exists()) {
                dir.mkdirs(); // cria diretórios se não existirem
            }

            // Gera nome único para o arquivo
            String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
            File destino = new File(dir, nomeArquivo);

            // Salva o arquivo
            imagem.transferTo(destino);

            // Retorna caminho relativo para usar na URL
            return "/uploads/vantagens/" + nomeArquivo;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar imagem", e);
        }
    }

    @Transactional
    public void criar(VantagemCreateRequestDTO dto, MultipartFile imagem) {
        EmpresaParceira empresaParceira = buscarEmpresaParceira(dto.idEmpresaParceira());
        Vantagem vantagem = new Vantagem(dto.descricao(), dto.custo(), dto.tipo(), empresaParceira, imagem != null ? "" : null);

        // Salva imagem se fornecida
        if (imagem != null && !imagem.isEmpty()) {
            String urlImagem = salvarImagem(imagem);
            vantagem.setImagemUrl(urlImagem);
        }

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

        if (dto.imagem() != null && !dto.imagem().isEmpty()) {
            String imagemUrl = salvarImagem(dto.imagem());
            vantagem.setImagemUrl(imagemUrl);
        }

        repository.save(vantagem);
    }

    @Transactional
    public void excluir(Long id) {
        Vantagem vantagem = buscar(id);
        repository.delete(vantagem);
    }
}
