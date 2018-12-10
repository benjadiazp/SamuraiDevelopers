package org.springframework.samurai.school.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Mensaje;
import org.springframework.samurai.school.repository.MensajeRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaMensajeRepositoryImpl implements MensajeRepository {
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public Mensaje findById(int id) throws DataAccessException {
		return this.em.find(Mensaje.class, id);
	}

	@Override
	public void save(Mensaje mensaje) throws DataAccessException {
		if (mensaje.getId() == null) {
            this.em.persist(mensaje);
        } else {
            this.em.merge(mensaje);
        }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Mensaje> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT mensaje FROM Mensaje mensaje").getResultList();
	}

}
