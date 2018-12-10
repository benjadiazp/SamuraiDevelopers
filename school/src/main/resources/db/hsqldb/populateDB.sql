INSERT INTO users(username,password,enabled) VALUES ('admin','admin', true);

INSERT INTO roles VALUES (1,'admin', 'ROLE_OWNER_ADMIN');
INSERT INTO roles VALUES (2,'admin', 'ROLE_VET_ADMIN');
INSERT INTO roles VALUES (3,'admin', 'ROLE_ADMIN');

INSERT INTO curso VALUES (1, 4, 'medio', 'A');
INSERT INTO curso VALUES (2, 4, 'medio', 'B');
INSERT INTO curso VALUES (3, 3, 'basico', 'A');
INSERT INTO curso VALUES (4, 6, 'basico', 'A');
INSERT INTO curso VALUES (5, 1, 'medio', 'C');
INSERT INTO curso VALUES (6, 2, 'basico', 'D');
INSERT INTO curso VALUES (7, 3, 'medio', 'B');
INSERT INTO curso VALUES (8, 1, 'medio', 'D');
INSERT INTO curso VALUES (9, 7, 'basico', 'C');
INSERT INTO curso VALUES (10, 5, 'basico', 'D');

INSERT INTO apoderado VALUES (1, 'Adriana', 'Gomez');
INSERT INTO apoderado VALUES (2, 'Bastian', 'Romero');
INSERT INTO apoderado VALUES (3, 'Camilo', 'Torres');
INSERT INTO apoderado VALUES (4, 'Graciela', 'Parra');
INSERT INTO apoderado VALUES (5, 'Ingrid', 'Lopez');
INSERT INTO apoderado VALUES (6, 'Luis', 'Venegas');
INSERT INTO apoderado VALUES (7, 'Luz', 'Baes');
INSERT INTO apoderado VALUES (8, 'Nestor', 'Garrido');
INSERT INTO apoderado VALUES (9, 'Paola', 'Cortes');
INSERT INTO apoderado VALUES (10, 'Sebastian', 'Trujillo');

INSERT INTO alumno VALUES (1, 'Rodrigo', 'Gomez', 1, 1);
INSERT INTO alumno VALUES (2, 'Eduardo', 'Velazco', 2, 2);
INSERT INTO alumno VALUES (3, 'Diego', 'Ruelas', 3, 3);
INSERT INTO alumno VALUES (4, 'Leonardo', 'Mayor', 4, 4);
INSERT INTO alumno VALUES (5, 'David', 'Sotelo', 5, 5);
INSERT INTO alumno VALUES (6, 'Elizabeth', 'Hernandes', 6, 6);
INSERT INTO alumno VALUES (7, 'Margarita', 'Garcia', 7, 7);
INSERT INTO alumno VALUES (8, 'Yolanda', 'Gonzales', 8, 8);
INSERT INTO alumno VALUES (9, 'Adriana', 'Martinez', 9, 9);
INSERT INTO alumno VALUES (10, 'Araceli', 'Lopez', 10, 10);


INSERT INTO profesor VALUES (1, 'Alexis', 'Vega');
INSERT INTO profesor VALUES (2, 'Sebastian', 'Davila');
INSERT INTO profesor VALUES (3, 'Tadeo', 'Cervantes');
INSERT INTO profesor VALUES (4, 'Axel', 'Salazar');
INSERT INTO profesor VALUES (5, 'Daniel', 'Aguirre');
INSERT INTO profesor VALUES (6, 'Natalia', 'Perez');
INSERT INTO profesor VALUES (7, 'Veronica', 'Rodriguez');
INSERT INTO profesor VALUES (8, 'Alejandra', 'Sanchez');
INSERT INTO profesor VALUES (9, 'Mariana', 'Robles');
INSERT INTO profesor VALUES (10, 'Gabriela', 'Flores');

INSERT INTO anotacion VALUES (1,'Me faltó el respeto.', 0, '2013-01-04', 1, 1);
INSERT INTO anotacion VALUES (2,'Rompió una mesa.', 0, '2013-01-04', 2, 2);
INSERT INTO anotacion VALUES (3,'Ayuda a sus compañeros a reforzar contenidos.', 1, '2015-03-11', 3, 3);
INSERT INTO anotacion VALUES (4,'Se ofrece volunatrio a resolver problemas en la pizarra.', 1, '2014-06-01', 4, 4);
INSERT INTO anotacion VALUES (5,'Tercera clase que no trae sus materiales.', 0, '2016-09-15', 5, 5);
INSERT INTO anotacion VALUES (6,'Pelea con un compañero en clases.', 0, '2012-10-22', 6, 6);
INSERT INTO anotacion VALUES (7,'Ayuda a trasladar material escolar desde la biblioteca.', 1, '2013-05-17', 7, 7);
INSERT INTO anotacion VALUES (8,'No trae cuadernos y hace desorden en clases.', 0, '2016-04-28', 8, 8);

INSERT INTO asignatura VALUES (1, 'Matematicas', 1);
INSERT INTO asignatura VALUES (2, 'Lenguaje', 2);
INSERT INTO asignatura VALUES (3, 'Historia', 3);
INSERT INTO asignatura VALUES (4, 'Educacion Fisica', 4);
INSERT INTO asignatura VALUES (5, 'Ciencias Naturales', 5);
INSERT INTO asignatura VALUES (6, 'Biologia', 6);
INSERT INTO asignatura VALUES (7, 'Fisica', 7);
INSERT INTO asignatura VALUES (8, 'Quimica', 8);

INSERT INTO mensaje VALUES (1,'Buenas tardes, se le comunica que su hijo ha sido suspendido.', '2013-01-04', 1, 1);
INSERT INTO mensaje VALUES (2,'Buenos dias, se le comunica que su hijo no tendra clases de mi asignatura durante esta semana.', '2014-04-05', 2, 1);
INSERT INTO mensaje VALUES (3,'Buenas tardes, se le comunica que su hijo debe venir disfrazado para el dia de mañana.', '2017-08-22', 2, 1);
INSERT INTO mensaje VALUES (4,'Buenas tardes, se le comunica que su hijo no entrara mas a mis clases si no trae sus tareas terminadas.', '2015-04-17', 2, 3);
INSERT INTO mensaje VALUES (5,'Buenas tardes, se le comunica que su hijo esta eximido de la asignatura.', '2012-08-11', 2, 1);
INSERT INTO mensaje VALUES (6,'Buenos dias, se le comunica que su hijo ha sido enviado a enfermeria por problemas de salud.', '2015-05-21', 2, 2);
INSERT INTO mensaje VALUES (7,'Buenos dias, se le comunica que su hijo podra rendir las evaluaciones de forma oral, debido a su fractura de brazo.', '2012-11-06', 2, 2);

INSERT INTO evaluacion VALUES(1,'2013-04-12', 1);
INSERT INTO evaluacion VALUES(2,'2014-05-11', 2);
INSERT INTO evaluacion VALUES(3,'2015-06-23', 3);
INSERT INTO evaluacion VALUES(4,'2013-05-30', 4);
INSERT INTO evaluacion VALUES(5,'2015-04-19', 5);
INSERT INTO evaluacion VALUES(6,'2016-07-01', 6);
INSERT INTO evaluacion VALUES(7,'2017-08-21', 7);
INSERT INTO evaluacion VALUES(8,'2013-09-11', 8);
INSERT INTO evaluacion VALUES(9,'2014-09-15', 1);
INSERT INTO evaluacion VALUES(10,'2016-10-27', 2);

INSERT INTO evaluacion_alumno VALUES(1,22,1,1);
INSERT INTO evaluacion_alumno VALUES(2,67,2,2);
INSERT INTO evaluacion_alumno VALUES(3,60,3,3);
INSERT INTO evaluacion_alumno VALUES(4,34,4,4);
INSERT INTO evaluacion_alumno VALUES(5,46,5,5);
INSERT INTO evaluacion_alumno VALUES(6,70,6,6);
INSERT INTO evaluacion_alumno VALUES(7,66,7,7);
INSERT INTO evaluacion_alumno VALUES(8,45,8,8);
INSERT INTO evaluacion_alumno VALUES(9,33,9,9);
INSERT INTO evaluacion_alumno VALUES(10,60,10,10);