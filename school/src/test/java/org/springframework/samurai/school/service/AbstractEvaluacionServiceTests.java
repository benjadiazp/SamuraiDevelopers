package org.springframework.samurai.school.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samurai.school.model.Evaluacion;
import org.springframework.samurai.school.service.AsignaturaService;
import org.springframework.samurai.school.service.EvaluacionService;
import org.springframework.transaction.annotation.Transactional;

public  abstract class AbstractEvaluacionServiceTests {

	@Autowired
	protected EvaluacionService evaluacionService;
	

	protected AsignaturaService asignaturaService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void shouldFindEvaluacionById() {
    	Evaluacion evaluacion = this.evaluacionService.findEvaluacionById(1);
    	assertThat(evaluacion.getId()).isEqualTo(1);
    }
	
	
	@Test
    @Transactional
    public void shouldSaveEvaluacion() {
    	Collection<Evaluacion> evaluacion = this.evaluacionService.findAllEvaluacion();
        int tamano = evaluacion.size();

        Evaluacion eva = new Evaluacion();
        eva.setFecha(new Date());
        eva.setAsignatura(this.asignaturaService.findAsignaturaById(1));
        
        this.evaluacionService.saveEvaluacion(eva);
        
        assertThat(eva.getId().longValue()).isNotEqualTo(0);
        assertThat(evaluacion.size()).isEqualTo(tamano + 1);
    }
	
	@Test
    @Transactional
    public void shouldDeleteEvaluacion() {
    	Evaluacion evaluacion = this.evaluacionService.findEvaluacionById(1);
    	this.evaluacionService.deleteEvaluacion(evaluacion);
    	try {
    		evaluacion = this.evaluacionService.findEvaluacionById(1);
    	} catch (Exception e) {
    		evaluacion = null; 
    	}
    	assertThat(evaluacion).isNull(); 
    }
	
}
