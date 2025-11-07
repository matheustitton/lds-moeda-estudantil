TRUNCATE TABLE instituicao_ensino RESTART IDENTITY CASCADE;

INSERT INTO instituicao_ensino (nome, cnpj) VALUES
('Pontifícia Universidade Católica de Minas Gerais (PUC-MG)', '67890123000174'),
('Universidade Federal de Minas Gerais (UFMG)', '34567890000102'),
('Universidade de São Paulo (USP)', '12345678000195'),
('Universidade Estadual de Campinas (UNICAMP)', '23456789000109'),
('Universidade Federal do Rio de Janeiro (UFRJ)', '45678901000116');

TRUNCATE TABLE usuario RESTART IDENTITY CASCADE;
INSERT INTO usuario (email, senha, tipo_usuario) VALUES
('professor.1@gmail.com', 'senha123', 'PROFESSOR'),
('professor.2@gmail.com', 'senha123', 'PROFESSOR'),
('aluno.1@gmail.com', 'senha123', 'ALUNO'),
('aluno.2@gmail.com', 'senha123', 'ALUNO');

TRUNCATE TABLE professor RESTART IDENTITY CASCADE;
INSERT INTO professor (id, nome, departamento, cpf, instituicao_id, saldo) VALUES
(1, 'Professor 1', 'Departamento de Matemática', '12345678901', 1, 1000),
(2, 'Professor 2', 'Departamento de Física', '10987654321', 2, 1000);

TRUNCATE TABLE aluno RESTART IDENTITY CASCADE;
INSERT INTO aluno (id, nome, rg, cpf, curso, instituicao_id, saldo) VALUES
(3, 'Aluno 1', '385598300', '16547087042', 'Engenharia de Software', 1, 0),
(4, 'Aluno 2', '112926216', '66813014013', 'Ciência da Computaria', 2, 0);