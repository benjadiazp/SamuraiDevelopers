package org.springframework.samurai.school.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Mensaje;

public interface MensajeRepository {
	Mensaje findById(int id) throws DataAccessException;
	
	void save(Mensaje mensaje) throws DataAccessException;

	Collection<Mensaje> findAll() throws DataAccessException;
}
