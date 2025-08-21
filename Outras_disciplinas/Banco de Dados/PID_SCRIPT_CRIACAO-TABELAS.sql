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
        rua varchar(100) not null,
        cep varchar(9),
        situacao varchar(15) not null check (situacao in ('Pendente','Em análise', 'Em andamento', 'Concluída')),
        tipo VARCHAR(50) NOT NULL CHECK (tipo IN (
        'BURACO_NA_RUA_OU_CALCADA',
        'POSTE_COM_LUZ_QUEIMADA',
        'LIXO_ACUMULADO_OU_TERRENO_SUJO',
        'SINALIZACAO_OU_SEMAFORO_COM_DEFEITO',
        'PROBLEMAS_EM_PRACAS_E_PARQUES',
        'FALHAS_NO_TRANSPORTE_PUBLICO',
        'PROBLEMAS_EM_POSTO_DE_SAUDE_OU_ESCOLA',
        'SOM_ALTO_OU_PERTURBACAO_DO_SOSSEGO',
        'OBRA_IRREGULAR_OU_IMOVEL_ABANDONADO',
        'MAUS_TRATOS_AOS_ANIMAIS',
        'PESSOA_PRECISANDO_DE_AJUDA_SOCIAL',
        'OUTROS_PROBLEMAS'));

        secretaria_id integer not null references secretaria(id),
        municipio_id integer not null references municipio(id) on delete cascade,
        funcionarioPrefeitura_id integer not null references funcionarioPrefeitura(id),
        denunciante_id integer not null references denunciante(id),
        classificacao_id integer not null references classificacao(id),

        UNIQUE (denunciante_id, data_criacao)
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