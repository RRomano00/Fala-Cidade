begin;

insert into users (fullname, email, password, phone_number, cpf, role) values
('João Silva', 'joao@email.com', 'senha123', '11999999999', '123.456.789-00', 'ADMINISTRATOR'),
('Maria Souza', 'maria@email.com', 'senha456', '11988888888', '987.654.321-00', 'USER'),
('Carlos Lima', 'carlos@email.com', 'senha789', '11977777777', '111.222.333-44', 'EMPLOYEE'),
('Ana Paula', 'ana@email.com', 'senhaabc', '11966666666', '555.666.777-88', 'EMPLOYEE');

insert into city (name, state) values
('São Paulo', 'SP'),
('Rio de Janeiro', 'RJ');

insert into report (description, neighborhood, number, street, city, status, type, users_id)
values
('Buraco grande na rua', 'Centro', '100', 'Rua A', 'São Paulo', 'PENDENTE', 'BURACO_NA_RUA_OU_CALCADA', 1),
('Lixo acumulado na praça', 'Copacabana', '200', 'Avenida Atlântica', 'Rio de Janeiro', 'EM_ANDAMENTO', 'LIXO_ACUMULADO_OU_TERRENO_SUJO', 2);

commit;
