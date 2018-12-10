package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Evaluacion;

public interface EvaluacionService {
	Evaluacion findEvaluacionById(int id) throws DataAccessException;
	Collection<Evaluacion> findAllEvaluacion() throws DataAccessException;
	void saveEvaluacion(Evaluacion evaluacion) throws DataAccessException;
	void deleteEvaluacion(Evaluacion evaluacion) throws DataAccessException;
}
