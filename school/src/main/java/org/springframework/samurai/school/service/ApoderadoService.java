package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.*;

public interface ApoderadoService {
	Apoderado findApoderadoById(int id) throws DataAccessException;
	Collection<Apoderado> findAllApoderados() throws DataAccessException;
	void saveApoderado(Apoderado apoderado) throws DataAccessException;
	void deleteApoderado(Apoderado apoderado) throws DataAccessException;
	Collection<Apoderado> findApoderadoByApellido(String apellido) throws DataAccessException;
}
