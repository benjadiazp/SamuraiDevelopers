package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.AnotacionRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaAnotacionRepositoryImpl implements AnotacionRepository {
	@PersistenceContext
    private EntityManager em;

	@Override
	public Anotacion findById(int id) throws DataAccessException {
		return this.em.find(Anotacion.class, id);
	}

	@Override
	public void save(Anotacion anotacion) throws DataAccessException {
		if (anotacion.getId() == null) {
            this.em.persist(anotacion);
        } else {
            this.em.merge(anotacion);
        }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Anotacion> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT anotacion FROM Anotacion anotacion").getResultList();
	}

	@Override
	public void delete(Anotacion anotacion) throws DataAccessException {
		this.em.remove(this.em.contains(anotacion) ? anotacion : this.em.merge(anotacion));
		
	}
	
	
}
