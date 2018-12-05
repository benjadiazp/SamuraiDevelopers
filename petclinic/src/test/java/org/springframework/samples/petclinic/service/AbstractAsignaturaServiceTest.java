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
public class AbstractAsignaturaServiceTest {
	
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
    }

 /*   @Test
    public void shouldFindAllAsignatura() {
    	Collection<Asignatura> asignaturas = this.asignaturaService.findAllAsignatura();
        Asignatura nombre = EntityUtils.getById(asignaturas, Asignatura.class, 1);
        assertThat(nombre.getNombre()).isEqualTo("Lenjuage");
        Asignatura nombre1 = EntityUtils.getById(asignaturas, Asignatura.class, 3);
        assertThat(nombre1.getNombre()).isEqualTo("Matematica");
    }*/



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
