package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface AlumnoService {
	Alumno findAlumnoById(int id) throws DataAccessException;
	Collection<Alumno> findAllAlumnos() throws DataAccessException;
	void saveAlumno(Alumno alumno) throws DataAccessException;
	void deleteAlumno(Alumno alumno) throws DataAccessException;
}
