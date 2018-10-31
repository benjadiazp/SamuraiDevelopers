package org.springframework.samples.petclinic.rest;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Profesor;
import org.springframework.samples.petclinic.service.ProfesorService;
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
@RequestMapping("api/profesores")
public class ProfesorRestController {
	@Autowired
	private ProfesorService profesorService;

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{profesorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Profesor> getProfesor(@PathVariable("profesorId") int profesorId){
		Profesor profesor = this.profesorService.findProfesorById(profesorId);
		if(profesor == null){
			return new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Profesor>> getProfesores(){
		Collection<Profesor> profesores = this.profesorService.findAllProfesores();
		
		if(profesores.isEmpty()){
			return new ResponseEntity<Collection<Profesor>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Profesor>>(profesores, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Profesor> addProfesor(@RequestBody @Valid Profesor profesor, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (profesor == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Profesor>(headers, HttpStatus.BAD_REQUEST);
		}
		this.profesorService.saveProfesor(profesor);
		headers.setLocation(ucBuilder.path("/api/Profesor/{id}").buildAndExpand(profesor.getId()).toUri());
		return new ResponseEntity<Profesor>(profesor, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{profesorId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Profesor> updateProfesor(@PathVariable("ProfesorId") int ProfesorId, @RequestBody @Valid Profesor profesor, BindingResult bindingResult){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (profesor == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Profesor>(headers, HttpStatus.BAD_REQUEST);
		}
		Profesor currentProfesor = this.profesorService.findProfesorById(ProfesorId);
		if(currentProfesor == null){
			return new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		}
		currentProfesor.setId(profesor.getId());
		currentProfesor.setNombre(profesor.getNombre());
		currentProfesor.setApellido(profesor.getApellido());
		currentProfesor.setAnotaciones(profesor.getAnotaciones());
		this.profesorService.saveProfesor(currentProfesor);
		return new ResponseEntity<Profesor>(currentProfesor, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.APODERADO_ADMIN)" )
   	@RequestMapping(value = "/{profesorId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   	@Transactional
   	public ResponseEntity<Void> deleteProfesor(@PathVariable("ProfesorId") int profesorId){
   		Profesor profesor = this.profesorService.findProfesorById(profesorId);
   		if(profesor == null){
   			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
   		}
   		this.profesorService.deleteProfesor(profesor);
   		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
   	}

}
