package org.springframework.samurai.school.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.service.AsignaturaService;
import org.springframework.samurai.school.service.ProfesorService;
import org.springframework.samurai.school.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;
public abstract class AbstractAsignaturaServiceTest {
	
	@Autowired
    protected AsignaturaService asignaturaService;
	
	@Autowired
    protected ProfesorService profesorService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldFindAsignaturaById() {
    	Asignatura asignatura = this.asignaturaService.findAsignaturaById(1);
    	assertThat(asignatura.getId()).isEqualTo(1);
        assertThat(asignatura.getNombre()).isEqualTo("Matematicas");
    }
    
    @Test
    public void shouldFindAllAsignaturas() {
    	Collection<Asignatura> asignaturas = this.asignaturaService.findAllAsignatura();
    	
        Asignatura asignatura = EntityUtils.getById(asignaturas, Asignatura.class, 1);
        assertThat(asignatura.getNombre()).isEqualTo("Matematicas");
        
        asignatura = EntityUtils.getById(asignaturas, Asignatura.class, 3);
        assertThat(asignatura.getNombre()).isEqualTo("Historia");
        }

    @Test
    @Transactional
    public void shouldDeleteAsignatura() {
    	Asignatura asignatura = this.asignaturaService.findAsignaturaById(1);
    	
        this.asignaturaService.deleteAsignatura(asignatura);
        
        try {
        	asignatura = this.asignaturaService.findAsignaturaById(1);
		} catch (Exception e) {
			asignatura = null;
		}
        assertThat(asignatura).isNull();

    }
}
