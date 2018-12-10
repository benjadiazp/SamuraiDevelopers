CREATE DATABASE IF NOT EXISTS petclinic;

ALTER DATABASE petclinic
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON petclinic.* TO pc@localhost IDENTIFIED BY 'pc';

USE petclinic;

CREATE TABLE roles (
  id              INTEGER IDENTITY PRIMARY KEY,
  username        VARCHAR(20) NOT NULL,
  role            VARCHAR(20) NOT NULL
);
ALTER TABLE roles ADD CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username);
CREATE INDEX fk_username_idx ON roles (username);

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
  idApoderado int not null,
  foreign key (idApoderado) references Apoderado (id)
);

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
  idProfesor  int          null,
  foreign key (idAlumno) references Alumno (id),
  foreign key (idProfesor) references Profesor (id)
);
