CREATE TABLE jogos(
	id_jogo INTEGER PRIMARY KEY,
	titulo_jogo varchar(100),
	genero_jogo varchar(100),
	preco_jogo double,
	descricao_jogo TEXT,
	imagem_jogo longblob 
);

CREATE TABLE usuarios (
    id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo_usuario VARCHAR(50),
    nome_usuario VARCHAR(50) NOT NULL,
    senha_usuario VARCHAR(100) NOT NULL,
    foto_usuario longblob
);

CREATE TABLE compra (
    id_compra INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    id_usuario INT,
    id_jogo INT,
    preco_total DOUBLE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_jogo) REFERENCES jogos(id_jogo)
);


CREATE TABLE jogos_favoritos (
    id_usuario INT,
    id_jogo INT,
    PRIMARY KEY (id_usuario, id_jogo),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_jogo) REFERENCES jogos(id_jogo)
);

