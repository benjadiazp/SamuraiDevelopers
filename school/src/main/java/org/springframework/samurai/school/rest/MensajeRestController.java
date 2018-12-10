package org.springframework.samurai.school.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samurai.school.model.Mensaje;
import org.springframework.samurai.school.service.MensajeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/mensajes")
public class MensajeRestController {
	@Autowired
	private MensajeService mensajeService;
	
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value="/{mensajeId}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Mensaje> getAnotacion(@PathVariable("mensajeId") int mensajeId){
		Mensaje mensaje = this.mensajeService.findMensajeById(mensajeId);
		if(mensaje==null) {
			return new ResponseEntity<Mensaje>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
	}
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Mensaje>> getAnotaciones(){
		Collection<Mensaje> anotaciones = this.mensajeService.findAllMensajes();
		
		if(anotaciones.isEmpty()){
			return new ResponseEntity<Collection<Mensaje>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Mensaje>>(anotaciones, HttpStatus.OK);
	}
	
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Mensaje> addMensaje(@RequestBody @Valid Mensaje mensaje, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (mensaje == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Mensaje>(headers, HttpStatus.BAD_REQUEST);
		}
		this.mensajeService.saveMensaje(mensaje);
		headers.setLocation(ucBuilder.path("/api/Anotacion/{id}").buildAndExpand(mensaje.getId()).toUri());
		return new ResponseEntity<Mensaje>(mensaje, headers, HttpStatus.CREATED);
	}
	
	
}
