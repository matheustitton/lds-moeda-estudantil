package com.moeda.estudantil.dto.vantagem;

import com.moeda.estudantil.enums.ETipoVantagem;
import org.springframework.web.multipart.MultipartFile;

public record VantagemUpdateRequestDTO(
        String descricao,
        int custo,
        ETipoVantagem tipo,
        MultipartFile imagem
) {}
