package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext; 

import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.repository.EvaluacionAlumnoRepository;
import org.springframework.samples.petclinic.model.*;
import org.springframework.dao.DataAccessException; 
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaEvaluacionAlumnoRepositoryImpl implements EvaluacionAlumnoRepository{
	@PersistenceContext
	private EntityManager em; 
	
	@Override
	public EvaluacionAlumno findById(int id) {
		return this.em.find(EvaluacionAlumno.class, id); 
	}
	
	@Override
	public void save(EvaluacionAlumno evaluacionAlumno) {
		if(evaluacionAlumno.getId() == null) {
			this.em.persist(evaluacionAlumno);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<EvaluacionAlumno> findAll() throws DataAccessException{
		return this.em.createQuery("SELECT evaluacionAlumno FROM EvaluacionAlumno evaluacionAlumno").getResultList();
	}
	
	@Override
	public void delete(EvaluacionAlumno evaluacionAlumno) throws DataAccessException{
		String idEvAl = evaluacionAlumno.getId().toString(); 
		this.em.createQuery("DELETE FROM Asignatura asignatura WHERE idAsignatura=" + idEvAl).executeUpdate();
		this.em.createQuery("DELETE FROM Alumno alumno WHERE idAlumno="+ idEvAl).executeUpdate();
		if(em.contains(evaluacionAlumno))
			em.remove(evaluacionAlumno);
	}
}