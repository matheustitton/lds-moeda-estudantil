package com.moeda.estudantil.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.moeda.estudantil.model.Usuario;
import com.moeda.estudantil.model.Vantagem;
import com.moeda.estudantil.model.VantagemResgate;
import com.moeda.estudantil.repository.VantagemResgateRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VantagemResgateService {

    private final VantagemResgateRepository repository;
    private final JavaMailSender mailSender;

    public void gerarEEnviarCodigo(Vantagem vantagem, Usuario aluno) throws Exception {

        // 1 — Cria token único
        String token = UUID.randomUUID().toString();

        // 2 — Salva no banco
        VantagemResgate resgate = new VantagemResgate();
        resgate.setToken(token);
        resgate.setVantagem(vantagem);
        resgate.setAluno(aluno);
        repository.save(resgate);

        // 3 — Cria QRCode apontando para o token
        byte[] qrCode = gerarQRCode("http://localhost:8080/api/resgate/" + token);

        // 4 — Envia e-mail
        enviarEmailComQRCode(aluno.getEmail(), vantagem.getDescricao(), qrCode);
    }

    private byte[] gerarQRCode(String text) throws Exception {
        try {
            QRCodeWriter qr = new QRCodeWriter();
            var matrix = qr.encode(text, BarcodeFormat.QR_CODE, 300, 300);

            try (ByteArrayOutputStream png = new ByteArrayOutputStream()) {
                MatrixToImageWriter.writeToStream(matrix, "PNG", png);
                return png.toByteArray();
            }
        } catch (WriterException e) {
            throw new RuntimeException("Erro gerando QRCode", e);
        }
    }

    private void enviarEmailComQRCode(String email, String tituloVantagem, byte[] imagem) throws Exception {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(email);
        helper.setSubject("Seu QRCode de Resgate: " + tituloVantagem);
        helper.setText(
                """
                Olá!
                
                Aqui está o QRCode para resgatar sua vantagem.
                Basta apresentar este código no local indicado.
                """,
                true
        );

        helper.addAttachment("qrcode.png", () -> new java.io.ByteArrayInputStream(imagem));

        mailSender.send(msg);
    }
}
