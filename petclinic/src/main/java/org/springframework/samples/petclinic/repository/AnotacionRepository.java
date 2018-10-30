package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface AnotacionRepository {
	
	Anotacion findById(int id) throws DataAccessException;
    
    void save(Anotacion anotacion) throws DataAccessException;

	Collection<Anotacion> findAll() throws DataAccessException;

	void delete(Anotacion anotacion) throws DataAccessException;
}
