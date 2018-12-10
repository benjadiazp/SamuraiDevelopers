package org.springframework.samurai.school.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samurai.school.model.Alumno;
import org.springframework.samurai.school.repository.AlumnoRepository;
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
		
		return this.em.createQuery("SELECT alumno FROM Alumno alumno").getResultList();
	}

	@Override
	public void delete(Alumno alumno) throws DataAccessException {
		this.em.remove(this.em.contains(alumno) ? alumno : this.em.merge(alumno));
	}
}
