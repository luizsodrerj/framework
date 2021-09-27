CREATE TABLE Peca (
	id			INTEGER IDENTITY PRIMARY KEY,
	descricao	VARCHAR(500),
	categoria	VARCHAR(100),
	tipo		VARCHAR(100),
	imagem		LONGVARBINARY,
	status		VARCHAR(70),
	preco		DOUBLE
);


CREATE TABLE mensagem (
	id			INTEGER IDENTITY PRIMARY KEY,
	nome		VARCHAR(500),
	email		VARCHAR(300),
	telefone	VARCHAR(11),
	mensagem	VARCHAR(1500),
	status		INTEGER DEFAULT 1,
	data		DATE
);

