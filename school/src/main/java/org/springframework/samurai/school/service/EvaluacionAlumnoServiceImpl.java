package org.springframework.samurai.school.service;
import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.repository.*;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class EvaluacionAlumnoServiceImpl implements EvaluacionAlumnoService{
	
	private EvaluacionAlumnoRepository evaluacionAlumnoRepository; 
	
	@Autowired
	public EvaluacionAlumnoServiceImpl(EvaluacionAlumnoRepository evaluacionAlumnoRepository) {
		this.evaluacionAlumnoRepository = evaluacionAlumnoRepository; 
	}
	
	@Override
	@Transactional(readOnly = true)
	public EvaluacionAlumno findEvaluacionAlumnoById(int id) throws DataAccessException {
		EvaluacionAlumno evaluacionAlumno = null;
		try {
			evaluacionAlumno = evaluacionAlumnoRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return evaluacionAlumno; 
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<EvaluacionAlumno> findAllEvaluacionAlumno() throws DataAccessException {
		return evaluacionAlumnoRepository.findAll();
	}
	
	@Override
	@Transactional
	public void saveEvaluacionAlumno(EvaluacionAlumno evaluacionAlumno) throws DataAccessException {
		evaluacionAlumnoRepository.save(evaluacionAlumno);
	}
	
	@Override
	@Transactional
	public void deleteEvaluacionAlumno(EvaluacionAlumno evaluacionAlumno) throws DataAccessException {
		evaluacionAlumnoRepository.delete(evaluacionAlumno);
	}
}

