package com.moeda.estudantil.util;

public class EmailUtils {

    public static String gerarEmailReconhecimento(String nomeAluno, String nomeProfessor, int pontos, String motivo) {
        return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body {
                            font-family: 'Segoe UI', Arial, sans-serif;
                            background-color: #f4f6f8;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            background-color: #ffffff;
                            max-width: 600px;
                            margin: 40px auto;
                            border-radius: 12px;
                            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                            overflow: hidden;
                        }
                        .header {
                            background-color: #4CAF50;
                            color: white;
                            text-align: center;
                            padding: 20px;
                        }
                        .header h1 {
                            margin: 0;
                            font-size: 24px;
                        }
                        .content {
                            padding: 30px;
                            color: #333;
                            line-height: 1.6;
                        }
                        .content h2 {
                            color: #4CAF50;
                            margin-top: 0;
                        }
                        .points {
                            background-color: #e8f5e9;
                            border-left: 5px solid #4CAF50;
                            padding: 15px;
                            margin: 20px 0;
                            font-size: 18px;
                            font-weight: bold;
                            color: #2e7d32;
                        }
                        .motivo {
                            background-color: #f1f8e9;
                            border-left: 4px solid #aed581;
                            padding: 15px;
                            font-style: italic;
                            color: #33691e;
                        }
                        .footer {
                            text-align: center;
                            font-size: 14px;
                            color: #888;
                            padding: 20px;
                            background-color: #fafafa;
                        }
                        .footer a {
                            color: #4CAF50;
                            text-decoration: none;
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🎉 Parabéns, %s!</h1>
                        </div>
                        <div class="content">
                            <p>Você acaba de ser <strong>reconhecido pelo(a) professor(a) %s</strong> pelo seu desempenho e atitude! 👏</p>
                            <div class="points">
                                Você ganhou <span style="font-size:22px;">%d pontos</span> que podem ser trocados por prêmios!
                            </div>
                            <p>💬 <strong>Motivo do reconhecimento:</strong></p>
                            <div class="motivo">%s</div>
                            <p>Continue se dedicando — cada ação positiva te aproxima de novas conquistas!</p>
                            <p>Com carinho,<br><strong>Equipe Educa Coins 💚</strong></p>
                        </div>
                        <div class="footer">
                            <p>Este é um e-mail automático. Para saber mais sobre seus pontos e prêmios, acesse o <a href="#">portal do aluno</a>.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(nomeAluno, nomeProfessor, pontos, motivo);
    }

    public static String gerarEmailConfirmacaoProfessor(String nomeProfessor, String nomeAluno, int pontos, String motivo) {
        return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body {
                            font-family: 'Segoe UI', Arial, sans-serif;
                            background-color: #f4f6f8;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            background-color: #ffffff;
                            max-width: 600px;
                            margin: 40px auto;
                            border-radius: 12px;
                            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                            overflow: hidden;
                        }
                        .header {
                            background-color: #4CAF50;
                            color: white;
                            text-align: center;
                            padding: 20px;
                        }
                        .header h1 {
                            margin: 0;
                            font-size: 24px;
                        }
                        .content {
                            padding: 30px;
                            color: #333;
                            line-height: 1.6;
                        }
                        .content h2 {
                            color: #4CAF50;
                            margin-top: 0;
                        }
                        .info {
                            background-color: #e8f5e9;
                            border-left: 5px solid #4CAF50;
                            padding: 15px;
                            margin: 20px 0;
                            font-size: 16px;
                            color: #2e7d32;
                        }
                        .motivo {
                            background-color: #f1f8e9;
                            border-left: 4px solid #aed581;
                            padding: 15px;
                            font-style: italic;
                            color: #33691e;
                        }
                        .footer {
                            text-align: center;
                            font-size: 14px;
                            color: #888;
                            padding: 20px;
                            background-color: #fafafa;
                        }
                        .footer a {
                            color: #4CAF50;
                            text-decoration: none;
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>✅ Reconhecimento enviado com sucesso!</h1>
                        </div>
                        <div class="content">
                            <p>Olá, <strong>%s</strong>!</p>
                            <p>Seu reconhecimento foi registrado com sucesso no sistema <strong>Moeda Estudantil</strong>.</p>
                            <div class="info">
                                <p><strong>Aluno reconhecido:</strong> %s</p>
                                <p><strong>Pontos concedidos:</strong> %d</p>
                            </div>
                            <p>💬 <strong>Motivo informado:</strong></p>
                            <div class="motivo">%s</div>
                            <p>Obrigado por incentivar o esforço e o bom desempenho dos alunos! Sua contribuição faz toda a diferença. 👏</p>
                            <p>Com apreço,<br><strong>Equipe Educa Coins 💚</strong></p>
                        </div>
                        <div class="footer">
                            <p>Este é um e-mail automático de confirmação. Acompanhe suas ações pelo <a href="#">painel do professor</a>.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(nomeProfessor, nomeAluno, pontos, motivo);
    }

    public static String gerarEmailResgateVantagem(String nomeAluno, String nomeVantagem, int pontosUsados, String urlImagemVantagem, String urlQrCode) {
        return """
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body {
                        font-family: 'Segoe UI', Arial, sans-serif;
                        background-color: #f4f6f8;
                        margin: 0;
                        padding: 0;
                    }
                    .container {
                        background-color: #ffffff;
                        max-width: 600px;
                        margin: 40px auto;
                        border-radius: 12px;
                        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                        overflow: hidden;
                    }
                    .header {
                        background-color: #4CAF50;
                        color: white;
                        text-align: center;
                        padding: 20px;
                    }
                    .header h1 {
                        margin: 0;
                        font-size: 24px;
                    }
                    .content {
                        padding: 30px;
                        color: #333;
                        line-height: 1.6;
                    }
                    .content h2 {
                        color: #4CAF50;
                        margin-top: 0;
                    }
                    .points {
                        background-color: #e8f5e9;
                        border-left: 5px solid #4CAF50;
                        padding: 15px;
                        margin: 20px 0;
                        font-size: 18px;
                        font-weight: bold;
                        color: #2e7d32;
                    }
                    .vantagem-img {
                        width: 100%%;
                        border-radius: 10px;
                        margin: 20px 0;
                    }
                    .qrcode-container {
                        text-align: center;
                        margin: 30px 0;
                        padding: 20px;
                        background-color: #f1f8e9;
                        border-radius: 12px;
                        border-left: 4px solid #aed581;
                    }
                    .qrcode-container img {
                        width: 180px;
                        height: auto;
                    }
                    .footer {
                        text-align: center;
                        font-size: 14px;
                        color: #888;
                        padding: 20px;
                        background-color: #fafafa;
                    }
                    .footer a {
                        color: #4CAF50;
                        text-decoration: none;
                        font-weight: bold;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>🎁 Resgate realizado com sucesso!</h1>
                    </div>
                    <div class="content">
                        <p>Olá, <strong>%s</strong>! Seu resgate foi concluído! 🎉</p>

                        <p>Você trocou seus pontos pela seguinte vantagem:</p>

                        <h2>%s</h2>

                        <img src="%s" alt="Vantagem" class="vantagem-img"/>

                        <div class="points">
                            Você utilizou <span style="font-size:22px;">%d pontos</span> para fazer este resgate.
                        </div>

                        <p>Para utilizar sua vantagem, apresente o QRCode abaixo:</p>

                        <div class="qrcode-container">
                            <img src="%s" alt="QR Code para resgate"/>
                            <p style="margin-top:10px; color:#33691e; font-weight:bold;">Mostre este código no local indicado para validar sua vantagem.</p>
                        </div>

                        <p>Aproveite! 😄</p>
                        <p>Com carinho,<br><strong>Equipe Educa Coins 💚</strong></p>
                    </div>

                    <div class="footer">
                        <p>Este é um e-mail automático. Para acompanhar seus pontos e vantagens, acesse o <a href="#">portal do aluno</a>.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(nomeAluno, nomeVantagem, urlImagemVantagem, pontosUsados, urlQrCode);
    }

    public static String gerarEmailAvisoParceiro(String nomeEmpresa, String nomeAluno, String nomeVantagem, int custoPontos) {
        return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body {
                            font-family: 'Segoe UI', Arial, sans-serif;
                            background-color: #f4f6f8;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            background-color: #ffffff;
                            max-width: 600px;
                            margin: 40px auto;
                            border-radius: 12px;
                            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                            overflow: hidden;
                        }
                        .header {
                            background-color: #4CAF50;
                            color: white;
                            text-align: center;
                            padding: 20px;
                        }
                        .header h1 {
                            margin: 0;
                            font-size: 24px;
                        }
                        .content {
                            padding: 30px;
                            color: #333;
                            line-height: 1.6;
                        }
                        .content h2 {
                            color: #4CAF50;
                            margin-top: 0;
                        }
                        .resgate-info {
                            background-color: #e8f5e9;
                            border-left: 5px solid #4CAF50;
                            padding: 15px;
                            margin: 20px 0;
                            font-size: 16px;
                            color: #2e7d32;
                        }
                        .footer {
                            text-align: center;
                            font-size: 14px;
                            color: #888;
                            padding: 20px;
                            background-color: #fafafa;
                        }
                        .footer a {
                            color: #4CAF50;
                            text-decoration: none;
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🎁 Nova vantagem resgatada!</h1>
                        </div>

                        <div class="content">
                            <p>Olá, <strong>%s</strong>! 👋</p>

                            <p>A equipe do <strong>Educa Coins</strong> informa que uma de suas vantagens foi resgatada por um aluno.</p>

                            <div class="resgate-info">
                                <p><strong>Vantagem:</strong> %s</p>
                                <p><strong>Aluno:</strong> %s</p>
                                <p><strong>Pontos utilizados:</strong> %d</p>
                            </div>

                            <p>Isso significa que o aluno já recebeu o comprovante de resgate com QRCode e poderá apresentar para realizar o uso da vantagem.</p>

                            <p>Agradecemos por apoiar o programa e contribuir com benefícios reais que motivam os estudantes! 💚</p>

                            <p>Com parceria,<br><strong>Equipe Educa Coins</strong></p>
                        </div>

                        <div class="footer">
                            <p>Este é um e-mail automático. Para suporte, acesse o <a href="#">painel do parceiro</a>.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(nomeEmpresa, nomeVantagem, nomeAluno, custoPontos);
    }
}
