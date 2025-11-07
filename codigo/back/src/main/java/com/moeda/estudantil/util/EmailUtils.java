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
}
