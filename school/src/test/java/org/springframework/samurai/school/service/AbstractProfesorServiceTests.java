package org.springframework.samurai.school.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samurai.school.model.Profesor;
import org.springframework.samurai.school.service.ProfesorService;
import org.springframework.samurai.school.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractProfesorServiceTests {
	@Autowired
    protected ProfesorService profesorService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldFindProfesoresById() {
    	Profesor profesor = this.profesorService.findProfesorById(1);
    	assertThat(profesor.getId()).isEqualTo(1);
    }

    @Test
    public void shouldFindAllProfesores() {
    	Collection<Profesor> profesores = this.profesorService.findAllProfesores();
        Profesor profesor1 = EntityUtils.getById(profesores, Profesor.class, 1);
        assertThat(profesor1.getNombre()).isEqualTo("Alexis");
        Profesor profesor3 = EntityUtils.getById(profesores, Profesor.class, 3);
        assertThat(profesor3.getNombre()).isEqualTo("Tadeo");
    }

    @Test
    @Transactional
    public void shouldSaveProfesor() {
    	Collection<Profesor> profesores = this.profesorService.findAllProfesores();
        int tamano = profesores.size();

        Profesor profesor = new Profesor();
        profesor.setNombre("ProfesorTest");
        profesor.setApellido("ApellidoTest");
        this.profesorService.saveProfesor(profesor);
        assertThat(profesor.getId().longValue()).isNotEqualTo(0);

        profesores = this.profesorService.findAllProfesores();
        assertThat(profesores.size()).isEqualTo(tamano + 1);
    }

    @Test
    @Transactional
    public void shouldDeleteProfesor() {
    	Profesor profesor = this.profesorService.findProfesorById(1);
        this.profesorService.deleteProfesor(profesor);
        try {
        	profesor = this.profesorService.findProfesorById(1);
		} catch (Exception e) {
			profesor = null;
		}
        assertThat(profesor).isNull();

    }	
}
