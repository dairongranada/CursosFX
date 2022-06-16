
CREATE database sena;
USE sena;

CREATE TABLE personas (
  id int PRIMARY KEY AUTO_INCREMENT,
  identificacion char(20) NULL,
  nombre varchar(45) NULL,
  apellido varchar(45) NULL,
  edad varchar(45) NULL,
  tipo int NULL,
  email VARCHAR(100) NULL
);

CREATE TABLE tipo (
  idtipo int not null PRIMARY KEY AUTO_INCREMENT,
  Descripcion char(20) NULL
);

ALTER TABLE personas ADD FOREIGN KEY (tipo) REFERENCES tipo(idtipo);

CREATE TABLE cursos (
  codigo int not null PRIMARY KEY AUTO_INCREMENT,
  nombre varchar(45) NULL,
  instructor INT NULL
);

ALTER TABLE cursos ADD FOREIGN KEY (instructor) REFERENCES personas(id);

CREATE TABLE horarios (
  idHorario int not null PRIMARY KEY AUTO_INCREMENT,
  curso INT NULL,
  fecha DATE NULL,
  hora time
);

ALTER TABLE horarios ADD FOREIGN KEY (curso) REFERENCES cursos(codigo);

CREATE TABLE registro_cursos (
	id_registro INT AUTO_INCREMENT PRIMARY KEY,
    alumno INT NULL,
    curso  INT NULL
);

ALTER TABLE registro_cursos ADD FOREIGN KEY (alumno) REFERENCES personas(id);
ALTER TABLE registro_cursos ADD FOREIGN KEY (curso) REFERENCES cursos(codigo);

INSERT INTO tipo (Descripcion) VALUES ('Instructor');
INSERT INTO tipo (Descripcion) VALUES ('Estudiante');
INSERT INTO tipo (Descripcion) VALUES ('Admin');


/*==  H O R A R I I OS  ==*/
INSERT INTO horarios (día, hora) VALUES ('Lunes', '10:00');
INSERT INTO horarios (día, hora) VALUES ('Martes', '14:00');
INSERT INTO horarios (día, hora) VALUES ('Miercoles', '16:00');
INSERT INTO horarios (día, hora) VALUES ('Jueves', '18:00');
/* INSERT INTO horarios (día, hora) VALUES ('viernes', '20:00');*/

/*==  T I P O S  ==*/
INSERT INTO tipo (Descripcion) VALUES ('Instructor');
INSERT INTO tipo (Descripcion) VALUES ('Estudiante');
INSERT INTO tipo (Descripcion) VALUES ('admin');

INSERT INTO personas (identificacion, nombre, apellido, tipo, email) VALUES ('1985865', 'Henrry', 'Falla Ortiz', '1', 'hfallao@sena.edu.co');
INSERT INTO personas (identificacion, nombre, apellido, tipo, email) VALUES ('1089452', 'Ediwn', 'Gustavo', '1', 'edwin@misena.edu.co');
INSERT INTO personas (identificacion, nombre, apellido, tipo, email) VALUES ('1789623', 'Alvaro', 'Rodriguez', '1', 'arodrigue@misena.edu.co');
INSERT INTO personas (identificacion, nombre, apellido, edad, tipo, email) VALUES ('123456', 'AdminD', 'Dairon', '18', '3', 'DaironAdmin@gmail.com');
INSERT INTO personas (identificacion, nombre, apellido, edad, tipo, email) VALUES ('123456', 'AdminS', 'Steven', '18', '3', 'StevenAdmin@gmail.com');


/*------------------------------------------------------------------------------------------*/
CREATE USER 'root'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON sena.* TO 'ad'@'localhost';
FLUSH PRIVILEGES;