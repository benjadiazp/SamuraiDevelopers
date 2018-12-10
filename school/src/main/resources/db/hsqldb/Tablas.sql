create table Persona
(
  idPersona int not null primary key,
  nombre    varchar(45) null,
  apellido    varchar(45) null
);

create table Apoderado
(
  idApoderado int not null,
  foreign key (idApoderado) references Persona (idPersona)
);

create table Alumno
(
  idAlumno    int not null,
  idCurso     int not null,
  idApoderado int not null,
  grado int not null,
  foreign key (idApoderado) references Apoderado (idApoderado),
  foreign key (idAlumno) references Persona (idPersona)
);

create table Profesor
(
  idProfesor int not null,
  foreign key (idProfesor) references Persona (idPersona)
);

create table Anotacion
(
  idAnotacion int auto_increment primary key,
  texto       varchar(300) null,
  tipo        tinyint      null,
  fecha       date         null,
  idAlumno    int          null,
  idProfesor  int          null,
  foreign key (idAlumno) references Alumno (idAlumno),
  foreign key (idProfesor) references Profesor (idProfesor)
);
