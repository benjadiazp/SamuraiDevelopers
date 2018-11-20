package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Anotacion;
import org.springframework.samples.petclinic.model.Asignatura;

public interface AsignaturaRepository {
	
	Asignatura findById(int id) throws DataAccessException;
    
    void save(Asignatura asignatura) throws DataAccessException;

	Collection<Asignatura> findAll() throws DataAccessException;

	void delete(Asignatura asignatura) throws DataAccessException;
}
