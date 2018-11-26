package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Evaluacion;

public interface EvaluacionService {
	Evaluacion findEvaluacionById(int id) throws DataAccessException;
	Collection<Evaluacion> findAllEvaluacion() throws DataAccessException;
	void saveEvaluacion(Evaluacion evaluacion) throws DataAccessException;
	void deleteEvaluacion(Evaluacion evaluacion) throws DataAccessException;
}
