package org.springframework.samples.petclinic.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class EvaluacionServiceImpl implements EvaluacionService {
	private EvaluacionRepository evaluacionRepository;

	@Autowired
	public EvaluacionServiceImpl(EvaluacionRepository evaluacionRepository)
	{
		this.evaluacionRepository = evaluacionRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Evaluacion findEvaluacionById(int id) throws DataAccessException {
		Evaluacion evaluacion = null;
		try {
			evaluacion = evaluacionRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return evaluacion;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Evaluacion> findAllEvaluacion() throws DataAccessException {
		System.out.println("Probando consulta...");
		return evaluacionRepository.findAll();
	}

	@Override
	@Transactional
	public void saveEvaluacion(Evaluacion evaluacion) throws DataAccessException {
		evaluacionRepository.save(evaluacion);
		
	}

	@Override
	@Transactional
	public void deleteEvaluacion(Evaluacion evaluacion) throws DataAccessException {
		evaluacionRepository.delete(evaluacion);
		
	}
}
