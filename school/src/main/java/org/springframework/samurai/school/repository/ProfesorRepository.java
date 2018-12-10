package org.springframework.samurai.school.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.*;

public interface ProfesorRepository {
	
	Collection<Profesor> findAll() throws DataAccessException;
    
	Profesor findById(int id) throws DataAccessException;

	void save(Profesor profesor) throws DataAccessException;
	
	void delete(Profesor profesor) throws DataAccessException;
}
