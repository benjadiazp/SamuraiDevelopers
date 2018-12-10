package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.*;

public interface AlumnoService {
	Alumno findAlumnoById(int id) throws DataAccessException;
	Collection<Alumno> findAllAlumnos() throws DataAccessException;
	void saveAlumno(Alumno alumno) throws DataAccessException;
	void deleteAlumno(Alumno alumno) throws DataAccessException;
}
