CREATE TABLE Peca (
	id			INTEGER IDENTITY PRIMARY KEY,
	descricao	VARCHAR(500),
	categoria	VARCHAR(100),
	tipo		VARCHAR(100),
	imagem		LONGVARBINARY,
	status		VARCHAR(70),
	preco		DOUBLE
);


