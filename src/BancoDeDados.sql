/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luanm
 * Created: 01/03/2018
 */

DROP DATABASE biblioteca;

CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE IF NOT EXISTS pessoas(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(128),
    cpf VARCHAR(12),
    rua VARCHAR(128),
    bairro VARCHAR(64),
    email VARCHAR(64),
    telefone VARCHAR(12),
    id_funcionario INT NULL,
    id_leitor INT NULL
);

CREATE TABLE IF NOT EXISTS leitores(
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantidade_livros INT NULL DEFAULT 0,
    bloqueio BOOLEAN NULL DEFAULT FALSE,
    id_pessoa INT NULL
);

CREATE TABLE IF NOT EXISTS funcionarios(
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(32) NOT NULL,
    senha VARCHAR(32) NOT NULL,
    id_pessoa INT NULL
);

CREATE TABLE IF NOT EXISTS livros(
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(128),
    autor VARCHAR(64),
    categoria VARCHAR(32),
    status VARCHAR(32),
    ano INT,
    isbn INT,
    edicao INT,
    id_emprestimo INT NULL
);

CREATE TABLE IF NOT EXISTS emprestimos(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_funcionario INT NULL,
    id_leitor INT NULL,
    data_emprestimo DATE,
    data_devolucao DATE
);

CREATE TABLE IF NOT EXISTS reservas(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_funcionario INT NULL,
    id_leitor INT NULL,
    id_livro INT NULL,
    data_reserva DATE
);

CREATE TABLE IF NOT EXISTS livrosreservados(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_livro INT NULL,
    id_reserva INT NULL
);

-- Atribuindo relacionamento (Chaves estrangeiras) 

ALTER TABLE pessoas
    ADD CONSTRAINT fk_pessoa_funcionario
        FOREIGN KEY(id_funcionario) REFERENCES funcionarios(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_pessoa_leitor
        FOREIGN KEY(id_leitor) REFERENCES leitores(id) ON DELETE CASCADE;


ALTER TABLE funcionarios ADD CONSTRAINT fk_funcionario_pessoa
    FOREIGN KEY(id_pessoa) REFERENCES pessoas(id) ON DELETE CASCADE;

ALTER TABLE leitores ADD CONSTRAINT fk_leitor_pessoa
    FOREIGN KEY(id_pessoa) REFERENCES pessoas(id) ON DELETE CASCADE;


ALTER TABLE livros
    ADD CONSTRAINT fk_livro_emprestimo
        FOREIGN KEY(id_emprestimo) REFERENCES emprestimos(id) ON DELETE CASCADE;

ALTER TABLE emprestimos
    ADD CONSTRAINT fk_emprestimo_funcionario
        FOREIGN KEY(id_funcionario) REFERENCES pessoas(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_emprestimo_leitor
        FOREIGN KEY(id_leitor) REFERENCES pessoas(id) ON DELETE CASCADE;

ALTER TABLE reservas
    ADD CONSTRAINT fk_reserva_funcionario
        FOREIGN KEY(id_funcionario) REFERENCES pessoas(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_reserva_leitor
        FOREIGN KEY(id_leitor) REFERENCES pessoas(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_reserva_livro
        FOREIGN  KEY(id_livro) REFERENCES livros(id) ON DELETE CASCADE;


INSERT INTO pessoas (nome, cpf, rua, bairro, email, telefone) VALUES
    ("Pessoa1", "1234", "rua 1", "bairro 1", "pessoa@gmail.com", "999999");
INSERT INTO leitores (quantidade_livros, bloqueio, id_pessoa) VALUES
    (0, false, 1);
UPDATE pessoas SET id_leitor = 1 WHERE id = 1;

INSERT INTO pessoas (nome, cpf, rua, bairro, email, telefone) VALUES
    ("funcionario", "555", "Alfeneiiros 1", "centro", "caraca@gmail.com", "2222");
INSERT INTO funcionarios (usuario, senha, id_pessoa) VALUES
    ("func", "123", 2);
UPDATE pessoas SET id_funcionario = 1 WHERE id = 2;
