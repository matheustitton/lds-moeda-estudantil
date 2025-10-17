package com.moeda.estudantil.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraCreateRequestDTO;
import com.moeda.estudantil.dto.empresa_parceira.EmpresaParceiraResponseDTO;
import com.moeda.estudantil.model.EmpresaParceira;
import com.moeda.estudantil.repository.EmpresaParceiraRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaParceiraService {

    private final EmpresaParceiraRepository empresaFiliadaRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public EmpresaParceiraService(EmpresaParceiraRepository empresaFiliadaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.empresaFiliadaRepository = empresaFiliadaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EmpresaParceiraResponseDTO findById(Long id) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        return empresaFiliadaRepository.findById(id)
                .map(empresa -> new EmpresaParceiraResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial()))
                .orElse(null);
    }

    @Transactional
    public void create(EmpresaParceiraCreateRequestDTO dto) {
        try {
            String senhaCriptografada = passwordEncoder.encode(dto.senha());
            EmpresaParceira empresa = new EmpresaParceira(dto.cnpj(), dto.razaoSocial(), dto.email(), senhaCriptografada);
            empresaFiliadaRepository.save(empresa);
        } catch (ConstraintViolationException cve) {
            throw new RuntimeException("CNPJ ou E-mail já cadastrado.");
        }
    }

    public EmpresaParceiraResponseDTO update(Long id, EmpresaParceiraCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        return empresaFiliadaRepository.findById(id)
                .map(empresa -> {
                    empresa = new EmpresaParceira(empresaFiliadaCreateRequestDTO.cnpj(), empresaFiliadaCreateRequestDTO.razaoSocial(), empresaFiliadaCreateRequestDTO.email(), empresaFiliadaCreateRequestDTO.senha());
                    empresa = empresaFiliadaRepository.save(empresa);
                    return new EmpresaParceiraResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial());
                })
                .orElse(null);
    }

    public EmpresaParceiraResponseDTO delete(Long id) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        empresaFiliadaRepository.deleteById(id);
        return new EmpresaParceiraResponseDTO(id, null, null);
    }

    public EmpresaParceiraResponseDTO[] findAll() {
        return empresaFiliadaRepository.findAll().stream()
                .map(empresa -> new EmpresaParceiraResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial()))
                .toArray(EmpresaParceiraResponseDTO[]::new);
    }
}
