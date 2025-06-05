begin;

    create table usuario(
        id serial primary key,
        nome varchar(50) not null,
        username varchar(15) not null unique,
        email varchar(40) not null unique,
        senha varchar(20) not null,
        telefone varchar(16),
        cpf varchar(14) unique

    );

    create table denunciante(
        id primary key,
        bairro varchar(100) not null,
        numero integer not null,
        logradouro varchar(100) not null,
        cep varchar(9)
    );

    create table funcionarioPrefeitura(
        id primary key
    );


    create table denuncia(
        id primary key,
        descricao text not null,
        data_criacao date timestamp not null default now(),
        bairro varchar(100) not null,
        numero integer not null,
        logradouro varchar(100) not null,
        cep varchar(9),
        status 
    
    );

    create table administrador(
        id serial primary key
    );

commit;