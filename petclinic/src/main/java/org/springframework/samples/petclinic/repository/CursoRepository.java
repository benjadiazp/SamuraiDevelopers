package org.springframework.samples.petclinic.repository;
import java.util.Collection;


import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Curso;
public interface CursoRepository {
	Curso findById(int id) throws DataAccessException;
    
    void save(Curso curso) throws DataAccessException;

	Collection<Curso> findAll() throws DataAccessException;

	void delete(Curso curso) throws DataAccessException;
}
