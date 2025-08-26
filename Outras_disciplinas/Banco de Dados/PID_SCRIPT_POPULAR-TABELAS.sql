begin;

-- Usuários
insert into users (fullname, email, password, phone_number, cpf) values
('João Silva', 'joao@email.com', 'senha123', '11999999999', '123.456.789-00'),
('Maria Souza', 'maria@email.com', 'senha456', '11988888888', '987.654.321-00'),
('Carlos Lima', 'carlos@email.com', 'senha789', '11977777777', '111.222.333-44'),
('Ana Paula', 'ana@email.com', 'senhaabc', '11966666666', '555.666.777-88');

-- Cidades
insert into city (name, state) values
('São Paulo', 'SP'),
('Rio de Janeiro', 'RJ');

-- Complainants
insert into complainant (neighborhood, number, street, city, cep, city_id, users_id) values
('Centro', '100', 'Rua A', 'São Paulo', '01000-000', 1, 1),
('Copacabana', '200', 'Avenida Atlântica', 'Rio de Janeiro', '22000-000', 2, 2);

-- Departamentos
insert into department (name, city_id) values
('Obras', 1),
('Iluminação', 1),
('Limpeza Urbana', 2);

-- Funcionários da cidade
insert into city_employee (city_id, department_id, users_id) values
(1, 1, 3),
(2, 3, 4);

-- Classificações (entidade fraca)
insert into classification (priority) values
('Alta'),
('Media');

-- Relatórios
insert into report (description, neighborhood, number, street, city, status, type, department_id, city_employee_id, complainant_id, classification_id)
values
('Buraco grande na rua', 'Centro', '100', 'Rua A', 'São Paulo', 'Pendente', 'BURACO_NA_RUA_OU_CALCADA', 1, 1, 1, 1),
('Lixo acumulado na praça', 'Copacabana', '200', 'Avenida Atlântica', 'Rio de Janeiro', 'Em andamento', 'LIXO_ACUMULADO_OU_TERRENO_SUJO', 3, 2, 2, 2);

-- Mídias
insert into media (file_path, report_id) values
('uploads/buraco.jpg', 1),
('uploads/lixo.jpg', 2);

-- Administradores
insert into administrator (users_id) values
(1);

commit;