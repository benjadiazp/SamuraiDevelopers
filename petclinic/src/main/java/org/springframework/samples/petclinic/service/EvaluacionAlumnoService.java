package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.EvaluacionAlumno;

public interface EvaluacionAlumnoService {
	EvaluacionAlumno findEvaluacionAlumnoById(int id) throws DataAccessException;
	Collection<EvaluacionAlumno> findAllEvaluacionAlumno() throws DataAccessException;
	void saveEvaluacionAlumno(EvaluacionAlumno evaluacionAlumno) throws DataAccessException;
	void deleteEvaluacionAlumno(EvaluacionAlumno evaluacionAlumno) throws DataAccessException; 
}
