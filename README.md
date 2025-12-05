<!-- Este template foi criado para servir como referência e pode ser facilmente adaptado para diferentes projetos de desenvolvimento -->

<!-- [![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=99999999&assignment_repo_type=AssignmentRepo) [![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=99999999)
-->

<a href="https://classroom.github.com/online_ide?assignment_repo_id=99999999&assignment_repo_type=AssignmentRepo"><img src="https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg" width="200"/></a> <a href="https://classroom.github.com/open-in-codespaces?assignment_repo_id=99999999"><img src="https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg" width="250"/></a>

---

# 🏷️ EducaCoins 👨‍💻

<table>
  <tr>
    <td width="800px">
      <div align="justify">
        Este **README.md** apresenta a documentação oficial do projeto **Moeda Estudantil**, uma aplicação desenvolvida para otimizar e modernizar a distribuição, controle e utilização de créditos estudantis dentro de instituições de ensino. O sistema oferece uma plataforma integrada onde alunos podem utilizar “moedas virtuais” para realizar compras internas, enquanto a instituição mantém total gerenciamento sobre emissões, transações e regras de uso. O principal objetivo do projeto é **proporcionar mais transparência, eficiência e automação** no processo de concessão e acompanhamento desses benefícios, reduzindo falhas operacionais e melhorando a experiência dos usuários.
      </div>
    </td>
    <td>
      <div>
        <img src="https://github.com/matheustitton/lds-moeda-estudantil/blob/main/docs/logo-Kittl.svg" alt="Logo do Projeto" width="120px"/>
      </div>
    </td>
  </tr> 
</table>

---

## 📚 Índice
- [Links Úteis](#-links-úteis)
- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades Principais](#-funcionalidades-principais)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura](#-arquitetura)
- [Autores](#-autores)
- [Agradecimentos](#-agradecimentos)

---

## 🔗 Links Úteis
* 🌐 **Demo Online:** [Acesse a Aplicação Web](<link-da-demo-web>)
  > 💻 **Descrição:** Link para a aplicação em ambiente de produção (Ex: hospedado na Vercel, Netlify ou AWS S3).

---

## 📝 Sobre o Projeto
O sistema de mérito estudantil tem como objetivo promover o reconhecimento acadêmico por meio de uma moeda virtual distribuída por professores a seus alunos. Ele foi idealizado para facilitar e organizar um processo de valorização do desempenho estudantil, oferecendo uma plataforma completa para cadastro, distribuição, consulta e resgate dessas moedas.

O projeto surge da necessidade de formalizar e automatizar práticas de incentivo já adotadas por algumas instituições, permitindo um controle mais transparente, eficiente e seguro das transações entre professores, alunos e empresas parceiras. Dessa forma, ele resolve problemas como falta de rastreabilidade, dificuldade de gestão de recompensas e ausência de integração entre os envolvidos.

Desenvolvido em um contexto acadêmico, o sistema pode ser usado em universidades, escolas ou qualquer instituição de ensino que deseja implementar um programa de mérito baseado em recompensas não financeiras. Além disso, empresas parceiras podem integrar-se ao sistema oferecendo vantagens que os alunos podem resgatar utilizando as moedas recebidas.

**A plataforma abrange todo o ciclo**:
- cadastro de alunos, professores e parceiros;
- distribuição de moedas e notificação automática;
- controle de saldo e extratos;
- gerenciamento de vantagens;
- resgate de recompensas com geração de cupom e autenticação obrigatória para todos os usuários.

Com isso, o projeto oferece uma solução prática e integrada para incentivar a participação acadêmica e fortalecer o relacionamento entre instituições, alunos e empresas parceiras.

## ✨ Funcionalidades Principais
Liste as funcionalidades de forma clara e objetiva.

- 🔐 Autenticação de Usuários: Login e acesso seguro para alunos, professores e empresas parceiras.
- 🧑‍🎓 Cadastro de Alunos: Registro de dados pessoais, acadêmicos e associação a uma instituição.
- 💰 Saldo de Moedas para Professores: Recebimento automático de 1.000 moedas por semestre, com saldo acumulativo.
- 🎁 Envio de Moedas aos Alunos: Professores podem transferir moedas informando valor e justificativa obrigatória.
- 📩 Notificação por E-mail: Alunos recebem e-mail ao ganhar moedas; empresas recebem e-mail quando vantagens são resgatadas.
- 📜 Extrato de Transações: Consulta completa de envios, recebimentos e trocas de moedas.
- 🛍️ Catálogo de Vantagens: Listagem de benefícios cadastrados pelas empresas parceiras (desconto, produtos, serviços, etc.).
- 🏢 Cadastro de Vantagens por Empresas Parceiras: Empresas podem cadastrar vantagens informando descrição, foto e custo em moedas.
- 🔄 Resgate de Vantagens: Alunos podem trocar moedas por benefícios, com geração de cupom e código único de verificação.
- 🧾 Controle de Saldos: Atualização automática do saldo ao enviar ou resgatar moedas.
- 🛡️ Validação de Transações.

---
## 🛠 Tecnologias Utilizadas

As seguintes ferramentas, frameworks e bibliotecas foram utilizados na construção deste projeto. Recomenda-se o uso das versões listadas (ou superiores) para garantir a compatibilidade.

#### 💻 Front-end
![Next.js](https://img.shields.io/badge/Next.js-15-000000?style=for-the-badge&logo=nextdotjs&logoColor=white)
![React](https://img.shields.io/badge/React-19-007ec6?style=for-the-badge&logo=react&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-5-3178c6?style=for-the-badge&logo=typescript&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/TailwindCSS-latest-0ea5e9?style=for-the-badge&logo=tailwindcss&logoColor=white)
![Zustand](https://img.shields.io/badge/Zustand-latest-4433ff?style=for-the-badge&logo=react&logoColor=white)
![Turbopack](https://img.shields.io/badge/Build-Turbopack-orange?style=for-the-badge&logo=vercel&logoColor=white)

#### 🖥️ Back-end
![Java](https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6db33f?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169e1?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666c?style=for-the-badge&logo=hibernate&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-yellow?style=for-the-badge&logo=jsonwebtokens&logoColor=black)
![OAuth2](https://img.shields.io/badge/Auth-OAuth2-blue?style=for-the-badge&logo=auth0&logoColor=white)
![Spring Security](https://img.shields.io/badge/Security-Spring_Security-6db33f?style=for-the-badge&logo=springsecurity&logoColor=white)



### ⚙️ Infraestrutura & DevOps

* **Containerização:** Docker, Docker Compose

---

🏗 Arquitetura

O sistema foi desenvolvido utilizando a arquitetura MVC em camadas, estruturada em quatro componentes principais: Controller, Service, Model e Repository.
Essa abordagem foi escolhida por oferecer clareza na separação de responsabilidades, facilidade de manutenção e um fluxo de dados simples e intuitivo.

🔍 Visão Geral

A arquitetura é dividida em quatro camadas principais:

1. Controller — responsável por receber e responder às requisições da API.
2. Service — concentra as regras de negócio e validações.
3. Model — representa as entidades e o domínio da aplicação.
4. pository — camada de acesso a dados.

Essa abordagem segue o padrão MVC, porém com a adição de uma camada Service para isolar a lógica de negócios da lógica de controle, garantindo maior testabilidade e modularidade.

### 🧩 Componentes da Arquitetura

1. Controller (Interface / Entrada da Aplicação)

- Recebe requisições HTTP.
- Valida dados de entrada.
- Converte a requisição no formato esperado pelo Service.
- Retorna respostas HTTP adequadas.

2. Service (Regra de Negócio da Aplicação)

- Implementa a lógica da aplicação.
- Orquestra o fluxo entre Controller e Repository.
- Aplica regras de negócio de nível de aplicação.
- Garante consistência e validações.

3. Model (Representação dos Dados)

- Define a estrutura das entidades que trafegam no sistema.
- Representa tabelas, objetos de domínio simplificados ou DTOs internos.
- Pode conter validações simples ou mapeamentos.

4. Repository (Acesso a Dados)

- É responsável por comunicação com o banco de dados.
- Implementa consultas, inserções e atualizações.
- Encapsula detalhes da persistência.
- Evita que a camada de serviço conheça detalhes do banco.

---


## 🚀 Deploy
Instruções para deploy em produção.

1.  **Build do Projeto:**
    Execute o build separadamente para os dois artefatos (Azure para o Back-end e Vercel para o Front-end).

```bash
# 1. Build do Front-end (Next.js) 
cd frontend
npm run build

# 2. Build do Back-end (Spring Boot/Maven) - Gera o arquivo .jar executável em /target
cd ../back
docker compose up -d --build
```

2.  **Configuração do Ambiente de Produção:** Defina as variáveis de ambiente no seu provedor (e.g., Vercel, Railway, Heroku, DigitalOcean).

> 🔑 **Variáveis Cruciais:** Para executar o projeto corretamente, é necessário configurar as seguintes variáveis de ambiente no seu sistema ou no arquivo .env. Elas garantem que o banco de dados PostgreSQL, o servidor Spring Boot e o serviço de envio de e-mails funcionem adequadamente. Abaixo estão as variáveis necessárias, juntamente com exemplos de valores que você pode ajustar conforme sua configuração:
>
> POSTGRES_USER=admin
> 
> POSTGRES_PASSWORD=senha123
> 
> POSTGRES_DATABASE=educa_db
> 
> POSTGRES_LOCAL_PORT=5432
> 
> POSTGRES_DOCKER_PORT=5432
> 
> SPRING_LOCAL_PORT=8080
>
> SPRING_DOCKER_PORT=8080
> 
> MAIL_USERNAME=seuemail@gmail.com]
> 
> MAIL_PASSWORD=suasenhadeaplicativo

3.  **Execução em Produção:**
    Instruções de Deploy (Azure + Vercel)

Este guia descreve como implantar o projeto em produção utilizando:

Azure → Back-end (Spring Boot) + Banco de Dados (PostgreSQL via Docker)

Vercel → Front-end (Next.js)

##🌐 Deploy do Back-end na Azure

O back-end foi implantado em uma Máquina Virtual (VM) na Microsoft Azure com:

* 1 GB de RAM

* 30 GB de SSD

* Ubuntu Server

* Docker + Docker Compose instalados

##1️⃣ Criar e Preparar a Máquina Virtual

Acesse o portal: https://portal.azure.com

Vá em Máquinas Virtuais → Criar

Configure:

* Imagem: Ubuntu Server LTS

* Tamanho: B1s (1 vCPU, 1GB RAM)

* Disco: 30 GB SSD

* Liberar portas na criação:

:22 (SSH)

:8080 (API Spring)

:5432 (Postgres, se necessário externo)

Criar.

##2️⃣ Acessar a VM via SSH]
```bash
ssh usuario@ip_da_vm
```

##3️⃣ Instalar Docker e Docker Compose
```bash
sudo apt update
sudo apt install docker.io -y
sudo systemctl enable docker
sudo systemctl start docker
```

Instalar Docker Compose:
```bash
sudo apt install docker-compose -y
```

##4️⃣ Enviar o Back-end para a VM

Na sua máquina local:
```bash
scp -r ./back usuario@ip_da_vm:/home/usuario/
```
##5️⃣ Configurar Variáveis de Ambiente

Na Azure (na própria VM), crie um arquivo .env dentro da pasta /back.nano .env

Cole:
``` bash
POSTGRES_USER=admin
POSTGRES_PASSWORD=senha123
POSTGRES_DATABASE=educa_db
POSTGRES_LOCAL_PORT=5432
POSTGRES_DOCKER_PORT=5432

SPRING_LOCAL_PORT=8080
SPRING_DOCKER_PORT=8080

MAIL_USERNAME=seuemail@gmail.com
MAIL_PASSWORD=suasenhadeaplicativo
```

##6️⃣ Executar o Back-end

Dentro da pasta /back:
```bash
docker compose up -d --build
```

Isso inicia:

PostgreSQL (via Docker)

API Spring Boot (via JAR em container)

Checar logs:

```bash
docker logs nome_do_container
```

##7️⃣ Verificar se está no ar

A API estará acessível pelo IP público da VM:

http://SEU_IP_PUBLICO:8080

**Video utilizado como suporte: [(Video)](https://www.youtube.com/watch?v=qKGSn2xdX1Y)**

---

## 👥 Autores

| 👤 Nome | 🖼️ Foto | :octocat: GitHub | 💼 LinkedIn |
|---------|----------|-----------------|-------------|
| Matheus Titton | <div align="center"><img src="https://joaopauloaramuni.github.io/image/aramunilogo.png" width="70px" height="70px"></div> | <div align="center"><a href="https://github.com/matheustitton"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/matheustitton/"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> |
| Ítalo Vitorino  | <div align="center"><img src="https://joaopauloaramuni.github.io/image/aramunilogo.png" width="70px" height="70px"></div> | <div align="center"><a href="https://github.com/italovitorino"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/italo-vitorinobs/"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> |
| Murilo Andrade  | <div align="center"><img src="https://joaopauloaramuni.github.io/image/aramunilogo.png" width="70px" height="70px"></div> | <div align="center"><a href="https://github.com/muriloAmachado"><img src="https://joaopauloaramuni.github.io/image/github6.png" width="50px" height="50px"></a></div> | <div align="center"><a href="https://www.linkedin.com/in/murilo-andrade-machado-a3b763293/"><img src="https://joaopauloaramuni.github.io/image/linkedin2.png" width="50px" height="50px"></a></div> |

---

## 🙏 Agradecimentos
Em ambiente acadêmico, citar fontes e inspirações é crucial (integridade acadêmica). Em ambiente profissional, mostra humildade e conexão com a comunidade.

Gostaria de agradecer aos seguintes canais e pessoas que foram fundamentais para o desenvolvimento deste projeto:

* [**Engenharia de Software PUC Minas**](https://www.instagram.com/engsoftwarepucminas/) - Pelo apoio institucional, estrutura acadêmica e fomento à inovação e boas práticas de engenharia.
* [**Prof. Dr. João Paulo Aramuni**](https://github.com/joaopauloaramuni) - Pelos valiosos ensinamentos sobre **Arquitetura de Software** e **Padrões de Projeto**.

---


