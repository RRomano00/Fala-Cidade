begin;

    -- Apaga as tabelas na ordem reversa das dependências
    drop table if exists media cascade;
    drop table if exists report cascade;
    drop table if exists classification cascade;
    drop table if exists city_employee cascade;
    drop table if exists department cascade;
    drop table if exists complainant cascade;
    drop table if exists administrator cascade;
    drop table if exists city cascade;
    drop table if exists users cascade;

    create type role_type as enum ('USER', 'ADMINISTRATOR', 'EMPLOYEE');


    create table users(
        id serial primary key,
        fullname varchar(50) not null,
        email varchar(40) not null unique,
        password varchar(255) not null,
        phone_number varchar(16) unique,
        cpf varchar(14) unique,
        role role_type not null default 'USER'
    );

    create table city(
        id serial primary key,
        name varchar(100) not null,
        state varchar(100) not null,
        unique(name, state)
    );

    create table complainant(
        id serial primary key,
        neighborhood varchar(100) not null,
        number varchar(10) not null,
        street varchar(100) not null,
        city varchar(100) not null,
        cep varchar(9),
        city_id integer not null references city(id),
        users_id integer not null references users(id) on delete cascade,
        unique(users_id)
    );

    create table department(
        id serial primary key,
        name varchar(50) not null,
        city_id integer not null references city(id) on delete cascade
    );

    create table city_employee(
        id serial primary key,
        city_id integer not null references city(id),
        department_id integer not null references department(id),
        users_id integer not null references users(id) on delete cascade,
        unique(users_id)
    );

    create table report(
        id serial primary key,
        description text not null,
        creation_date timestamp not null default now(),
        neighborhood varchar(100) not null,
        number varchar(20) not null,
        street varchar(100) not null,
        city varchar(30) not null,
        status varchar(15) not null check (status in ('PENDENTE', 'EM_ANDAMENTO', 'ATENDIDA')) default 'PENDENTE',
        type varchar(50) not null check (type in (
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
            'PESSOA_PRECISANDO_DE_AJUDA',
            'OUTROS_PROBLEMAS'
        )),
        department_id integer references department(id),
        city_employee_id integer references city_employee(id),
        complainant_id integer references complainant(id),
        unique (complainant_id, creation_date)
    );

    create table media(
        id serial primary key,
        file_path text not null,
        report_id integer not null references report(id) on delete cascade
    );

    create table administrator(
        id serial primary key,
        users_id integer not null references users(id) on delete cascade,
        unique(users_id)
    );

commit;
