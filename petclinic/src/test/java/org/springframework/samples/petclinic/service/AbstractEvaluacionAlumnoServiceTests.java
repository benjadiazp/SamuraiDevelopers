package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractEvaluacionAlumnoServiceTests {

	@Autowired
    protected EvaluacionAlumnoService evaluacionAlumnoService;
	
	@Autowired
    protected AlumnoService alumnoService;
	
	@Autowired
    protected EvaluacionService evaluacionService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void shouldFindEvaluacionAlumnoById() {
    	EvaluacionAlumno evaluacionAlumno = this.evaluacionAlumnoService.findEvaluacionAlumnoById(1);
    	assertThat(evaluacionAlumno.getId()).isEqualTo(1);
    }
	
	@Test
    public void shouldFindAllEvaluacionAlumno() {
    	Collection<EvaluacionAlumno> evaluacionAlumno = this.evaluacionAlumnoService.findAllEvaluacionAlumno();
       // EvaluacionAlumno evAlumno1 = EntityUtils.getById(evAlumno1, EvaluacionAlumno.class, 1);
       // assertThat(evAlumno1.getNota()).isEqualTo(22);
        
       // EvaluacionAlumno evAlumno2 = EntityUtils.getById(evAlumno2, EvaluacionAlumno.class, 2);
       // assertThat(evAlumno2.getNota()).isEqualTo(67);
    }

    @Test
    @Transactional
    public void shouldSaveEvaluacionAlumno() {
    	Collection<EvaluacionAlumno> evaluacionAlumnos = this.evaluacionAlumnoService.findAllEvaluacionAlumno();
        int tamano = evaluacionAlumnos.size();

        EvaluacionAlumno evAlumno = new EvaluacionAlumno();
        evAlumno.setNota(20);
        evAlumno.setAlumno(this.alumnoService.findAlumnoById(1));
        evAlumno.setEvaluacion(this.evaluacionService.findEvaluacionById(1));
        this.evaluacionAlumnoService.saveEvaluacionAlumno(evAlumno);
        assertThat(evAlumno.getId().intValue()).isNotEqualTo(0);

       // evAlumno = this.evaluacionAlumnoService.findAllEvaluacionAlumno();
        assertThat(evaluacionAlumnos.size()).isEqualTo(tamano + 1);
    }

    @Test
    @Transactional
    public void shouldDeleteEvaluacionAlumno() {
    	EvaluacionAlumno evaluacionAlumno = this.evaluacionAlumnoService.findEvaluacionAlumnoById(1);
    	this.evaluacionAlumnoService.deleteEvaluacionAlumno(evaluacionAlumno);
    	try {
    		evaluacionAlumno = this.evaluacionAlumnoService.findEvaluacionAlumnoById(1);
    	} catch (Exception e) {
    		evaluacionAlumno = null; 
    	}
    	assertThat(evaluacionAlumno).isNull(); 
    }
}