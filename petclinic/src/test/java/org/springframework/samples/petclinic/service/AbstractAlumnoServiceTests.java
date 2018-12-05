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

public abstract class AbstractAlumnoServiceTests {
	@Autowired
    protected AlumnoService alumnoService;
	
	@Autowired
    protected ApoderadoService apoderadoService;
	
	@Autowired
    protected CursoService cursoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldFindAlumnoById() {
    	Alumno alumno = this.alumnoService.findAlumnoById(1);
    	assertThat(alumno.getId()).isEqualTo(1);
    }

    @Test
    public void shouldFindAllAlumnos() {
    	Collection<Alumno> alumnos = this.alumnoService.findAllAlumnos();
        Alumno alumno1 = EntityUtils.getById(alumnos, Alumno.class, 1);
        assertThat(alumno1.getNombre()).isEqualTo("Rodrigo");
        Alumno alumno3 = EntityUtils.getById(alumnos, Alumno.class, 3);
        assertThat(alumno3.getNombre()).isEqualTo("Diego");
    }

    @Test
    @Transactional
    public void shouldSaveAlumno() {
    	Collection<Alumno> alumnos = this.alumnoService.findAllAlumnos();
        int tamano = alumnos.size();

        Alumno alumno = new Alumno();
        alumno.setNombre("AlumnoTest");
        alumno.setApellido("ApellidoTest");
        alumno.setApoderado(this.apoderadoService.findApoderadoById(1));
        alumno.setCurso(this.cursoService.findCursoById(1));
        this.alumnoService.saveAlumno(alumno);
        assertThat(alumno.getId().longValue()).isNotEqualTo(0);

        alumnos = this.alumnoService.findAllAlumnos();
        assertThat(alumnos.size()).isEqualTo(tamano + 1);
    }

    @Test
    @Transactional
    public void shouldDeleteAlumno() {
    	Alumno alumno = this.alumnoService.findAlumnoById(1);
        this.alumnoService.deleteAlumno(alumno);
        try {
        	alumno = this.alumnoService.findAlumnoById(1);
		} catch (Exception e) {
			alumno = null;
		}
        assertThat(alumno).isNull();

    }
}
