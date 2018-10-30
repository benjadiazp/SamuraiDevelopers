package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface ProfesorService {
	Profesor findProfesorById(int id) throws DataAccessException;
	Collection<Profesor> findProfesores() throws DataAccessException;
	Collection<Profesor> findAllProfesores() throws DataAccessException;
	void saveProfesor(Profesor profesor) throws DataAccessException;
	void deleteProfesor(Profesor profesor) throws DataAccessException;
}
