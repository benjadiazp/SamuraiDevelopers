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
import org.springframework.samples.petclinic.model.Profesor;
import org.springframework.samples.petclinic.service.ApplicationTestConfig;
import org.springframework.samples.petclinic.service.ProfesorService;
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
public class ProfesorRestControllerTests {
	@Autowired
    private ProfesorRestController profesorRestController;

    @MockBean
    protected ProfesorService profesorService;

    private MockMvc mockMvc;

    private List<Profesor> profesores;

    @Before
    public void initProfesores(){
    	this.mockMvc = MockMvcBuilders.standaloneSetup(profesorRestController)
    			.setControllerAdvice(new ExceptionControllerAdvice())
    			.build();
    	profesores = new ArrayList<Profesor>();

    	Profesor profesor = new Profesor();
    	profesor.setId(3);
    	profesor.setNombre("Profesor1");
    	profesor.setApellido("Apellido1");
    	profesores.add(profesor);
    	
    	profesor = new Profesor();
    	profesor.setId(4);
    	profesor.setNombre("Profesor2");
    	profesor.setApellido("Apellido1");
    	profesores.add(profesor);
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetProfesorSuccess() throws Exception {
    	given(this.profesorService.findProfesorById(3)).willReturn(profesores.get(0));
        this.mockMvc.perform(get("/api/profesores/3")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.nombre").value("Profesor1"));
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetProfesorNotFound() throws Exception {
    	given(this.profesorService.findProfesorById(-1)).willReturn(null);
        this.mockMvc.perform(get("/api/profesores/-1")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAllProfesoresSuccess() throws Exception {
    	given(this.profesorService.findAllProfesores()).willReturn(profesores);
        this.mockMvc.perform(get("/api/profesores/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0].id").value(3))
            .andExpect(jsonPath("$.[0].nombre").value("Profesor1"))
            .andExpect(jsonPath("$.[1].id").value(4))
            .andExpect(jsonPath("$.[1].nombre").value("Profesor2"));
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAllProfesoresNotFound() throws Exception {
    	profesores.clear();
    	given(this.profesorService.findAllProfesores()).willReturn(profesores);
        this.mockMvc.perform(get("/api/profesores/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testCreateProfesorSuccess() throws Exception {
    	Profesor nuevoProfesor = profesores.get(0);
    	nuevoProfesor.setId(999);
    	ObjectMapper mapper = new ObjectMapper();
    	String newProfesorAsJSON = mapper.writeValueAsString(nuevoProfesor);
    	this.mockMvc.perform(post("/api/profesores/")
    		.content(newProfesorAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
    		.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testCreateProfesorError() throws Exception {
    	Profesor newProfesor = profesores.get(0);
    	newProfesor.setId(null);
    	newProfesor.setNombre(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newProfesorAsJSON = mapper.writeValueAsString(newProfesor);
    	this.mockMvc.perform(post("/api/profesores/")
        		.content(newProfesorAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest());
     }
}
