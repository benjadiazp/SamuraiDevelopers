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
import org.springframework.samurai.school.model.Alumno;
import org.springframework.samurai.school.model.Anotacion;
import org.springframework.samurai.school.model.Apoderado;
import org.springframework.samurai.school.model.Curso;
import org.springframework.samurai.school.model.Profesor;
import org.springframework.samurai.school.rest.AnotacionRestController;
import org.springframework.samurai.school.rest.ExceptionControllerAdvice;
import org.springframework.samurai.school.service.AnotacionService;
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
public class AnotacionRestControllerTests {
	@Autowired
	private AnotacionRestController anotacionRestController;

	@MockBean
	protected AnotacionService anotacionService;

	private MockMvc mockMvc;

	private List<Anotacion> anotaciones;

	@Before
	public void initAlumnos(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(anotacionRestController)
				.setControllerAdvice(new ExceptionControllerAdvice())
				.build();
		
		anotaciones = new ArrayList<Anotacion>();

		Apoderado apoderado = new Apoderado();
		apoderado.setId(1);
		apoderado.setNombre("Apoderado1");
		apoderado.setApellido("Apellido1");

		Curso curso = new Curso();
		curso.setId(2);
		curso.setGrado(1);
		curso.setNivel("Medio");
		curso.setClase("B");

		Alumno alumno = new Alumno();
		alumno.setId(3);
		alumno.setNombre("Alumno1");
		alumno.setApellido("Apellido1");
		alumno.setApoderado(apoderado);
		alumno.setCurso(curso);

		Profesor profesor = new Profesor();
		profesor.setId(4);
		profesor.setNombre("Profesor1");
		profesor.setApellido("Apellido1");

		Anotacion anotacion = new Anotacion();
		anotacion.setId(5);
		anotacion.setTexto("Anotacion negativa 1 de prueba");
		anotacion.setFecha(new Date());
		anotacion.setTipo(0);
		anotacion.setAlumno(alumno);
		anotacion.setProfesor(profesor);
		anotaciones.add(anotacion);

		anotacion = new Anotacion();
		anotacion.setId(6);
		anotacion.setTexto("Anotacion negativa 2 de prueba");
		anotacion.setFecha(new Date());
		anotacion.setTipo(0);
		anotacion.setAlumno(alumno);
		anotacion.setProfesor(profesor);
		anotaciones.add(anotacion);
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testGetAnotacionSuccess() throws Exception {
		given(this.anotacionService.findAnotacionById(5)).willReturn(anotaciones.get(0));
		this.mockMvc.perform(get("/api/anotaciones/5")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value(5))
				.andExpect(jsonPath("$.texto").value("Anotacion negativa 1 de prueba"));
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testGetAnotacionNotFound() throws Exception {
		given(this.anotacionService.findAnotacionById(-1)).willReturn(null);
		this.mockMvc.perform(get("/api/anotaciones/-1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testGetAllAnotacionesSuccess() throws Exception {
		given(this.anotacionService.findAllAnotaciones()).willReturn(anotaciones);
		this.mockMvc.perform(get("/api/anotaciones/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.[0].id").value(5))
				.andExpect(jsonPath("$.[0].texto").value("Anotacion negativa 1 de prueba"))
				.andExpect(jsonPath("$.[1].id").value(6))
				.andExpect(jsonPath("$.[1].texto").value("Anotacion negativa 2 de prueba"));
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testGetAllAnotacionesNotFound() throws Exception {
		anotaciones.clear();
		given(this.anotacionService.findAllAnotaciones()).willReturn(anotaciones);
		this.mockMvc.perform(get("/api/anotaciones/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testCreateAnotacionSuccess() throws Exception {
		Anotacion nuevaAnotacion = anotaciones.get(0);
		nuevaAnotacion.setId(999);
		ObjectMapper mapper = new ObjectMapper();
		String newAnotacionAsJSON = mapper.writeValueAsString(nuevaAnotacion);
		this.mockMvc.perform(post("/api/anotaciones/")
				.content(newAnotacionAsJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(roles="OWNER_ADMIN")
	public void testCreateAnotacionError() throws Exception {
		Anotacion nuevaAnotacion = anotaciones.get(0);
		nuevaAnotacion.setTexto(null);
		ObjectMapper mapper = new ObjectMapper();
		String newAnotacionAsJSON = mapper.writeValueAsString(nuevaAnotacion);
		this.mockMvc.perform(post("/api/anotaciones/")
				.content(newAnotacionAsJSON)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());
	}
}
