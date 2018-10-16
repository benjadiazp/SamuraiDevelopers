CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Enviar_Mensaje` (
  `Texto` varchar(300) DEFAULT NULL,
  `Fecha` date DEFAULT NULL,
  `idEmisor` int(11) NOT NULL,
  `idReceptor` int(11) NOT NULL,
  KEY `emisor_idx` (`idEmisor`),
  KEY `receptor_idx` (`idReceptor`),
  CONSTRAINT `emisor` FOREIGN KEY (`idEmisor`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `receptor` FOREIGN KEY (`idReceptor`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Curso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nivel` INT NOT NULL,
  `letra` VARCHAR(1) NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `Profesor` (
  `idProfesor` int(11) NOT NULL,
  KEY `id_idx` (`idProfesor`),
  CONSTRAINT `idP` FOREIGN KEY (`idProfesor`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Apoderado` (
  `idApoderado` int(11) NOT NULL,
  KEY `id_idx` (`idApoderado`),
  CONSTRAINT `idA` FOREIGN KEY (`idApoderado`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Alumno` (
  `idAlumno` int(11) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `idApoderado` int(11) NOT NULL,
  KEY `idAl_idx` (`idAlumno`),
  KEY `apoderado_idx` (`idApoderado`),
  KEY `curso_idx` (`idCurso`),
  CONSTRAINT `apoderado` FOREIGN KEY (`idApoderado`) REFERENCES `Apoderado` (`idApoderado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `curso` FOREIGN KEY (`idCurso`) REFERENCES `Curso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idAl` FOREIGN KEY (`idAlumno`) REFERENCES `Usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Asignatura` (
  `idAsignatura` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `idProfesor` int(11) NOT NULL,
  PRIMARY KEY (`idAsignatura`),
  KEY `idP_idx` (`idProfesor`),
  CONSTRAINT `idProfe` FOREIGN KEY (`idProfesor`) REFERENCES `Profesor` (`idProfesor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `Evaluacion` (
  `idEvaluacion` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  `idAsignatura` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEvaluacion`),
  KEY `idProfesor2_idx` (`idProfesor`),
  KEY `idAsignatura2_idx` (`idAsignatura`),
  CONSTRAINT `idAsignatura2` FOREIGN KEY (`idAsignatura`) REFERENCES `Asignatura` (`idAsignatura`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idProfesor2` FOREIGN KEY (`idProfesor`) REFERENCES `Profesor` (`idProfesor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Clase` (
  `idClase` int(11) NOT NULL AUTO_INCREMENT,
  `horaInicio` int(11) DEFAULT NULL,
  `horaFin` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idClase`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Anotacion` (
  `idAnotacion` int(11) NOT NULL AUTO_INCREMENT,
  `texto` varchar(300) DEFAULT NULL,
  `tipo` tinyint(4) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `idAsignatura` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAnotacion`),
  KEY `idAlumno2_idx` (`idAlumno`),
  KEY `idAsignatura2_idx` (`idAsignatura`),
  CONSTRAINT `idAlumno2` FOREIGN KEY (`idAlumno`) REFERENCES `Alumno` (`idAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idAsignatura3` FOREIGN KEY (`idAsignatura`) REFERENCES `Asignatura` (`idAsignatura`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Asistir_Asignatura` (
  `idAlumno` int(11) NOT NULL,
  `idAsignatura` int(11) NOT NULL,
  KEY `idAlumno3_idx` (`idAlumno`),
  KEY `idAsignatura4_idx` (`idAsignatura`),
  CONSTRAINT `idAlumno4` FOREIGN KEY (`idAlumno`) REFERENCES `Alumno` (`idAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idAsignatura4` FOREIGN KEY (`idAsignatura`) REFERENCES `Asignatura` (`idAsignatura`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Realizar_Evaluacion` (
  `nota` float DEFAULT NULL,
  `presente` tinyint(4) DEFAULT NULL,
  `justificado` tinyint(4) DEFAULT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `idEvaluacion` int(11) DEFAULT NULL,
  KEY `idAlumno3_idx` (`idAlumno`),
  KEY `idEvaluacion2_idx` (`idEvaluacion`),
  CONSTRAINT `idAlumno3` FOREIGN KEY (`idAlumno`) REFERENCES `Alumno` (`idAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idEvaluacion2` FOREIGN KEY (`idEvaluacion`) REFERENCES `Evaluacion` (`idEvaluacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Asistir_Clase` (
  `idClase` int(11) DEFAULT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `presente` tinyint(4) DEFAULT NULL,
  `justificado` tinyint(4) DEFAULT NULL,
  KEY `idAlumno5_idx` (`idAlumno`),
  KEY `idClase2_idx` (`idClase`),
  CONSTRAINT `idAlumno5` FOREIGN KEY (`idAlumno`) REFERENCES `Alumno` (`idAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idClase2` FOREIGN KEY (`idClase`) REFERENCES `Clase` (`idClase`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
