CREATE TABLE status_pedido (
	id 			INT NOT NULL PRIMARY KEY,
	ds_status	VARCHAR(50)
);

CREATE TABLE Forma_Pagamento (
	id 		INT AUTO_INCREMENT PRIMARY KEY,
	forma 	VARCHAR(100)
);


CREATE TABLE item (
	id 			bigint AUTO_INCREMENT PRIMARY KEY,
	codigo 		VARCHAR(35),
	descricao 	VARCHAR(200),
	qtd_Estoque int,
	valor		double
);


create table Pedido_Venda (
	id 				bigint AUTO_INCREMENT PRIMARY KEY,
	id_status		int,
	id_forma_pgto	int,
	data			date
);

alter table pedido_venda
add constraint fk_st foreign key (id_status)
references status_pedido(id);

alter table pedido_venda
add constraint fk_frm foreign key (id_forma_pgto)
references Forma_Pagamento(id);

create table Item_Pedido(
	id 			bigint AUTO_INCREMENT PRIMARY KEY,
	codigo 		VARCHAR(35),
	descricao 	VARCHAR(200),
	qtd_pedida 	int,
	valor_Venda	double,
	id_item_pai bigint,
	id_pedido	bigint
);

alter table Item_Pedido
add constraint fk_it foreign key (id_item_pai)
references item(id);

alter table Item_Pedido
add constraint fk_ped foreign key (id_pedido)
references pedido_venda(id);







