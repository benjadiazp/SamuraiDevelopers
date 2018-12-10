package org.springframework.samurai.school.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samurai.school.model.Asignatura;
import org.springframework.samurai.school.model.Evaluacion;
import org.springframework.samurai.school.model.Profesor;
import org.springframework.samurai.school.rest.EvaluacionRestController;
import org.springframework.samurai.school.rest.ExceptionControllerAdvice;
import org.springframework.samurai.school.service.ApplicationTestConfig;
import org.springframework.samurai.school.service.EvaluacionService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
public class EvaluacionRestControllerTests {
	
	@Autowired
	private EvaluacionRestController evaluacionRestController;
	
	@MockBean
	protected EvaluacionService evaluacionService;
	
	private MockMvc mockMvc;
	
	private List <Evaluacion> evaluaciones;
	
	@Before
	public void initMensajes() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(evaluacionRestController)
				.setControllerAdvice(new ExceptionControllerAdvice())
				.build();
		
		evaluaciones = new ArrayList<Evaluacion>();
		
		Profesor profesor = new Profesor();
		profesor.setId(1);
		profesor.setNombre("Juan");
		profesor.setApellido("Perez");
		
		Asignatura asignatura = new Asignatura();
		asignatura.setId(2);
		asignatura.setNombre("Testing");
		asignatura.setProfesor(profesor);
		
		Evaluacion evaluacion = new Evaluacion();
		evaluacion.setId(3);
		evaluacion.setFecha(new Date());
		evaluacion.setAsignatura(asignatura);
		
		evaluaciones.add(evaluacion);
	}
	
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetEvaluacionSuccess() throws Exception {
    	given(this.evaluacionService.findEvaluacionById(3)).willReturn(evaluaciones.get(0));
        this.mockMvc.perform(get("/api/evaluaciones/3")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(3));
    }
	
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetEvaluacionNotFound() throws Exception {
    	given(this.evaluacionService.findEvaluacionById(1)).willReturn(null);
        this.mockMvc.perform(get("/api/evaluaciones/1")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
	}
	
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAllEvaluacionSuccess() throws Exception {
    	given(this.evaluacionService.findAllEvaluacion()).willReturn(evaluaciones);
        this.mockMvc.perform(get("/api/evaluaciones/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0].id").value(3));
    }

	@Test
    @WithMockUser(roles="OWNER_ADMIN")
	public void testGetAllEvaluacionNotFound() throws Exception {
		evaluaciones.clear();
		given(this.evaluacionService.findAllEvaluacion()).willReturn(evaluaciones);
		this.mockMvc.perform(get("/api/evaluaciones/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testCreateEvaluacionSuccess() throws Exception {
		Evaluacion nuevaEvaluacion = evaluaciones.get(0);
		nuevaEvaluacion.setId(999);
		ObjectMapper mapper = new ObjectMapper();
		String newEvaluacionAsJSON = mapper.writeValueAsString(nuevaEvaluacion);
		this.mockMvc.perform(post("/api/evaluaciones/")
				.content(newEvaluacionAsJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testCreateEvaluacionError() throws Exception {
		//Evaluacion evaluacionError = evaluaciones.get(0);
		//evaluacionError.setId(null);
		//evaluacionError.setFecha(null);
		ObjectMapper mapper = new ObjectMapper();
		String evaluacionErrorAsJSON = mapper.writeValueAsString("null");
		this.mockMvc.perform(post("/api/evaluaciones/")
				.content(evaluacionErrorAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}


}
