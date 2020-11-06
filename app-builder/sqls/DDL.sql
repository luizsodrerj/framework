CREATE TABLE APP (
	ID INTEGER IDENTITY PRIMARY KEY,
	NOME_APP VARCHAR(300) NOT NULL
);

CREATE TABLE FORM (
	ID INTEGER IDENTITY PRIMARY KEY,
	NOME_FORM VARCHAR(500) NOT NULL,
	ID_APP INTEGER NOT NULL
);

ALTER TABLE FORM
ADD FOREIGN KEY (ID_APP) 
REFERENCES APP(ID);


CREATE TABLE FORM_FIELDS (
	ID 		INTEGER IDENTITY PRIMARY KEY,
	LABEL 	VARCHAR(900),
	TIPO 	VARCHAR(45),
	FORMATO VARCHAR(150),
	ID_FORM INTEGER NOT NULL
);

ALTER TABLE FORM_FIELDS
ADD FOREIGN KEY (ID_FORM) 
REFERENCES FORM(ID);


CREATE TABLE TRANSACTION (
	ID BIGINT IDENTITY PRIMARY KEY,
	ID_FORM INTEGER NOT NULL
);

ALTER TABLE TRANSACTION
ADD FOREIGN KEY (ID_FORM) 
REFERENCES FORM(ID);

CREATE TABLE FIELD_TRANSACTION (
	ID BIGINT IDENTITY PRIMARY KEY,
	FIELD_ID	INTEGER NOT NULL,
	TX_ID 		BIGINT NOT NULL,
	FIELD_VALUE VARCHAR(3000),
	BLOB_VALUE BINARY(5242880)
);

ALTER TABLE FIELD_TRANSACTION
ADD FOREIGN KEY (FIELD_ID) 
REFERENCES FORM_FIELDS(ID);

ALTER TABLE FIELD_TRANSACTION
ADD FOREIGN KEY (TX_ID) 
REFERENCES TRANSACTION(ID);

CREATE TABLE COMPONENT_TYPE (
	ID INT IDENTITY PRIMARY KEY,
	TIPO VARCHAR(350) NOT NULL
);

ALTER TABLE FORM_FIELDS
ADD COMP_TYPE_ID INT;

ALTER TABLE FORM_FIELDS
ADD FOREIGN KEY (COMP_TYPE_ID) 
REFERENCES COMPONENT_TYPE(ID);


















