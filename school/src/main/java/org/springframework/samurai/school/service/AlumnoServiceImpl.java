package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	private AlumnoRepository alumnoRepository;

	@Autowired
	public AlumnoServiceImpl(AlumnoRepository alumnoRepository)
	{
		this.alumnoRepository = alumnoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findAlumnoById(int id) throws DataAccessException {
		Alumno alumno = null;
		try {
			alumno = alumnoRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return alumno;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Alumno> findAllAlumnos() throws DataAccessException {
		return alumnoRepository.findAll();
	}

	@Override
	@Transactional
	public void saveAlumno(Alumno alumno) throws DataAccessException {
		alumnoRepository.save(alumno);
		
	}

	@Override
	@Transactional
	public void deleteAlumno(Alumno alumno) throws DataAccessException {
		alumnoRepository.delete(alumno);
		
	}
}
