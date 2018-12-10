package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesorServiceImpl implements ProfesorService{
	
	private ProfesorRepository profesorRepository;
	
	@Autowired
	public ProfesorServiceImpl(ProfesorRepository profesorRepository)
	{
		this.profesorRepository = profesorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findProfesorById(int id) throws DataAccessException {
		Profesor profesor = null;
		try {
			profesor = profesorRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return profesor;
	}

	@Override
	@Transactional(readOnly = true)
    @Cacheable(value = "vets")
	public Collection<Profesor> findProfesores() throws DataAccessException {
		return profesorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Profesor> findAllProfesores() throws DataAccessException {
		return profesorRepository.findAll();
	}

	@Override
	@Transactional
	public void saveProfesor(Profesor profesor) throws DataAccessException {
		profesorRepository.save(profesor);
		
	}

	@Override
	@Transactional
	public void deleteProfesor(Profesor profesor) throws DataAccessException {
		profesorRepository.delete(profesor);
		
	}

}
