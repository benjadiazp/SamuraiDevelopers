package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApoderadoServiceImpl implements ApoderadoService {
	private ApoderadoRepository apoderadoRepository;
	
	@Autowired
	public ApoderadoServiceImpl(ApoderadoRepository apoderadoRepository)
	{
		this.apoderadoRepository = apoderadoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Apoderado findApoderadoById(int id) throws DataAccessException {
		Apoderado apoderado = null;
		try {
			apoderado = apoderadoRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return apoderado;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Apoderado> findAllApoderados() throws DataAccessException {
		return apoderadoRepository.findAll();
	}

	@Override
	@Transactional
	public void saveApoderado(Apoderado apoderado) throws DataAccessException {
		apoderadoRepository.save(apoderado);
		
	}

	@Override
	@Transactional
	public void deleteApoderado(Apoderado apoderado) throws DataAccessException {
		apoderadoRepository.delete(apoderado);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Apoderado> findApoderadoByApellido(String apellido) throws DataAccessException {
		return apoderadoRepository.findByApellido(apellido);
	}
	
	
}
