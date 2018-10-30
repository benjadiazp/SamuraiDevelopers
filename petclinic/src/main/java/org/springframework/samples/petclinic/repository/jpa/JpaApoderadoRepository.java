package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.*;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaApoderadoRepository implements ApoderadoRepository{
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
    public Collection<Apoderado> findByApellido(String apellido) {
        // using 'join fetch' because a single query should load both owners and pets
        // using 'left join fetch' because it might happen that an owner does not have pets yet
        Query query = this.em.createQuery("SELECT DISTINCT apoderado FROM Apoderado apoderado left join fetch apoderado.alumnos WHERE apoderado.apellido LIKE :apellido");
        query.setParameter("apellido", apellido + "%");
        return query.getResultList();
    }

    @Override
    public Apoderado findById(int id) {
        // using 'join fetch' because a single query should load both owners and pets
        // using 'left join fetch' because it might happen that an owner does not have pets yet
        Query query = this.em.createQuery("SELECT apoderado FROM Apoderado apoderado left join fetch apoderado.alumnos WHERE apoderado.id =:id");
        query.setParameter("id", id);
        return (Apoderado) query.getSingleResult();
    }


    @Override
    public void save(Apoderado apoderado) {
        if (apoderado.getId() == null) {
            this.em.persist(apoderado);
        } else {
            this.em.merge(apoderado);
        }

    }
    
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Apoderado> findAll() throws DataAccessException {
		Query query = this.em.createQuery("SELECT apoderado FROM Apoderado apoderado");
        return query.getResultList();
	}

	@Override
	public void delete(Apoderado apoderado) throws DataAccessException {
		this.em.remove(this.em.contains(apoderado) ? apoderado : this.em.merge(apoderado));
	}
}
