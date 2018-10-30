package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface ApoderadoRepository {

	Collection<Apoderado> findByApellido(String apellido) throws DataAccessException;

    Apoderado findById(int id) throws DataAccessException;

    void save(Apoderado apoderado) throws DataAccessException;

	Collection<Apoderado> findAll() throws DataAccessException;

	void delete(Apoderado apoderado) throws DataAccessException;
}
