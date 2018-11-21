INSERT INTO users(username,password,enabled) VALUES ('admin','admin', true);

INSERT INTO roles VALUES (1,'admin', 'ROLE_OWNER_ADMIN');
INSERT INTO roles VALUES (2,'admin', 'ROLE_VET_ADMIN');
INSERT INTO roles VALUES (3,'admin', 'ROLE_ADMIN');

INSERT INTO apoderado VALUES (1, 'Apoderado1', 'Apellido1');
INSERT INTO apoderado VALUES (2, 'Apoderado2', 'Apellido2');
INSERT INTO apoderado VALUES (3, 'Apoderado3', 'Apellido3');

INSERT INTO alumno VALUES (1, 'Alumno1', 'Apellido1', 1, 1);
INSERT INTO alumno VALUES (2, 'Alumno2', 'Apellido2', 1, 2);
INSERT INTO alumno VALUES (3, 'Alumno3', 'Apellido3', 2, 3);

INSERT INTO profesor VALUES (1, 'Profesor1', 'Apellido1');
INSERT INTO profesor VALUES (2, 'Profesor2', 'Apellido2');

INSERT INTO anotacion VALUES (1,'Me faltó el respeto.', 0, '2013-01-04', 1, 1);
INSERT INTO anotacion VALUES (2,'Rompió una mesa.', 0, '2013-01-04', 1, 1);

INSERT INTO curso VALUES (1, 4, 'medio', 'A');
INSERT INTO curso VALUES (2, 4, 'medio', 'B');

INSERT INTO asignatura VALUES (1, 'Matemáticas', 1);
INSERT INTO asignatura VALUES (2, 'Lenguaje', 2);