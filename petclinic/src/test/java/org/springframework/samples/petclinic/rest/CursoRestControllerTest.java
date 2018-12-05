package org.springframework.samples.petclinic.rest;
import java.util.ArrayList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import org.springframework.samples.petclinic.service.ApplicationTestConfig;
import org.springframework.samples.petclinic.service.CursoService;
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
public class CursoRestControllerTest {
	@Autowired
	private CursoRestController cursoRestController;
	
	@MockBean
    private CursoService cursoService;
	
	private MockMvc mockMvc;
	
	private List<Curso> cursos;
	
	@Before
	public void initCurso() {
		  this.mockMvc = MockMvcBuilders.standaloneSetup(cursoRestController)
	                .setControllerAdvice(new ExceptionControllerAdvice())
	                .build();
	        cursos = new ArrayList<Curso>();
	        Curso curso = new Curso();  
	      
	        
	        
	        curso.setId(6);
	        curso.setGrado(1);
	    	curso.setNivel("Medio");
	    	curso.setAlumnos(null);
	    	cursos.add(curso);
	    	
	    	curso = new Curso();
	    	curso.setId(7);
	    	curso.setGrado(2);
	    	curso.setNivel("Basica");
	    	cursos.add(curso);
	    	

	        
	        
	               
	}
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetCursoSuccess() throws Exception {
        given(this.cursoService.findCursoById(6)).willReturn(cursos.get(0));
        this.mockMvc.perform(get("/api/cursos/6")
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(6))
            .andExpect(jsonPath("$.grado").value(1))
            .andExpect(jsonPath("$.nivel").value("Medio"));

    }
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetCursoNotFound() throws Exception {
        given(this.cursoService.findCursoById(-1)).willReturn(null);
        this.mockMvc.perform(get("/api/cursos/-1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
	   @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllCursoSuccess() throws Exception {
	    	given(this.cursoService.findAllCurso()).willReturn(cursos);
	        this.mockMvc.perform(get("/api/cursos/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.[0].id").value(6))
	            .andExpect(jsonPath("$.[0].grado").value(1))
	            .andExpect(jsonPath("$.[0].nivel").value("Medio"))

	            .andExpect(jsonPath("$.[1].id").value(7))
	            .andExpect(jsonPath("$.[1].grado").value(2))
	            .andExpect(jsonPath("$.[1].nivel").value("Basica"));
	    }

}
