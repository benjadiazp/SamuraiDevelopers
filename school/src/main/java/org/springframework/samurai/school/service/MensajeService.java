package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Mensaje;

public interface MensajeService {
	Mensaje findMensajeById(int id) throws DataAccessException;
	Collection<Mensaje> findAllMensajes() throws DataAccessException;
	void saveMensaje(Mensaje mensaje) throws DataAccessException;
}
