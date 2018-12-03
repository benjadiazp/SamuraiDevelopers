package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Curso;
import org.springframework.samples.petclinic.model.Evaluacion;
import org.springframework.samples.petclinic.repository.EvaluacionRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public class JpaEvaluacionRepositorylmpl implements EvaluacionRepository {
	
	
	@PersistenceContext
    private EntityManager ev;

	@Override
	public Evaluacion findById(int id) throws DataAccessException {
		return this.ev.find(Evaluacion.class, id);
	}

	@Override
	public void save(Evaluacion evaluacion) throws DataAccessException {
		if(evaluacion.getId()==null) {
			this.ev.persist(evaluacion);
		}else {
			this.ev.merge(evaluacion);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Evaluacion> findAll() throws DataAccessException {
		return this.ev.createQuery("Select evaluacion from Evaluacion evaluacion").getResultList();
	}
	@Override
	public void delete(Evaluacion evaluacion) throws DataAccessException {
		this.ev.remove(this.ev.contains(evaluacion) ? evaluacion : this.ev.merge(evaluacion));
	}
}
