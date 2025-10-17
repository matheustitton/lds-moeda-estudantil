package com.moeda.estudantil.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.moeda.estudantil.dto.empresaFiliada.EmpresaFiliadaCreateRequestDTO;
import com.moeda.estudantil.dto.empresaFiliada.EmpresaFiliadaResponseDTO;
import com.moeda.estudantil.model.EmpresaFiliada;
import com.moeda.estudantil.repository.EmpresaFiliadaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaFiliadaService {

    private final EmpresaFiliadaRepository empresaFiliadaRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public EmpresaFiliadaService(EmpresaFiliadaRepository empresaFiliadaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.empresaFiliadaRepository = empresaFiliadaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EmpresaFiliadaResponseDTO findById(Long id) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        return empresaFiliadaRepository.findById(id)
                .map(empresa -> new EmpresaFiliadaResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial()))
                .orElse(null);
    }

    @Transactional
    public void create(EmpresaFiliadaCreateRequestDTO dto) {
        try {
            String senhaCriptografada = passwordEncoder.encode(dto.senha());
            EmpresaFiliada empresa = new EmpresaFiliada(dto.cnpj(), dto.razaoSocial(), dto.email(), senhaCriptografada);
            empresaFiliadaRepository.save(empresa);
        } catch (ConstraintViolationException cve) {
            throw new RuntimeException("CNPJ ou E-mail já cadastrado.");
        }
    }

    public EmpresaFiliadaResponseDTO update(Long id, EmpresaFiliadaCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        return empresaFiliadaRepository.findById(id)
                .map(empresa -> {
                    empresa = new EmpresaFiliada(empresaFiliadaCreateRequestDTO.cnpj(), empresaFiliadaCreateRequestDTO.razaoSocial(), empresaFiliadaCreateRequestDTO.email(), empresaFiliadaCreateRequestDTO.senha());
                    empresa = empresaFiliadaRepository.save(empresa);
                    return new EmpresaFiliadaResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial());
                })
                .orElse(null);
    }

    public EmpresaFiliadaResponseDTO delete(Long id) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        empresaFiliadaRepository.deleteById(id);
        return new EmpresaFiliadaResponseDTO(id, null, null);
    }

    public EmpresaFiliadaResponseDTO[] findAll() {
        return empresaFiliadaRepository.findAll().stream()
                .map(empresa -> new EmpresaFiliadaResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial()))
                .toArray(EmpresaFiliadaResponseDTO[]::new);
    }
}
