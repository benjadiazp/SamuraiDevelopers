package org.springframework.samurai.school.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.*;

public interface AnotacionRepository {
	
	Anotacion findById(int id) throws DataAccessException;
    
    void save(Anotacion anotacion) throws DataAccessException;

	Collection<Anotacion> findAll() throws DataAccessException;

	void delete(Anotacion anotacion) throws DataAccessException;
}
