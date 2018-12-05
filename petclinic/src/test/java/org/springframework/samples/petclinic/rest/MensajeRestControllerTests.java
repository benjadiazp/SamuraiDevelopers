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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.service.ApplicationTestConfig;
import org.springframework.samples.petclinic.service.MensajeService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.samples.petclinic.model.Apoderado;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.Profesor;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
public class MensajeRestControllerTests {

	@Autowired
	private MensajeRestController mensajeRestController;
	
	@MockBean
	protected MensajeService mensajeService;
	
	private MockMvc mockMvc;
	
	private List <Mensaje> mensajes;
	
	@Before
	public void initMensajes() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(mensajeRestController).setControllerAdvice(new ExceptionControllerAdvice()).build();
		
		mensajes = new ArrayList<Mensaje>();
		
		Apoderado apoderado = new Apoderado();
		apoderado.setId(1);
		apoderado.setNombre("Pedro");
		apoderado.setApellido("Picapiedra");
		
		
		Profesor profesor = new Profesor();
		profesor.setId(2);
		profesor.setNombre("Lucas");
		profesor.setApellido("Contigo");
		
		Mensaje mensaje = new Mensaje();
		mensaje.setId(3);
		mensaje.setFecha(new Date());
		mensaje.setApoderado(apoderado);
		mensaje.setProfesor(profesor);
		mensaje.setTexto("El hijo bueno para falta oe si");
		
	}
	
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetMensajeSuccess() throws Exception {
    	given(this.mensajeService.findMensajeById(3)).willReturn(mensajes.get(0));
        this.mockMvc.perform(get("/api/mensajes/3")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.texto").value("El hijo bueno para falta oe si"));
    }
	
	
	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetMensajesNotFound() throws Exception {
    	given(this.mensajeService.findMensajeById(-1)).willReturn(null);
        this.mockMvc.perform(get("/api/mensajes/-1")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
	
	 @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllMensajesSuccess() throws Exception {
	    	given(this.mensajeService.findAllMensajes()).willReturn(mensajes);
	        this.mockMvc.perform(get("/api/mensajes/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType("application/json;charset=UTF-8"))
	            .andExpect(jsonPath("$.[0].id").value(3))
	            .andExpect(jsonPath("$.[0].texto").value("El hijo bueno para falta oe si"));
	    }
	 
	 @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testGetAllMensajesNotFound() throws Exception {
	    	mensajes.clear();
	    	given(this.mensajeService.findAllMensajes()).willReturn(mensajes);
	        this.mockMvc.perform(get("/api/mensajes/")
	        	.accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotFound());
	    }
	 
	 @Test
	    @WithMockUser(roles="OWNER_ADMIN")
	    public void testCreateMensajesSuccess() throws Exception {
	    	Mensaje nuevoMensaje = mensajes.get(0);
	    	nuevoMensaje.setId(999);
	    	ObjectMapper mapper = new ObjectMapper();
	    	String newMensajeAsJSON = mapper.writeValueAsString(nuevoMensaje);
	    	this.mockMvc.perform(post("/api/mensajes/")
	    		.content(newMensajeAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
	    		.andExpect(status().isCreated());
	    }
}
