package org.springframework.samples.petclinic.rest;

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
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.ApplicationTestConfig;
import org.springframework.samples.petclinic.service.EvaluacionAlumnoService;
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
public class EvaluacionAlumnoRestControllerTests {
	
	@Autowired
    private EvaluacionAlumnoRestController evaluacionAlumnoRestController;
	
	@MockBean
    private EvaluacionAlumnoService evaluacionAlumnoService;
	
	private MockMvc mockMvc;

    private List<EvaluacionAlumno> evaluacionAlumnos;
	
    @Before
    public void initEvaluacion(){
    	this.mockMvc = MockMvcBuilders.standaloneSetup(evaluacionAlumnoRestController)
    			.setControllerAdvice(new ExceptionControllerAdvice())
    			.build();
    	evaluacionAlumnos = new ArrayList<EvaluacionAlumno>();

    	Alumno alumno = new Alumno();
    	Apoderado apoderado = new Apoderado();
    	Curso curso = new Curso();
    	
    	alumno.setId(1);
    	alumno.setNombre("Juanito");
    	alumno.setApellido("Perez");
    	alumno.setApoderado(apoderado);
    	alumno.setCurso(curso);
    	
    	Evaluacion evaluacion = new Evaluacion(); 
    	Asignatura asignatura = new Asignatura();
    	evaluacion.setId(1);
    	evaluacion.setFecha(null);
    	evaluacion.setAsignatura(asignatura);
    	
    	EvaluacionAlumno evaluacionAlumno = new EvaluacionAlumno();
    	evaluacionAlumno.setId(1);
    	evaluacionAlumno.setNota(22);
    	evaluacionAlumnos.add(evaluacionAlumno); 
    }
    
    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetEvaluacionAlumnoSuccess() throws Exception {
    	given(this.evaluacionAlumnoService.findEvaluacionAlumnoById(1)).willReturn(evaluacionAlumnos.get(0));
        this.mockMvc.perform(get("/api/evaluacionAlumno/1")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nota").value(22));
    }
    
    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetEvaluacionAlumnoNotFound() throws Exception {
    	given(this.evaluacionAlumnoService.findEvaluacionAlumnoById(-1)).willReturn(null);
        this.mockMvc.perform(get("/api/evaluacionAlumno/-1")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    
    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAllEvaluacionesAlumnosSuccess() throws Exception {
    	given(this.evaluacionAlumnoService.findAllEvaluacionAlumno()).willReturn(evaluacionAlumnos);
        this.mockMvc.perform(get("/api/evaluacionAlumno/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0].id").value(1))
            .andExpect(jsonPath("$.[0].nombre").value("Juanito"));
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAllEvaluacionesAlumnosNotFound() throws Exception {
    	evaluacionAlumnos.clear();
    	given(this.evaluacionAlumnoService.findAllEvaluacionAlumno()).willReturn(evaluacionAlumnos);
        this.mockMvc.perform(get("/api/evaluacionAlumno/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testCreateEvaluacionAlumnoSuccess() throws Exception {
    	EvaluacionAlumno evaluacionAlumnoNew = evaluacionAlumnos.get(0); 
    	evaluacionAlumnoNew.setId(10);
    	ObjectMapper mapper = new ObjectMapper();
    	String evaluacionAlumnoNewJson = mapper.writeValueAsString(evaluacionAlumnoNew);
    	this.mockMvc.perform(post("/api/evaluacionAlumno/")
    		.content(evaluacionAlumnoNewJson).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
    		.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testCreateEvaluacionAlumnoError() throws Exception {
    	EvaluacionAlumno evaluacionAlumnoNew = evaluacionAlumnos.get(0);
    	evaluacionAlumnoNew.setId(null);
    	evaluacionAlumnoNew.setNota(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String nevaluacionAlumnoNewJson = mapper.writeValueAsString(evaluacionAlumnoNew);
    	this.mockMvc.perform(post("/api/evaluacionAlumno/")
        		.content(nevaluacionAlumnoNewJson).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest());
     }

}
