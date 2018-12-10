DROP TABLE evaluacion_alumno IF EXISTS;
DROP TABLE evaluacion IF EXISTS;
DROP TABLE mensaje IF EXISTS;
DROP TABLE asignatura IF EXISTS;
DROP TABLE anotacion IF EXISTS;
DROP TABLE profesor IF EXISTS;
DROP TABLE alumno IF EXISTS;
DROP TABLE apoderado IF EXISTS;
DROP TABLE curso IF EXISTS;

DROP TABLE roles IF EXISTS;
DROP TABLE users IF EXISTS;

create table curso
(
  id      int identity primary key,
  grado   int          not null,
  nivel   varchar(10)  not null,
  clase   varchar(1)   not null
);

create table apoderado
(
  id        int identity primary key,
  nombre    varchar(45)  not null,
  apellido  varchar(45)  not null
);

create table alumno
(
  id          int identity primary key,
  nombre      varchar(45)  not null,
  apellido    varchar(45)  not null,
  idCurso     int          not null,
  idApoderado int          not null
);

ALTER TABLE alumno ADD CONSTRAINT fk_curso FOREIGN KEY (idCurso) REFERENCES curso (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_curso_idx ON alumno (idCurso);

ALTER TABLE alumno ADD CONSTRAINT fk_apoderado FOREIGN KEY (idApoderado) REFERENCES apoderado (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_apoderado_idx ON alumno (idApoderado);


create table profesor
(
  id       int identity primary key,
  nombre   varchar(45)  not null,
  apellido varchar(45)  not null
);

create table anotacion
(
  id         int identity primary key,
  texto      varchar(300) not null,
  tipo       int          not null,
  fecha      date         not null,
  idAlumno   int          not null,
  idProfesor int          not null
);

ALTER TABLE anotacion ADD CONSTRAINT fk_alumno FOREIGN KEY (idAlumno) REFERENCES alumno (id);
CREATE INDEX fk_alumno2_idx ON anotacion (idAlumno);

ALTER TABLE anotacion ADD CONSTRAINT fk_profesor2 FOREIGN KEY (idProfesor) REFERENCES profesor (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_profesor2_idx ON anotacion (idProfesor);

create table asignatura
(
  id         int identity primary key,
  nombre     varchar(45)  not null,
  idProfesor int          not null
);

ALTER TABLE asignatura ADD CONSTRAINT fk_profesor3 FOREIGN KEY (idProfesor) REFERENCES profesor (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_profesor3_idx ON asignatura (idProfesor);

create table mensaje
(
	id         int identity primary key,
	texto      varchar(300) not null,
	fecha      date			not null,
	idEmisor   int,
	idReceptor int
);

ALTER TABLE mensaje ADD CONSTRAINT fk_emisor FOREIGN KEY (idEmisor) REFERENCES profesor (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_emisor_idx ON mensaje (idEmisor);

ALTER TABLE mensaje ADD CONSTRAINT fk_receptor FOREIGN KEY (idReceptor) REFERENCES apoderado (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_receptor_idx ON mensaje (idReceptor);


create table evaluacion
(
	id           int identity primary key,
	fecha        date,
    idAsignatura int
);

ALTER TABLE evaluacion ADD CONSTRAINT fk_Asignatura FOREIGN KEY (idAsignatura) REFERENCES Asignatura (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_asignatura_idx ON Evaluacion (idAsignatura);

create table evaluacion_alumno
(
	id           int identity primary key,
	nota         int		  not null,
	idEvaluacion int,
	idAlumno     int
);

ALTER TABLE evaluacion_alumno ADD CONSTRAINT fk_Evaluacion FOREIGN KEY (idEvaluacion) REFERENCES Evaluacion (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_evaluacion_idx ON evaluacion_alumno (idEvaluacion);

ALTER TABLE evaluacion_alumno ADD CONSTRAINT fk_Alumno3 FOREIGN KEY (idAlumno) REFERENCES Alumno (id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_alumno3_idx ON evaluacion_alumno (idAlumno);

CREATE  TABLE users (
  username    VARCHAR(20) NOT NULL,
  password    VARCHAR(20) NOT NULL,
  enabled     BOOLEAN DEFAULT TRUE NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE roles (
  id          INT PRIMARY KEY,
  username    VARCHAR(20) NOT NULL,
  role        VARCHAR(20) NOT NULL
);
ALTER TABLE roles ADD CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX fk_username_idx ON roles (username);