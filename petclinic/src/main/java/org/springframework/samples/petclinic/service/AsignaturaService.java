package org.springframework.samples.petclinic.service;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface AsignaturaService {

	Asignatura findAsignaturaById(int id) throws DataAccessException;
	Collection<Asignatura> findAllAsignatura() throws DataAccessException;
	void saveAsignatura(Asignatura asignatura) throws DataAccessException;
	void deleteAsignatura(Asignatura asignatura) throws DataAccessException;
}
