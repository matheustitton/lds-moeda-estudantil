package com.moeda.estudantil.service;

import com.moeda.estudantil.dto.troca.TrocaAlunoDTO;
import com.moeda.estudantil.dto.troca.TrocaCreateRequestDTO;
import com.moeda.estudantil.dto.troca.TrocaDTO;
import com.moeda.estudantil.model.Aluno;
import com.moeda.estudantil.model.Troca;
import com.moeda.estudantil.model.Vantagem;
import com.moeda.estudantil.repository.AlunoRepository;
import com.moeda.estudantil.repository.TrocaRepository;
import com.moeda.estudantil.repository.VantagemRepository;
import com.moeda.estudantil.util.EmailUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class TrocaService {

    private final String uploadRoot = "/tmp";
    private TrocaRepository repository;
    private AlunoRepository alunoRepository;
    private VantagemRepository vantagemRepository;
    private EmailService emailService;
    private QrCodeService qrCodeService;

    public TrocaService(TrocaRepository repository, AlunoRepository alunoRepository, VantagemRepository vantagemRepository, EmailService emailService, QrCodeService qrCodeService) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.vantagemRepository = vantagemRepository;
        this.emailService = emailService;
        this.qrCodeService = qrCodeService;
    }

    private Troca buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Troca não encontrado."));
    }

    @Transactional
    public void criar(TrocaCreateRequestDTO dto) throws Exception {
        Aluno aluno = alunoRepository.findById(dto.idAluno()).orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
        Vantagem vantagem = vantagemRepository.findById(dto.idVantagem()).orElseThrow(() -> new RuntimeException("Vantagem não encontrada."));

        Troca troca = new Troca(aluno, vantagem);
        repository.save(troca);

        byte[] qrcodeBytes = qrCodeService.gerar(vantagem, aluno);
        String base64QrCode = Base64.getEncoder().encodeToString(qrcodeBytes);
        String imgQrCodeHtml = "data:image/png;base64," + base64QrCode;

        String caminhoFisico = uploadRoot + vantagem.getImagemUrl();
        Path path = Paths.get(caminhoFisico);
        byte[] imagemBytes = Files.readAllBytes(path);
        String base64Imagem = Base64.getEncoder().encodeToString(imagemBytes);
        String imagemHtml = "data:image/jpeg;base64," + base64Imagem;

        String corpoEmailTroca = EmailUtils.gerarEmailResgateVantagem(aluno.getNome(), vantagem.getDescricao(), vantagem.getCusto(), imagemHtml, imgQrCodeHtml);
        emailService.enviarEmail(aluno.getEmail(), "\uD83C\uDF81 Sua vantagem foi resgatada com sucesso!",  corpoEmailTroca);

        String corpoEmailAvisoParceiro = EmailUtils.gerarEmailAvisoParceiro(vantagem.getEmpresaParceira().getRazaoSocial(), aluno.getNome(), vantagem.getDescricao(), vantagem.getCusto());
        emailService.enviarEmail(vantagem.getEmpresaParceira().getEmail(), "⚠️ Aviso de vantagem resgatada", corpoEmailAvisoParceiro);
    }

    @Transactional
    public TrocaDTO buscar(Long id) {
        return buscarPorId(id).toDto();
    }

    public List<TrocaAlunoDTO> buscarPorAluno(Long id) {
        List<Troca> trocas = repository.findByAluno_Id(id);
        return trocas.stream().map(Troca::toTrocaAlunoDto).toList();
    }

    @Transactional
    public void cancelar(Long id) {
        Troca troca = buscarPorId(id);

        if (troca.cancelar()) {
            repository.save(troca);
            return;
        }

        throw new RuntimeException("Não é possível cancelar uma troca já concluída.");
    }
}
