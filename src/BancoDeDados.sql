/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luanm
 * Created: 01/03/2018
 */

CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE IF NOT EXISTS pessoa(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(128),
    cpf INT,
    rua VARCHAR(128),
    bairro VARCHAR(64),
    email VARCHAR(64),
    telefone INT,
    id_funcionario INT NULL,
    id_leitor INT NULL
);

CREATE TABLE IF NOT EXISTS leitor(
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantidade_livros INT NULL DEFAULT 0,
    bloqueio BOOLEAN NULL DEFAULT FALSE,
    id_pessoa INT NULL
);

CREATE TABLE IF NOT EXISTS funcionario(
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(32) NOT NULL,
    senha VARCHAR(32) NOT NULL,
    id_pessoa INT NULL
);

CREATE TABLE IF NOT EXISTS livro(
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(128),
    autor VARCHAR(64),
    categoria VARCHAR(32),
    status VARCHAR(32),
    ano INT,
    isbn INT,
    edicao INT,
    id_emprestimo INT NULL,
    id_reserva INT NULL
);

CREATE TABLE IF NOT EXISTS emprestimo(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_funcionario INT NULL,
    id_leitor INT NULL,
    data_emprestimo DATE,
    data_devolucao DATE
);

CREATE TABLE IF NOT EXISTS reserva(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_funcionario INT NULL,
    id_leitor INT NULL,
    data_reserva DATE
);

-- Atribuindo relacionamento (Chaves estrangeiras) 

ALTER TABLE pessoa 
    ADD CONSTRAINT fk_pessoa_funcionario
        FOREIGN KEY(id_funcionario) REFERENCES funcionario(id),
    ADD CONSTRAINT fk_pessoa_leitor
        FOREIGN KEY(id_leitor) REFERENCES leitor(id);


ALTER TABLE funcionario ADD CONSTRAINT fk_funcionario_pessoa
    FOREIGN KEY(id_pessoa) REFERENCES pessoa(id);

ALTER TABLE leitor ADD CONSTRAINT fk_leitor_pessoa
    FOREIGN KEY(id_pessoa) REFERENCES pessoa(id);


ALTER TABLE livro 
    ADD CONSTRAINT fk_livro_emprestimo
        FOREIGN KEY(id_emprestimo) REFERENCES emprestimo(id),
    ADD CONSTRAINT fk_livro_reserva
        FOREIGN KEY(id_reserva) REFERENCES reserva(id);

ALTER TABLE emprestimo 
    ADD CONSTRAINT fk_emprestimo_funcionario
        FOREIGN KEY(id_funcionario) REFERENCES funcionario(id),
    ADD CONSTRAINT fk_emprestimo_leitor
        FOREIGN KEY(id_leitor) REFERENCES leitor(id);

ALTER TABLE reserva
    ADD CONSTRAINT fk_reserva_funcionario
        FOREIGN KEY(id_funcionario) REFERENCES funcionario(id),
    ADD CONSTRAINT fk_reserva_leitor
        FOREIGN KEY(id_leitor) REFERENCES leitor(id);
