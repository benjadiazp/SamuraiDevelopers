package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Apoderado;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractApoderadoServiceTest {
	
	@Autowired
    protected ApoderadoService apoderadoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldFindApoderadoById() {
    	Apoderado apoderado = this.apoderadoService.findApoderadoById(1);
    	assertThat(apoderado.getId()).isEqualTo(1);
    }
    
    @Test
    public void shouldFindAllApoderados() {
    	Collection<Apoderado> apoderados = this.apoderadoService.findAllApoderados();
        Apoderado apoderado1 = EntityUtils.getById(apoderados, Apoderado.class, 1);
        assertThat(apoderado1.getNombre()).isEqualTo("Adriana");
        Apoderado apoderado3 = EntityUtils.getById(apoderados, Apoderado.class, 3);
        assertThat(apoderado3.getNombre()).isEqualTo("Camilo");
    }
    
    @Test
    @Transactional
    public void shouldSaveApoderado() {
    	Collection<Apoderado> apoderados = this.apoderadoService.findAllApoderados();
        int tamano = apoderados.size();

        Apoderado apoderado = new Apoderado();
        apoderado.setNombre("ApoderadoTest");
        apoderado.setApellido("ApellidoTest");
        this.apoderadoService.saveApoderado(apoderado);
        assertThat(apoderado.getId().longValue()).isNotEqualTo(0);

        apoderados = this.apoderadoService.findAllApoderados();
        assertThat(apoderados.size()).isEqualTo(tamano + 1);
    }

    @Test
    @Transactional
    public void shouldDeleteApoderado() {
    	Apoderado apoderado = this.apoderadoService.findApoderadoById(1);
        this.apoderadoService.deleteApoderado(apoderado);
        try {
        	apoderado = this.apoderadoService.findApoderadoById(1);
		} catch (Exception e) {
			apoderado = null;
		}
        assertThat(apoderado).isNull();
    }

}
