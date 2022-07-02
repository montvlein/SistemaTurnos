DROP TABLE IF EXISTS odontologos;
DROP TABLE IF EXISTS pacientes;
DROP TABLE IF EXISTS domicilios;

CREATE TABLE IF NOT EXISTS odontologos (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
matricula VARCHAR(225),
nombre VARCHAR(225),
apellido VARCHAR(225)
);

CREATE TABLE IF NOT EXISTS pacientes (
    id int auto_increment primary key,
    apellido varchar(255),
    nombre varchar(255),
    fecha_ingreso DATE,
    id_domicilio long);

CREATE TABLE IF NOT EXISTS domicilios (
    id int auto_increment primary key,
    calle varchar(255),
    numero int,
    localidad varchar(255),
    provincia varchar(255));

 CREATE TABLE IF NOT EXISTS LOGS
       (
               USER_ID VARCHAR(20) NOT NULL,
               DATED   DATETIME NOT NULL,
               LOGGER  VARCHAR(255) NOT NULL,
               LEVEL   VARCHAR(10) NOT NULL,
               MESSAGE VARCHAR(1000) NOT NULL
       );


-- Pueden agregar acá sentencias Insert para precargar datos.
INSERT INTO domicilios(calle, numero, localidad, provincia) VALUES('Test1', 1, 'Localidad Test1', 'Provincia Test1');
INSERT INTO pacientes(apellido, nombre, fecha_ingreso, id_domicilio) VALUES('Apellido1', 'Nombre1', '2022-05-31', 1);
INSERT INTO odontologos(apellido, nombre, matricula) VALUES('Apellido Od1', 'Nombre Od1', '445566');
INSERT INTO odontologos (matricula, nombre, apellido) VALUES ('matriculaTest', 'nombreTest', 'apellidoTest');
INSERT INTO odontologos VALUES (999999, 'matriculaIdTest', 'nombreIdTest', 'apellidoIdTest');
