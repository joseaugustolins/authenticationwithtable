# authenticationwithtable

- It is mandatory to start PostgreSQL and insert user records on the database
` docker run -p 5432:5432 -d postgres`
`insert into role(nome_role) values('ROLE_ADMIN');
insert into role(nome_role) values('ROLE_USER');
insert into usuario(login, nome, senha) values('joao', 'joao carlos', '$2a$10$Qp8y0r6u62BFtDAPkeZLJew584Q9codaZTAaIy.WyFszxz.GLbb42');
insert into usuario(login, nome, senha) values('maria', 'maria jos', '$2a$10$Qp8y0r6u62BFtDAPkeZLJew584Q9codaZTAaIy.WyFszxz.GLbb42');
insert into usuarios_roles(usuario_id, role_id) values('joao', 'ROLE_ADMIN');
insert into usuarios_roles(usuario_id, role_id) values('joao', 'ROLE_USER');
insert into usuarios_roles(usuario_id, role_id) values('maria', 'ROLE_USER');`