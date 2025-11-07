package com.moeda.estudantil.service;

import org.springframework.stereotype.Service;

import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraCreateRequestDTO;
import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraDTO;
import com.moeda.estudantil.model.EmpresaParceira;
import com.moeda.estudantil.repository.EmpresaParceiraRepository;
import com.moeda.estudantil.util.CriptografiaUtil;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaParceiraService {

    private final EmpresaParceiraRepository empresaFiliadaRepository;

    public EmpresaParceiraService(EmpresaParceiraRepository empresaFiliadaRepository) {
        this.empresaFiliadaRepository = empresaFiliadaRepository;
    }

    public EmpresaParceiraDTO findById(Long id) {
        EmpresaParceira empresa = empresaFiliadaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa parceira não encontrada"));
        return empresa.toDto();
    }

    @Transactional
    public void create(EmpresaParceiraCreateRequestDTO dto) {
        String senhaCriptografada = CriptografiaUtil.criptografar(dto.senha());
        EmpresaParceira empresa = new EmpresaParceira(dto.cnpj(), dto.razaoSocial(), dto.email(), senhaCriptografada);

        empresaFiliadaRepository.save(empresa);
    }

    public EmpresaParceiraDTO update(Long id, EmpresaParceiraCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        EmpresaParceira empresa = empresaFiliadaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa parceira não encontrada"));

        empresa.setCnpj(empresaFiliadaCreateRequestDTO.cnpj());
        empresa.setRazaoSocial(empresaFiliadaCreateRequestDTO.razaoSocial());
        empresa.setEmail(empresaFiliadaCreateRequestDTO.email());
        empresa.setSenha(CriptografiaUtil.criptografar(empresaFiliadaCreateRequestDTO.senha()));

        empresaFiliadaRepository.save(empresa);
        return empresa.toDto();
    }

    public void delete(Long id) {
        EmpresaParceira empresa = empresaFiliadaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa parceira não encontrada"));
        empresaFiliadaRepository.delete(empresa);
    }

    public List<EmpresaParceiraDTO> findAll() {
        return empresaFiliadaRepository.findAll().stream()
                .map(EmpresaParceira::toDto)
                .toList();
    }
}
