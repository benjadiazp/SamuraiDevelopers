package org.springframework.samples.petclinic.service;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCursoServiceTests {
	
	@Autowired
	protected CursoService cursoService;
	
	@Autowired
	protected AlumnoService alumnoService; 
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void shouldFindCursoById() {
    	Curso curso = this.cursoService.findCursoById(1);
    	assertThat(curso.getId()).isEqualTo(1);
    }
	
	@Test
    public void shouldFindAllCursos() {
    	Collection<Curso> cursos = this.cursoService.findAllCurso();
        Curso curso1 = EntityUtils.getById(cursos, Curso.class, 1);
        assertThat(curso1.getId()).isEqualTo(1);
        Curso curso2 = EntityUtils.getById(cursos, Curso.class, 2);
        assertThat(curso2.getId()).isEqualTo(2);
    }
	
	@Test
    @Transactional
    public void shouldSaveCurso() {
		Collection<Curso> cursos = this.cursoService.findAllCurso();
        int tamano = cursos.size();

        Curso curso = new Curso();
        curso.setId(999);
        curso.setGrado(4);
        curso.setNivel("medio");
        curso.setClase("A");
        this.cursoService.saveCurso(curso);
        assertThat(curso.getId().longValue()).isNotEqualTo(0);
        
        cursos = this.cursoService.findAllCurso();
        assertThat(cursos.size()).isEqualTo(tamano + 1);
    }
	
	@Test
    @Transactional
    public void shouldDeleteCurso() {
		Curso curso = this.cursoService.findCursoById(1);
		this.cursoService.deleteCurso(curso);
		
		try {
			curso = this.cursoService.findCursoById(1);
		} catch (Exception e) {
			curso = null;
		}
        assertThat(curso).isNull();

    }

}
