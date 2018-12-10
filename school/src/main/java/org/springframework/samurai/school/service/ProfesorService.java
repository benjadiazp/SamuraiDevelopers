package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.*;

public interface ProfesorService {
	Profesor findProfesorById(int id) throws DataAccessException;
	Collection<Profesor> findProfesores() throws DataAccessException;
	Collection<Profesor> findAllProfesores() throws DataAccessException;
	void saveProfesor(Profesor profesor) throws DataAccessException;
	void deleteProfesor(Profesor profesor) throws DataAccessException;
}
