package com.moeda.estudantil.service;

import org.springframework.stereotype.Service;

import com.moeda.estudantil.dto.empresaFiliada.EmpresaFiliadaCreateRequestDTO;
import com.moeda.estudantil.dto.empresaFiliada.EmpresaFiliadaResponseDTO;
import com.moeda.estudantil.model.EmpresaFiliada;
import com.moeda.estudantil.repository.EmpresaFiliadaRepository;

@Service
public class EmpresaFiliadaService {
    private final EmpresaFiliadaRepository empresaFiliadaRepository;

    public EmpresaFiliadaService(EmpresaFiliadaRepository empresaFiliadaRepository) {
        this.empresaFiliadaRepository = empresaFiliadaRepository;
    }

    public EmpresaFiliadaResponseDTO findById(Long id) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        return empresaFiliadaRepository.findById(id)
                .map(empresa -> new EmpresaFiliadaResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial()))
                .orElse(null);
    }

    public EmpresaFiliadaResponseDTO create(EmpresaFiliadaCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        if (empresaFiliadaRepository.findAll().stream().anyMatch(e -> e.getCnpj().equals(empresaFiliadaCreateRequestDTO.cnpj()))) {
            return null;
        }
        EmpresaFiliada empresa = new EmpresaFiliada(empresaFiliadaCreateRequestDTO.cnpj(), empresaFiliadaCreateRequestDTO.razaoSocial());
        empresa = empresaFiliadaRepository.save(empresa);
        return new EmpresaFiliadaResponseDTO(empresa.getId(), empresa.getCnpj(), empresa.getRazaoSocial());

    }

    public EmpresaFiliadaResponseDTO update(Long id, EmpresaFiliadaCreateRequestDTO empresaFiliadaCreateRequestDTO) {
        if (empresaFiliadaRepository.findAll().stream().noneMatch(e -> e.getId().equals(id))) {
            return null;
        }
        return empresaFiliadaRepository.findById(id)
                .map(empresa -> {
                    empresa = new EmpresaFiliada(empresaFiliadaCreateRequestDTO.cnpj(), empresaFiliadaCreateRequestDTO.razaoSocial());
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
