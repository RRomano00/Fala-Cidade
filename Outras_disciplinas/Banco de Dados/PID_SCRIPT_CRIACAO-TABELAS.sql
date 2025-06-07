begin;

    create table usuario(
        id serial primary key,
        nome varchar(50) not null,
        username varchar(15) not null unique,
        email varchar(40) not null unique,
        senha varchar(255) not null,
        telefone varchar(16),
        cpf varchar(14) unique

    );

    create table municipio(
        id serial primary key,
        nome varchar(100) not null,
        estado varchar(100) not null,
        unique(nome,estado)

    );

    create table denunciante(
        id serial primary key,
        bairro varchar(100) not null,
        numero integer not null,
        logradouro varchar(100) not null,
        cep varchar(9),

        municipio_id integer not null references municipio(id),
        usuario_id integer not null references usuario(id) on delete cascade,
        unique (usuario_id)
    );

    create table secretaria(
        id serial primary key,

        nome varchar(50) not null,

        municipio_id integer not null references municipio(id) on delete cascade
    );

    create table funcionarioPrefeitura(
        id serial primary key,

        municipio_id integer not null references municipio(id),
        secretaria_id integer not null references secretaria(id),
        usuario_id integer not null references usuario(id) on delete cascade,
        unique(usuario_id)
    );

    create table classificacao(
        id serial primary key,
        nome varchar(50) not null unique,
        descricao text not null,
        prioridade varchar(20) not null check (prioridade in ('Alta','Média','Baixa')) 

    );

    create table denuncia(
        id serial primary key,
        descricao text not null,
        data_criacao timestamp not null default now(),
        bairro varchar(100) not null,
        numero integer not null,
        logradouro varchar(100) not null,
        cep varchar(9),
        situacao varchar(15) not null check (situacao in ('Pendente','Em análise', 'Em andamento', 'Concluída')),
        numeroProtocolo integer not null,

        secretaria_id integer not null references secretaria(id),
        municipio_id integer not null references municipio(id) on delete cascade,
        funcionarioPrefeitura_id integer not null references funcionarioPrefeitura(id),
        denunciante_id integer not null references denunciante(id),
        classificacao_id integer not null references classificacao(id),
        unique(numeroProtocolo)

    );

    create table midia(
        id serial primary key,
        caminho_arquivo text not null, 
        denuncia_id integer not null references denuncia(id) on delete cascade
    );

    create table administrador(
        id serial primary key,
        usuario_id integer not null references usuario(id) on delete cascade
    );


commit;