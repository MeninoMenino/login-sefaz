create database loginsefaz;
use loginsefaz;

create table usuario(
	id bigint not null auto_increment,
    nome varchar(50) not null, 
    email varchar(50) not null, 
    senha varchar(50) not null,
    primary key (id)
);

create table usuario_telefones(
	usuario_id bigint not null,
    ddd integer not null, 
    numero varchar(10) not null, 
    tipo varchar(15) not null,
    foreign key(usuario_id) references usuario(id)
);

select * from usuario;