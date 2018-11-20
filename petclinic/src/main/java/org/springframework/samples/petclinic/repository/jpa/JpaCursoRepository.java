package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.samples.petclinic.repository.CursoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaCursoRepository implements CursoRepository{
	@PersistenceContext
    private EntityManager em;

	@Override
	public Curso findById(int id) throws DataAccessException {
		return this.em.find(Curso.class, id);
	}

	@Override
	public void save(Curso curso) throws DataAccessException {
		if (curso.getId() == null) {
            this.em.persist(curso);
        } else {
            this.em.merge(curso);
        }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Curso> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT anotacion FROM Anotacion anotacion").getResultList();
	}

	@Override
	public void delete(Curso curso) throws DataAccessException {
		String idCu = curso.getId().toString();
		this.em.createQuery("DELETE FROM Anotacion anotacion WHERE id=" + idCu).executeUpdate();
		if (em.contains(curso)) {
			em.remove(curso);
		}
	}
}
