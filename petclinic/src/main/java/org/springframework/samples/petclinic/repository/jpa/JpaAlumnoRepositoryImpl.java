package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.*;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaAlumnoRepositoryImpl implements AlumnoRepository{
	@PersistenceContext
    private EntityManager em;

    @Override
    public Alumno findById(int id) {
        return this.em.find(Alumno.class, id);
    }

    @Override
    public void save(Alumno alumno) {
        if (alumno.getId() == null) {
            this.em.persist(alumno);
        } else {
            this.em.merge(alumno);
        }
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Alumno> findAll() throws DataAccessException {
		return this.em.createQuery("SELECT pet FROM Pet pet").getResultList();
	}

	@Override
	public void delete(Alumno alumno) throws DataAccessException {
		//this.em.remove(this.em.contains(pet) ? pet : this.em.merge(pet));
		String idAl = alumno.getId().toString();
		this.em.createQuery("DELETE FROM Anotacion anotacion WHERE idAlumno=" + idAl).executeUpdate();
		this.em.createQuery("DELETE FROM Alumno alumno WHERE id=" + idAl).executeUpdate();
		if (em.contains(alumno)) {
			em.remove(alumno);
		}
	}
}
