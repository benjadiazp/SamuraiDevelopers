package org.springframework.samples.petclinic.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

	//cambiar el evaluacionAlumno por evaluacionesAlumno
    private List<EvaluacionAlumno> evaluacionAlumnos;
	
    //Se inicializan 
    @Before
    public void initEvaluacion(){
    	this.mockMvc = MockMvcBuilders.standaloneSetup(evaluacionAlumnoRestController)
    			.setControllerAdvice(new ExceptionControllerAdvice())
    			.build();
    	evaluacionAlumnos = new ArrayList<EvaluacionAlumno>();

    	Alumno alumno = new Alumno();
    	Apoderado apoderado = new Apoderado();
    	Curso curso = new Curso();
    	
    	alumno.setId(10);
    	alumno.setNombre("nombre");
    	alumno.setApellido("apellido");
    	alumno.setApoderado(apoderado);
    	alumno.setCurso(curso);
    	
    	Evaluacion evaluacion = new Evaluacion(); 
    	Asignatura asignatura = new Asignatura();
    	evaluacion.setId(2);
    	evaluacion.setFecha(null);
    	evaluacion.setAsignatura(asignatura);
    	
    	EvaluacionAlumno evaluacionAlumno = new EvaluacionAlumno();
    	evaluacionAlumno.setId(20);
    	evaluacionAlumno.setNota(2);
    	evaluacionAlumnos.add(evaluacionAlumno); 
    }
    
    //Inicializar primer test: obtener evaluaciones de alumnos (prueba correcta) 
    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetEvaluacionAlumnoSuccess() throws Exception {
    	given(this.evaluacionAlumnoService.findEvaluacionAlumnoById(20)).willReturn(evaluacionAlumnos.get(0));
        this.mockMvc.perform(get("/api/evaluacionAlumno/20")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(20))
            .andExpect(jsonPath("$.nota").value(2));
    }
}
