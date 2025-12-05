package com.moeda.estudantil.service;

import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.Vantagem;
import com.moeda.estudantil.model.VantagemResgate;
import com.moeda.estudantil.repository.VantagemResgateRepository;
import com.moeda.estudantil.util.QRCodeUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeService {

    private final String url = "http://48.217.82.183:8080/api/resgate/";
    private final VantagemResgateRepository repository;

    public QrCodeService(VantagemResgateRepository repository) {
        this.repository = repository;
    }

    public byte[] gerar(Vantagem vantagem, Aluno aluno) throws Exception {
        String token = UUID.randomUUID().toString();
        VantagemResgate resgate = new VantagemResgate(vantagem, aluno, token);
        repository.save(resgate);

        String url = this.url + token;
        return QRCodeUtil.gerarQRCode(url);
    }
}
