package org.springframework.samples.petclinic.rest;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.*;
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
@RequestMapping("api/asignaturas")
public class AsignaturaRestController {
	@Autowired
	private AsignaturaService asignaturaService;
	
    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{asignaturaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Asignatura> getAlumno(@PathVariable("asignaturaId") int asignaturaId){
		Asignatura asignatura = this.asignaturaService.findAsignaturaById(asignaturaId);
		
		if(asignatura == null){
			return new ResponseEntity<Asignatura>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Asignatura>(asignatura, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Asignatura>> getAsignatura(){
		Collection<Asignatura> asignatura = this.asignaturaService.findAllAsignatura();
		System.out.println("Consultando asignaturas...");
		System.out.println("Asignatura: " + asignatura.toString());
		if(asignatura.isEmpty()){
			return new ResponseEntity<Collection<Asignatura>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Asignatura>>(asignatura, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Asignatura> addAsignatura(@RequestBody @Valid Asignatura asignatura, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (asignatura == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Asignatura>(headers, HttpStatus.BAD_REQUEST);
		}
		this.asignaturaService.saveAsignatura(asignatura);
		headers.setLocation(ucBuilder.path("/api/asignaturas/{id}").buildAndExpand(asignatura.getId()).toUri());
		return new ResponseEntity<Asignatura>(asignatura, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{asignaturaId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Asignatura> updateAsignatura(@PathVariable("AsignaturaId") int AsignaturaId, @RequestBody @Valid Asignatura asignatura, BindingResult bindingResult){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (asignatura == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Asignatura>(headers, HttpStatus.BAD_REQUEST);
		}
		Asignatura currentAsignatura = this.asignaturaService.findAsignaturaById(AsignaturaId);
		if(currentAsignatura == null){
			return new ResponseEntity<Asignatura>(HttpStatus.NOT_FOUND);
		}
		currentAsignatura.setId(asignatura.getId());
		currentAsignatura.setNombre(asignatura.getNombre());
		currentAsignatura.setProfesor(asignatura.getProfesor());
		this.asignaturaService.saveAsignatura(currentAsignatura);
		return new ResponseEntity<Asignatura>(currentAsignatura, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.APODERADO_ADMIN)" )
	@RequestMapping(value = "/{asignaturaId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteAlumno(@PathVariable("AsignaturaId") int asignaturaId){
		Asignatura asignatura = this.asignaturaService.findAsignaturaById(asignaturaId);
		if(asignatura == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.asignaturaService.deleteAsignatura(asignatura);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
