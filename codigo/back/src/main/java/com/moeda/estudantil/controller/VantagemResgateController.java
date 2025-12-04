package com.moeda.estudantil.controller;

import com.moeda.estudantil.dto.aluno.AlunoDTO;
import com.moeda.estudantil.dto.vantagem.VantagemDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.VantagemResgate;
import com.moeda.estudantil.repository.AlunoRepository;
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

                    return ResponseEntity.ok("Vantagem resgatada com sucesso!");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * ---------------------------------------------------------
     * 2) ENDPOINT POST - GERA TOKEN, SALVA RESGATE E GERA QRCODE
     * ---------------------------------------------------------
     */
    @PostMapping("/{idVantagem}/{idAluno}")
    public ResponseEntity<byte[]> gerarResgate(
            @RequestBody VantagemDTO vantagemDTO,
            @RequestBody AlunoDTO alunoDTO) throws Exception {

        String token = UUID.randomUUID().toString();

        VantagemResgate resgate = new VantagemResgate();
        resgate.setToken(token);
        resgate.setUtilizado(false);
        resgate.setDataGeracao(LocalDateTime.now());
        com.moeda.estudantil.model.Vantagem vantagem = vantagemRepository.findById(vantagemDTO.id()).orElseThrow();
        com.moeda.estudantil.model.Aluno aluno = alunoRepository.findById(alunoDTO.id()).orElseThrow();
        resgate.setVantagem(vantagem);
        resgate.setAluno(aluno);
        repository.save(resgate);

        String url = "http://localhost:8080/api/resgate/" + token;

        byte[] qrCode = QRCodeUtil.gerarQRCode(url);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);

        // emailService.enviarQRCode(idAluno, qrCode, vantagemImagem);
    }

    @GetMapping("/qrcode/teste/{idVantagem}")
    public ResponseEntity<byte[]> gerarQRCodeTeste(@PathVariable Long idVantagem) throws Exception {

        String token = UUID.randomUUID().toString();

        byte[] qrImage = QRCodeUtil.gerarQRCode(
                "http://localhost:8080/api/resgate/" + token
        );

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }

}
