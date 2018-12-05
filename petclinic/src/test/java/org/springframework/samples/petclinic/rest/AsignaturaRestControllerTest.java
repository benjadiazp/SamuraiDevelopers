package org.springframework.samples.petclinic.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Anotacion;
import org.springframework.samples.petclinic.model.Asignatura;
import org.springframework.samples.petclinic.model.Profesor;
import org.springframework.samples.petclinic.service.ApplicationTestConfig;
import org.springframework.samples.petclinic.service.AsignaturaService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration


public class AsignaturaRestControllerTest {
	@Autowired
	private AsignaturaRestController asignaturaRestController;
	
	@MockBean
    private AsignaturaService asignaturaService;
	
	private MockMvc mockMvc;
	
	private List<Asignatura> asignaturas;
	
	@Before
	public void initAsignatura() {
		  this.mockMvc = MockMvcBuilders.standaloneSetup(asignaturaRestController)
	                .setControllerAdvice(new ExceptionControllerAdvice())
	                .build();
	        asignaturas = new ArrayList<Asignatura>();
	        Profesor profesor = new Profesor();
	        Asignatura asignatura = new Asignatura();
	        profesor.setId(4);
	        profesor.setNombre("Gaston");
	        profesor.setApellido("Lara");
	        
	        asignatura.setId(5);
	        asignatura.setNombre("Lenguaje");
	        asignatura.setProfesor(profesor);
	        
	        asignaturas.add(asignatura);
	        asignatura= new Asignatura();
	        asignatura.setId(6);
	        asignatura.setNombre("Matematica");
	        asignatura.setProfesor(profesor);
	        
	        asignaturas.add(asignatura);
	               
	}
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAsignaturaSuccess() throws Exception {
        given(this.asignaturaService.findAsignaturaById(5)).willReturn(asignaturas.get(0));
        this.mockMvc.perform(get("/api/asignaturas/5")
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(5))
            .andExpect(jsonPath("$.nombre").value("Lenguaje"));
    }
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAsignaturaNotFound() throws Exception {
        given(this.asignaturaService.findAsignaturaById(-1)).willReturn(null);
        this.mockMvc.perform(get("/api/asignaturas/-1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
	   @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllAsignaturaSuccess() throws Exception {
	    	given(this.asignaturaService.findAllAsignatura()).willReturn(asignaturas);
	        this.mockMvc.perform(get("/api/asignaturas/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.[0].id").value(5))
	            .andExpect(jsonPath("$.[0].nombre").value("Lenguaje"))

	            .andExpect(jsonPath("$.[1].id").value(6))
	            .andExpect(jsonPath("$.[1].nombre").value("Matematica"));
	    }
}
