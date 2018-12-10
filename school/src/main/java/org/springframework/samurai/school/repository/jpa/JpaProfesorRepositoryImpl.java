package org.springframework.samurai.school.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.repository.*;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaProfesorRepositoryImpl implements ProfesorRepository {
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Profesor findById(int id) throws DataAccessException {
		return this.em.find(Profesor.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Profesor> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT profesor FROM Profesor profesor").getResultList();
	}

	@Override
	public void save(Profesor profesor) throws DataAccessException {
        if (profesor.getId() == null) {
            this.em.persist(profesor);
        } else {
            this.em.merge(profesor);
        }
	}

	@Override
	public void delete(Profesor profesor) throws DataAccessException {
		this.em.remove(this.em.contains(profesor) ? profesor : this.em.merge(profesor));
	}
}
