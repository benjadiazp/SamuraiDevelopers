package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.repository.MensajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeServiceImpl implements MensajeService{
	
	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Mensaje findMensajeById(int id) throws DataAccessException {
		Mensaje mensaje = null;
		try {
			mensaje = mensajeRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return mensaje;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Mensaje> findAllMensajes() throws DataAccessException {
		return mensajeRepository.findAll();
	}

	@Override
	@Transactional
	public void saveMensaje(Mensaje mensaje) throws DataAccessException {
		mensajeRepository.save(mensaje);
	}
	
}
