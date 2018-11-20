package org.springframework.samples.petclinic.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl implements CursoService{
	
	private CursoRepository cursoRepository;

	@Autowired
	public CursoServiceImpl(CursoRepository cursoRepository)
	{
		this.cursoRepository = cursoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoById(int id) throws DataAccessException {
		Curso curso = null;
		try {
			curso = cursoRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return curso;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Curso> findAllCurso() throws DataAccessException {
		System.out.println("Probando consulta...");
		return cursoRepository.findAll();
	}

	@Override
	@Transactional
	public void saveCurso(Curso curso) throws DataAccessException {
		cursoRepository.save(curso);
		
	}

	@Override
	@Transactional
	public void deleteCurso(Curso curso) throws DataAccessException {
		cursoRepository.delete(curso);
		
	}
}
