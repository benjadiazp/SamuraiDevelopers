package org.springframework.samurai.school.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Alumno;

public interface AlumnoRepository {

    
    Alumno findById(int id) throws DataAccessException;
    
    void save(Alumno alumno) throws DataAccessException;

	Collection<Alumno> findAll() throws DataAccessException;

	void delete(Alumno alumno) throws DataAccessException;
}
