TRUNCATE TABLE instituicao_ensino RESTART IDENTITY CASCADE;

INSERT INTO instituicao_ensino (nome, cnpj) VALUES
('Pontifícia Universidade Católica de Minas Gerais (PUC-MG)', '67890123000174'),
('Universidade Federal de Minas Gerais (UFMG)', '34567890000102'),
('Universidade de São Paulo (USP)', '12345678000195'),
('Universidade Estadual de Campinas (UNICAMP)', '23456789000109'),
('Universidade Federal do Rio de Janeiro (UFRJ)', '45678901000116');
