package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;

public interface AlumnoRepository {

    
    Alumno findById(int id) throws DataAccessException;
    
    void save(Alumno alumno) throws DataAccessException;

	Collection<Alumno> findAll() throws DataAccessException;

	void delete(Alumno alumno) throws DataAccessException;
}
