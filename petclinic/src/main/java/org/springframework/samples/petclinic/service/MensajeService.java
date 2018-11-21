package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mensaje;

public interface MensajeService {
	Mensaje findMensajeById(int id) throws DataAccessException;
	Collection<Mensaje> findAllMensajes() throws DataAccessException;
	void saveMensaje(Mensaje mensaje) throws DataAccessException;
}
