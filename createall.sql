CREATE database gesoc_db character set UTF8;
use gesoc_db;

CREATE TABLE presupuestos
(
id_presupuesto int not null primary key
);

CREATE TABLE usuarios
(
id_usuario int not null primary key,
contrasenia varchar(50) not null,
nombre varchar(50) not null
);

CREATE TABLE items
(
id_item int not null primary key,
descripcion varchar(50) not null,
tipo varchar(50) not null
);

CREATE TABLE categorias
(
id_categoria int not null primary key,
nombre varchar(50),
monto_maximo int not null,
tipo varchar(50)
);

CREATE TABLE organizaciones
(
id_organizacion int not null auto_increment primary key,
id_categoria int not null,
foreign key (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE egresos
(
id_egreso int not null auto_increment,
id_presupuesto int not null,
id_usuario int not null,
fecha date not null,
primary key (id_egreso),
foreign key (id_presupuesto) REFERENCES presupuestos(id_presupuesto),
foreign key (id_usuario) REFERENCES usuarios(id_usuario)
);


