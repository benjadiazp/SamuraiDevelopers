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
	    	
	    	apoderado.setId(1);
	        apoderado.setNombre("Apoderado1");
	        apoderado.setApellido("Apellido");
	    	
	    	apoderados.add(apoderado);
	    	apoderado.setId(2);
	        apoderado.setNombre("Apoderado2");
	        apoderado.setApellido("Apellido");
	        apoderados.add(apoderado);
	    	
	    }
	    
	    @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetApoderadoSuccess() throws Exception {
	    	given(this.apoderadoService.findApoderadoById(2)).willReturn(apoderados.get(1));
	        this.mockMvc.perform(get("/api/apoderados/2")
	        	.accept(MediaType.APPLICATION_JSON_VALUE))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.id").value(2))
	            .andExpect(jsonPath("$.nombre").value("Apoderado2"));
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
	        this.mockMvc.perform(get("/api/apoderados")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.[0].id").value(7))
	            .andExpect(jsonPath("$.[0].nombre").value("Apoderado1"))
	            .andExpect(jsonPath("$.[1].id").value(5))
	            .andExpect(jsonPath("$.[1].nombre").value("Apoderado2"));
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
