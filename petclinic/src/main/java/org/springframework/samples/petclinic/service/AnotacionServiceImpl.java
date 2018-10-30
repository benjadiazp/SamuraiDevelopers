package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Anotacion;
import org.springframework.samples.petclinic.repository.AnotacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnotacionServiceImpl implements AnotacionService {
	@Autowired
	private AnotacionRepository anotacionRepository;

	@Override
	@Transactional(readOnly = true)
	public Anotacion findAnotacionById(int id) throws DataAccessException {
		Anotacion anotacion = null;
		try {
			anotacion = anotacionRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return anotacion;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Anotacion> findAllAnotaciones() throws DataAccessException {
		return anotacionRepository.findAll();
	}

	@Override
	@Transactional
	public void saveAnotacion(Anotacion anotacion) throws DataAccessException {
		anotacionRepository.save(anotacion);
		
	}

	@Override
	@Transactional
	public void deleteAnotacion(Anotacion anotacion) throws DataAccessException {
		anotacionRepository.delete(anotacion);
		
	}
	
	
}
