package org.springframework.samurai.school.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.repository.AsignaturaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaAsignaturaRepositoryImpl implements AsignaturaRepository{
	@PersistenceContext
    private EntityManager em;

	@Override
	public Asignatura findById(int id) throws DataAccessException {
		return this.em.find(Asignatura.class, id);
	}

	@Override
	public void save(Asignatura asignatura) throws DataAccessException {
		if (asignatura.getId() == null) {
            this.em.persist(asignatura);
        } else {
            this.em.merge(asignatura);
        }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Asignatura> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT asignatura FROM Asignatura asignatura").getResultList();
	}

	@Override
	public void delete(Asignatura asignatura) throws DataAccessException {
		this.em.remove(this.em.contains(asignatura) ? asignatura : this.em.merge(asignatura));
	}
}
