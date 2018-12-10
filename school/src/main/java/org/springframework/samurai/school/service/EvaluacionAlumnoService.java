package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.EvaluacionAlumno;

public interface EvaluacionAlumnoService {
	EvaluacionAlumno findEvaluacionAlumnoById(int id) throws DataAccessException;
	Collection<EvaluacionAlumno> findAllEvaluacionAlumno() throws DataAccessException;
	void saveEvaluacionAlumno(EvaluacionAlumno evaluacionAlumno) throws DataAccessException;
	void deleteEvaluacionAlumno(EvaluacionAlumno evaluacionAlumno) throws DataAccessException; 
}
