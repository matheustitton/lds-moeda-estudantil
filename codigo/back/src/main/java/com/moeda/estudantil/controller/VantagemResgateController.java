package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.aluno.AlunoDTO;
import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.enums.EStatusTroca;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.Troca;
import com.moeda.estudantil.model.VantagemResgate;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.TrocaRepository;
import com.moeda.estudantil.repository.VantagemRepository;
import com.moeda.estudantil.repository.VantagemResgateRepository;
import com.moeda.estudantil.util.QRCodeUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/resgate")
@RequiredArgsConstructor
public class VantagemResgateController {

    private final VantagemResgateRepository repository;
    private final VantagemRepository vantagemRepository;
    private final AlunoRepository alunoRepository;
    private final TrocaRepository trocaRepository;

    /**
     * ---------------------------------------------------------
     * 1) ENDPOINT DE RESGATE - ACESSADO PELO QR CODE
     * ---------------------------------------------------------
     */
    @GetMapping("/{token}")
    public ResponseEntity<String> resgatar(@PathVariable String token) {

        return repository.findByToken(token)
                .map(resgate -> {

                    if (resgate.isUtilizado()) {
                        return ResponseEntity.badRequest().body("Código já utilizado!");
                    }

                    resgate.setUtilizado(true);
                    resgate.setDataUtilizacao(LocalDateTime.now());
                    repository.save(resgate);

                    Troca troca = trocaRepository.findByAluno_IdAndVantagem_Id(resgate.getAluno().getId(), resgate.getVantagem().getId());

                    if (troca != null) {
                        troca.setStatus(EStatusTroca.CONCLUIDA);
                        trocaRepository.save(troca);
                    }

                    return ResponseEntity.ok("Vantagem resgatada com sucesso!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
