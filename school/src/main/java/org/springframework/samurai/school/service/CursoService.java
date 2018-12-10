package org.springframework.samurai.school.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Curso;

public interface CursoService {
	Curso findCursoById(int id) throws DataAccessException;
	Collection<Curso> findAllCurso() throws DataAccessException;
	void saveCurso(Curso curso) throws DataAccessException;
	void deleteCurso(Curso cursos) throws DataAccessException;
}
