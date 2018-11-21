package org.springframework.samples.petclinic.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{
	private AsignaturaRepository asignaturaRepository;

	@Autowired
	public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository)
	{
		this.asignaturaRepository = asignaturaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Asignatura findAsignaturaById(int id) throws DataAccessException {
		Asignatura asignatura = null;
		try {
			asignatura = asignaturaRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return asignatura;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Asignatura> findAllAsignatura() throws DataAccessException {
		System.out.println("Probando consulta...");
		return asignaturaRepository.findAll();
	}

	@Override
	@Transactional
	public void saveAsignatura(Asignatura asignatura) throws DataAccessException {
		asignaturaRepository.save(asignatura);
		
	}

	@Override
	@Transactional
	public void deleteAsignatura(Asignatura asignatura) throws DataAccessException {
		asignaturaRepository.delete(asignatura);
		
	}

}
