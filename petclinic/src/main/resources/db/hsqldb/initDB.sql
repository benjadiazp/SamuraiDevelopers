
DROP TABLE roles IF EXISTS;
DROP TABLE users IF EXISTS;

DROP TABLE curso IF EXISTS;
DROP TABLE asignatura IF EXISTS;
DROP TABLE mensaje IF EXISTS;
DROP TABLE anotacion IF EXISTS;
DROP TABLE profesor IF EXISTS;
DROP TABLE apoderado IF EXISTS;
DROP TABLE alumno IF EXISTS;




CREATE  TABLE users (
  username    VARCHAR(20) NOT NULL ,
  password    VARCHAR(20) NOT NULL ,
  enabled     BOOLEAN DEFAULT TRUE NOT NULL ,
  PRIMARY KEY (username)
);

CREATE TABLE roles (
  id              INTEGER PRIMARY KEY,
  username        VARCHAR(20) NOT NULL,
  role            VARCHAR(20) NOT NULL
);
ALTER TABLE roles ADD CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username);
CREATE INDEX fk_username_idx ON roles (username);

create table curso
(
  id int not null primary key,
  grado   tinyint      null,
  nivel   varchar(10)      null,
  clase   varchar(1)       null
);

create table apoderado
(
  id int not null primary key,
  nombre    varchar(45) null,
  apellido    varchar(45) null
);

create table alumno
(
  id int not null primary key,
  nombre    varchar(45) null,
  apellido    varchar(45) null,
  idCurso     int not null,
  idApoderado int not null
);

ALTER TABLE alumno ADD CONSTRAINT fk_curso FOREIGN KEY (idCurso) REFERENCES curso (id);
CREATE INDEX fk_curso_idx ON alumno (idCurso);

ALTER TABLE alumno ADD CONSTRAINT fk_apoderado FOREIGN KEY (idApoderado) REFERENCES apoderado (id);
CREATE INDEX fk_apoderado_idx ON alumno (idApoderado);



create table profesor
(
  id int not null primary key,
  nombre    varchar(45) null,
  apellido    varchar(45) null
);

create table anotacion
(
  id int primary key,
  texto       varchar(300) null,
  tipo        tinyint      null,
  fecha       date         null,
  idAlumno    int          null,
  idProfesor  int          null
);

ALTER TABLE anotacion ADD CONSTRAINT fk_alumno FOREIGN KEY (idAlumno) REFERENCES alumno (id);
CREATE INDEX fk_alumno2_idx ON anotacion (idAlumno);

ALTER TABLE anotacion ADD CONSTRAINT fk_profesor2 FOREIGN KEY (idProfesor) REFERENCES profesor (id);
CREATE INDEX fk_profesor2_idx ON anotacion (idProfesor);

create table asignatura
(
  id int not null primary key,
  nombre varchar(45)      null,
  idProfesor   int null
);

ALTER TABLE asignatura ADD CONSTRAINT fk_profesor3 FOREIGN KEY (idProfesor) REFERENCES profesor (id);
CREATE INDEX fk_profesor3_idx ON asignatura (idProfesor);

create table mensaje
(
	id int primary key,
	idEmisor int,
	idReceptor int,
	texto varchar(300),
	fecha date
);

ALTER TABLE mensaje ADD CONSTRAINT fk_emisor FOREIGN KEY (idEmisor) REFERENCES profesor (id);
CREATE INDEX fk_emisor_idx ON mensaje (idEmisor);

ALTER TABLE mensaje ADD CONSTRAINT fk_receptor FOREIGN KEY (idReceptor) REFERENCES apoderado (id);
CREATE INDEX fk_receptor_idx ON mensaje (idReceptor);