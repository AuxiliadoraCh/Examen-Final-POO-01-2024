create database parcialdb;
use parcialdb;
drop database parcialdb;

create table Cliente (
     id int auto_increment primary key,
     nombres varchar(50),
     apellidos varchar(59),
     direccion varchar(70),
     telefono varchar(32)
);

create table Tarjeta (
    id int auto_increment primary key,
    id_cliente int not null,
    tarjeta_num varchar(32),
    exp_date date,
    tipo varchar(20),
    id_facilitador int not null
);

create table Facilitador (
    id int auto_increment primary key,
    facilitador varchar(20)
);

create table Compra (
    id int auto_increment primary key,
    fecha date,
    monto float,
    descripcion text,
    id_tarjeta int not null
);

alter table Tarjeta add foreign key (id_cliente) references Cliente (id) on delete cascade;
alter table Tarjeta add foreign key (id_facilitador) references Facilitador (id) on delete cascade;
alter table Compra add foreign key (id_tarjeta) references Tarjeta (id) on delete cascade;

insert into Cliente (nombres, apellidos, direccion, telefono) values ('Guillermo Rafael','Caceres Estrada','Buena Vista 1','7223-8563');
insert into Cliente (nombres, apellidos, direccion, telefono) values ('Maria Auxiliadora','Chinchilla','Morazan','7298-4619');
insert into Cliente (nombres, apellidos, direccion, telefono) values ('Rebeca Elizabeth','Torres Angel','Lourdes','6029-2320');
insert into Cliente (nombres, apellidos, direccion, telefono) values ('Juan Jose','Melgar','San Salvador','6312-1741');
insert into Cliente (nombres, apellidos, direccion, telefono) values ('Carla Rosa','Flores Fernandez','San Salvador','6312-1741');
insert into Cliente (nombres, apellidos, direccion, telefono) values ('Jonataha Jose','Lopez Queso','San Salvador','6312-1741');
insert into Cliente (nombres, apellidos, direccion, telefono) values ('Quemador Sosa','Perdonado','San Salvador','6312-1741');

insert into Facilitador (facilitador) values ('Visa');
insert into Facilitador (facilitador) values ('Mastercard');
insert into Facilitador (facilitador) values ('American Express');

insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (1, '2222 3333 4444 5555', '2027-06-22','Credito',1);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (1, '2325 3093 6454 2525', '2028-04-03','Debito',2);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (2, '2124 3090 9434 3524', '2026-08-03','Debito',3);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (2, '1121 3190 1414 3124', '2027-02-03','Credito',1);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (3, '4221 5195 5410 3220', '2029-07-23','Credito',2);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (3, '6511 5198 9810 9822', '2026-03-13','Debito',1);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (4, '6234 5165 6510 6522', '2027-02-14','Debito',3);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (4, '0286 8767 6616 6423', '2025-04-24','Credito',1);
insert into Tarjeta (id_cliente,tarjeta_num,exp_date,tipo,id_facilitador) values (5, '8745 1452 9856 3256', '2025-12-24','Credito',1);

insert into Compra (fecha,monto,descripcion,id_tarjeta) values ('2023-04-05',23.5,'Pago de colegiatura',1);
insert into Compra (fecha,monto,descripcion,id_tarjeta) values ('2024-02-22',55.6,'Pago de membresia',3);
insert into Compra (fecha,monto,descripcion,id_tarjeta) values ('2022-06-25',100.5,'Pago de deuda',5);
insert into Compra (fecha,monto,descripcion,id_tarjeta) values ('2023-12-24',250.80,'Regalo de bodas',8);