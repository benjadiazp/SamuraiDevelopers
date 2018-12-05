package org.springframework.samples.petclinic.rest;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Alumno;
import org.springframework.samples.petclinic.model.Apoderado;
import org.springframework.samples.petclinic.model.Curso;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.Profesor;
import org.springframework.samples.petclinic.service.ApoderadoService;
import org.springframework.samples.petclinic.service.ApplicationTestConfig;
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
public class ApoderadoRestControllerTest {
	 	@Autowired
	    private ApoderadoRestController apoderadoRestController;

	    @MockBean
	    protected ApoderadoService apoderadoService;

	    private MockMvc mockMvc;

	    private List<Apoderado> apoderados;
	    
	    @Before
	    public void initApoderados(){
	    	this.mockMvc = MockMvcBuilders.standaloneSetup(apoderadoRestController)
	    			.setControllerAdvice(new ExceptionControllerAdvice())
	    			.build();
	    	apoderados = new ArrayList<Apoderado>();
	    	
	    	Apoderado apoderado = new Apoderado();
	    	Alumno alumno = new Alumno();
	    	Mensaje mensaje = new Mensaje();
	    	Profesor profesor = new Profesor();
	    	Curso curso = new Curso();
	    	
	    	curso.setId(7);
	    	curso.setGrado((byte)7);
	    	curso.setNivel("Basico");
	    	
	    	apoderado.setId(7);
	        apoderado.setNombre("ApoderadoName");
	        apoderado.setApellido("ApoderadoLastname");
	    	
	    	alumno.setId(7);
	    	alumno.setNombre("AlumnoName");
	    	alumno.setApellido("AlumnoLastname");
	    	alumno.setApoderado(apoderado);
	    	alumno.setCurso(curso);
	    	
	    	profesor.setId(7);
	    	profesor.setNombre("ProfesorName");
	    	profesor.setApellido("ProfesorLastname");
	    	
	    	mensaje.setId(7);
	    	mensaje.setApoderado(apoderado);
	    	mensaje.setProfesor(profesor);
	    	mensaje.setTexto("Mensaje de prueba.");
	    	mensaje.setFecha(new Date());
	    	
	    	//apoderado 2
	    	Apoderado apoderado2 = new Apoderado();
	    	Alumno alumno2 = new Alumno();
	    	Mensaje mensaje2 = new Mensaje();
	    	Profesor profesor2 = new Profesor();
	    	Curso curso2 = new Curso();
	    	
	    	curso2.setId(5);
	    	curso2.setGrado((byte)5);
	    	curso2.setNivel("Basico");
	    	
	    	apoderado2.setId(5);
	        apoderado2.setNombre("ApoderadoName2");
	        apoderado2.setApellido("ApoderadoLastname2");
	    	
	    	alumno2.setId(5);
	    	alumno2.setNombre("AlumnoName2");
	    	alumno2.setApellido("AlumnoLastname2");
	    	alumno2.setApoderado(apoderado2);
	    	alumno2.setCurso(curso2);
	    	
	    	profesor2.setId(5);
	    	profesor2.setNombre("ProfesorName2");
	    	profesor2.setApellido("ProfesorLastname2");
	    	
	    	mensaje2.setId(5);
	    	mensaje2.setApoderado(apoderado2);
	    	mensaje2.setProfesor(profesor2);
	    	mensaje2.setTexto("Mensaje de prueba 2.");
	    	mensaje2.setFecha(new Date());
	    	
	    	apoderados.add(apoderado);
	    	apoderados.add(apoderado2);
	    	
	    }
	    
	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetApoderadoSuccess() throws Exception {
	    	given(this.apoderadoService.findApoderadoById(7)).willReturn(apoderados.get(0));
	        this.mockMvc.perform(get("/api/apoderados/7")
	        	.accept(MediaType.APPLICATION_JSON_VALUE))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.id").value(7))
	            .andExpect(jsonPath("$.nombre").value("ApoderadoName"));
	    }
	    
	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetApoderadoNotFound() throws Exception {
	    	given(this.apoderadoService.findApoderadoById(-1)).willReturn(null);
	        this.mockMvc.perform(get("/api/apoderados/-1")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotFound());
	    }
	    
	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllApoderadosSuccess() throws Exception {
	    	given(this.apoderadoService.findAllApoderados()).willReturn(apoderados);
	        this.mockMvc.perform(get("/api/apoderados/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.[0].id").value(7))
	            .andExpect(jsonPath("$.[0].nombre").value("ApoderadoName"))
	            .andExpect(jsonPath("$.[1].id").value(5))
	            .andExpect(jsonPath("$.[1].nombre").value("ApoderadoName2"));
	    }
	    
	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllApoderadosNotFound() throws Exception {
	    	apoderados.clear();
	    	given(this.apoderadoService.findAllApoderados()).willReturn(apoderados);
	        this.mockMvc.perform(get("/api/apoderados/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotFound());
	    }
	    
	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testCreateApoderadoSuccess() throws Exception {
	    	Apoderado apoderadoSuccess = apoderados.get(0);
	    	apoderadoSuccess.setId(999);
	    	ObjectMapper mapper = new ObjectMapper();
	    	String apoderadoSuccessAsJSON = mapper.writeValueAsString(apoderadoSuccess);
	    	this.mockMvc.perform(post("/api/apoderados/")
	    		.content(apoderadoSuccessAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
	    		.andExpect(status().isCreated());
	    }
	    
	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testCreateApoderadoError() throws Exception {
	    	Apoderado apoderadoError = apoderados.get(0);
	    	apoderadoError.setId(null);
	    	apoderadoError.setNombre(null);
	    	ObjectMapper mapper = new ObjectMapper();
	    	String apoderadoErrorAsJSON = mapper.writeValueAsString(apoderadoError);
	    	this.mockMvc.perform(post("/api/apoderados/")
	        		.content(apoderadoErrorAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
	        		.andExpect(status().isBadRequest());
	     }
}
