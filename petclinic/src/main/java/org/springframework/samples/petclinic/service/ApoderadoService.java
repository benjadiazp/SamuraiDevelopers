package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface ApoderadoService {
	Apoderado findApoderadoById(int id) throws DataAccessException;
	Collection<Apoderado> findAllApoderados() throws DataAccessException;
	void saveApoderado(Apoderado apoderado) throws DataAccessException;
	void deleteApoderado(Apoderado apoderado) throws DataAccessException;
	Collection<Apoderado> findApoderadoByApellido(String apellido) throws DataAccessException;
}
