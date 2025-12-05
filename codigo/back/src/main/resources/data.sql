TRUNCATE TABLE instituicao_ensino RESTART IDENTITY CASCADE;

INSERT INTO instituicao_ensino (nome, cnpj)
VALUES ('Pontifícia Universidade Católica de Minas Gerais (PUC-MG)', '67890123000174'),
       ('Universidade Federal de Minas Gerais (UFMG)', '34567890000102'),
       ('Universidade de São Paulo (USP)', '12345678000195'),
       ('Universidade Estadual de Campinas (UNICAMP)', '23456789000109'),
       ('Universidade Federal do Rio de Janeiro (UFRJ)', '45678901000116');

TRUNCATE TABLE usuario RESTART IDENTITY CASCADE;

INSERT INTO usuario (email, senha, tipo_usuario)
VALUES ('italovbsilva03@gmail.com', 'senha123', 'PROFESSOR'),
       ('italo.vitorinobs@hotmail.com', 'senha123', 'ALUNO');

TRUNCATE TABLE professor RESTART IDENTITY CASCADE;

INSERT INTO professor (id, nome, departamento, cpf, instituicao_id, saldo)
VALUES (1, 'Professor 1', 'Departamento de Matemática', '12345678901', 1, 5000);

TRUNCATE TABLE aluno RESTART IDENTITY CASCADE;

INSERT INTO aluno (id, nome, rg, cpf, curso, instituicao_id, saldo)
VALUES (2, 'Aluno 1', '385598300', '16547087042', 'Engenharia de Software', 1, 0);
