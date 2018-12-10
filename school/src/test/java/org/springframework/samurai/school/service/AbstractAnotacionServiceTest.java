package org.springframework.samurai.school.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samurai.school.model.Anotacion;

public abstract class AbstractAnotacionServiceTest {

	@Autowired
	protected AnotacionService AnotacionService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void shouldFindAnotacionById() {
    	Anotacion anotacion = this.AnotacionService.findAnotacionById(2);
    	
    	assertThat(anotacion.getId()).isEqualTo(2);
    	assertThat(anotacion.getTexto()).isEqualTo("Rompió una mesa.");
    }
	
	@Test
    public void shouldNotFindAnotacionById() {
    	Anotacion anotacion = this.AnotacionService.findAnotacionById(20);
    	
    	assertNull(anotacion);
    }
}
