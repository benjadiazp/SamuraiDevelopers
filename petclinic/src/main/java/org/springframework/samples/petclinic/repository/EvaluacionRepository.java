package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Curso;
import org.springframework.samples.petclinic.model.Evaluacion;

public interface EvaluacionRepository {
	Evaluacion findById(int id) throws DataAccessException;
	
	void save (Evaluacion evaluacion) throws DataAccessException;
	
	Collection<Evaluacion> findAll() throws DataAccessException;
	
	void delete(Evaluacion evaluacion) throws DataAccessException;


}
