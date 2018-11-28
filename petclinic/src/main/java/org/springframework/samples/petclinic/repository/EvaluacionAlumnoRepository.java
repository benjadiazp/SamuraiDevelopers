package org.springframework.samples.petclinic.repository;

import java.util.Collection; 

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.EvaluacionAlumno; 


public interface EvaluacionAlumnoRepository {
	
	EvaluacionAlumno findById(int id) throws DataAccessException; 
	void save (EvaluacionAlumno evaluacionAlumno) throws DataAccessException;
	Collection<EvaluacionAlumno> findAll() throws DataAccessException;
	void delete(EvaluacionAlumno evaluacionAlumno) throws DataAccessException; 
}

