package org.springframework.samurai.school.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samurai.school.model.*;
import org.springframework.samurai.school.rest.AlumnoRestController;
import org.springframework.samurai.school.rest.ExceptionControllerAdvice;
import org.springframework.samurai.school.service.AlumnoService;
import org.springframework.samurai.school.service.ApplicationTestConfig;
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
public class AlumnoRestControllerTests {
	 @Autowired
	    private AlumnoRestController alumnoRestController;

	    @MockBean
	    protected AlumnoService alumnoService;

	    private MockMvc mockMvc;

	    private List<Alumno> alumnos;

	    @Before
	    public void initAlumnos(){
	    	this.mockMvc = MockMvcBuilders.standaloneSetup(alumnoRestController)
	    			.setControllerAdvice(new ExceptionControllerAdvice())
	    			.build();
	    	alumnos = new ArrayList<Alumno>();

	    	Apoderado apoderado = new Apoderado();
	    	apoderado.setId(1);
	    	apoderado.setNombre("Apoderado1");
	    	apoderado.setApellido("Apellido 1");

	    	Curso curso = new Curso();
	    	curso.setId(2);
	    	curso.setGrado(1);
	    	curso.setNivel("Medio");

	    	Alumno alumno = new Alumno();
	    	alumno.setId(3);
	    	alumno.setNombre("Alumno1");
	    	alumno.setApellido("Apellido1");
	    	alumno.setApoderado(apoderado);
	    	alumno.setCurso(curso);
	    	alumnos.add(alumno);

	    	alumno = new Alumno();
	    	alumno.setId(4);
	    	alumno.setNombre("Alumno2");
	    	alumno.setApellido("Apellido1");
	    	alumno.setApoderado(apoderado);
	    	alumno.setCurso(curso);
	    	alumnos.add(alumno);
	    }

	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAlumnoSuccess() throws Exception {
	    	given(this.alumnoService.findAlumnoById(3)).willReturn(alumnos.get(0));
	        this.mockMvc.perform(get("/api/alumnos/3")
	        	.accept(MediaType.APPLICATION_JSON_VALUE))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.id").value(3))
	            .andExpect(jsonPath("$.nombre").value("Alumno1"));
	    }

	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAlumnoNotFound() throws Exception {
	    	given(this.alumnoService.findAlumnoById(-1)).willReturn(null);
	        this.mockMvc.perform(get("/api/alumnos/-1")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotFound());
	    }

	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllAlumnosSuccess() throws Exception {
	    	given(this.alumnoService.findAllAlumnos()).willReturn(alumnos);
	        this.mockMvc.perform(get("/api/alumnos/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.[0].id").value(3))
	            .andExpect(jsonPath("$.[0].nombre").value("Alumno1"))
	            .andExpect(jsonPath("$.[1].id").value(4))
	            .andExpect(jsonPath("$.[1].nombre").value("Alumno2"));
	    }

	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllAlumnosNotFound() throws Exception {
	    	alumnos.clear();
	    	given(this.alumnoService.findAllAlumnos()).willReturn(alumnos);
	        this.mockMvc.perform(get("/api/alumnos/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotFound());
	    }

	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testCreateAlumnoSuccess() throws Exception {
	    	Alumno nuevoAlumno = alumnos.get(0);
	    	nuevoAlumno.setId(999);
	    	ObjectMapper mapper = new ObjectMapper();
	    	String newAlumnoAsJSON = mapper.writeValueAsString(nuevoAlumno);
	    	this.mockMvc.perform(post("/api/alumnos/")
	    		.content(newAlumnoAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
	    		.andExpect(status().isCreated());
	    }

	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testCreateAlumnoError() throws Exception {
	    	Alumno newAlumno = alumnos.get(0);
	    	newAlumno.setId(null);
	    	newAlumno.setNombre(null);
	    	ObjectMapper mapper = new ObjectMapper();
	    	String newAlumnoAsJSON = mapper.writeValueAsString(newAlumno);
	    	this.mockMvc.perform(post("/api/alumnos/")
	        		.content(newAlumnoAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
	        		.andExpect(status().isBadRequest());
	     }
}
