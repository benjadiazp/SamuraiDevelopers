package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface ProfesorRepository {
	
	Collection<Profesor> findAll() throws DataAccessException;
    
	Profesor findById(int id) throws DataAccessException;

	void save(Profesor profesor) throws DataAccessException;
	
	void delete(Profesor profesor) throws DataAccessException;
}
