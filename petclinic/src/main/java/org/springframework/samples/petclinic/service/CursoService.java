package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Curso;

public interface CursoService {
	Curso findCursoById(int id) throws DataAccessException;
	Collection<Curso> findAllCurso() throws DataAccessException;
	void saveCurso(Curso curso) throws DataAccessException;
	void deleteCurso(Curso cursos) throws DataAccessException;
}
