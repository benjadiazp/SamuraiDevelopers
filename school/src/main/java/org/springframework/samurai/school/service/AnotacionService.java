package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Anotacion;

public interface AnotacionService {
	Anotacion findAnotacionById(int id) throws DataAccessException;
	Collection<Anotacion> findAllAnotaciones() throws DataAccessException;
	void saveAnotacion(Anotacion anotacion) throws DataAccessException;
	void deleteAnotacion(Anotacion anotacion) throws DataAccessException;
}
