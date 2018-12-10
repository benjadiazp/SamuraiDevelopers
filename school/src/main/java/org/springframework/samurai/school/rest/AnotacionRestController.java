package org.springframework.samurai.school.rest;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samurai.school.model.Anotacion;
import org.springframework.samurai.school.service.AnotacionService;
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
@RequestMapping("api/anotaciones")
public class AnotacionRestController {
	
	@Autowired
	private AnotacionService anotacionService; 
	
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value="/{anotacionId}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Anotacion> getAnotacion(@PathVariable("anotacionId") int anotacionId){
		Anotacion anotacion = this.anotacionService.findAnotacionById(anotacionId);
		if(anotacion==null) {
			return new ResponseEntity<Anotacion>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Anotacion>(anotacion, HttpStatus.OK);
	}
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Anotacion>> getAnotaciones(){
		Collection<Anotacion> anotaciones = this.anotacionService.findAllAnotaciones();
		
		if(anotaciones.isEmpty()){
			return new ResponseEntity<Collection<Anotacion>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Anotacion>>(anotaciones, HttpStatus.OK);
	}
	
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Anotacion> addAnotacion(@RequestBody @Valid Anotacion anotacion, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (anotacion == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Anotacion>(headers, HttpStatus.BAD_REQUEST);
		}
		this.anotacionService.saveAnotacion(anotacion);
		headers.setLocation(ucBuilder.path("/api/anotaciones/{id}").buildAndExpand(anotacion.getId()).toUri());
		return new ResponseEntity<Anotacion>(anotacion, headers, HttpStatus.CREATED);
	}
	
		@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
		@RequestMapping(value = "/{anotacionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Anotacion> updateAnotacion(@PathVariable("anotacionId") int anotacionId, @RequestBody @Valid Anotacion anotacion, BindingResult bindingResult){
			BindingErrorsResponse errors = new BindingErrorsResponse();
			HttpHeaders headers = new HttpHeaders();
			if(bindingResult.hasErrors() || (anotacion == null)){
				errors.addAllErrors(bindingResult);
				headers.add("errors", errors.toJSON());
				return new ResponseEntity<Anotacion>(headers, HttpStatus.BAD_REQUEST);
			}
			Anotacion currentAnotacion = this.anotacionService.findAnotacionById(anotacionId);
			if(currentAnotacion == null){
				return new ResponseEntity<Anotacion>(HttpStatus.NOT_FOUND);
			}
			currentAnotacion.setId(anotacion.getId());
			currentAnotacion.setTexto(anotacion.getTexto());
			currentAnotacion.setTipo(anotacion.getTipo());
			currentAnotacion.setFecha(anotacion.getFecha());	
			currentAnotacion.setAlumno(anotacion.getAlumno());
			this.anotacionService.saveAnotacion(currentAnotacion);
			return new ResponseEntity<Anotacion>(currentAnotacion, HttpStatus.NO_CONTENT);
		}
		
		@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
		@RequestMapping(value="/{anotacionId}",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Anotacion> deleteAnotacion(@PathVariable("anotacionId") int anotacionId){
			Anotacion anotacion = this.anotacionService.findAnotacionById(anotacionId);
			if(anotacion==null) {
				return new ResponseEntity<Anotacion>(HttpStatus.NOT_FOUND);
			}
			this.anotacionService.deleteAnotacion(anotacion);
			return new ResponseEntity<Anotacion>(anotacion, HttpStatus.NO_CONTENT);
		}
}
