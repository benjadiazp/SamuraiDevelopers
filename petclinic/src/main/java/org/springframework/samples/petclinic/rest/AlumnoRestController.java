

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
@RequestMapping("api/Alumnos")
public class AlumnoRestController {

	@Autowired
	private AlumnoService alumnoService;

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{alumnoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Alumno> getPet(@PathVariable("AlumnoId") int alumnoId){
		Alumno alumno = this.alumnoService.findAlumnoById(alumnoId);
		if(alumno == null){
			return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Alumno>> getAlumnos(){
		Collection<Alumno> alumnos = this.alumnoService.findAllAlumnos();
		if(alumnos.isEmpty()){
			return new ResponseEntity<Collection<Alumno>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Alumno>>(alumnos, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Alumno> addAlumno(@RequestBody @Valid Alumno alumno, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (alumno == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Alumno>(headers, HttpStatus.BAD_REQUEST);
		}
		this.alumnoService.saveAlumno(alumno);
		headers.setLocation(ucBuilder.path("/api/Alumno/{id}").buildAndExpand(alumno.getId()).toUri());
		return new ResponseEntity<Alumno>(alumno, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{alumnoId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Alumno> updatePet(@PathVariable("AlumnoId") int AlumnoId, @RequestBody @Valid Alumno alumno, BindingResult bindingResult){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (alumno == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Alumno>(headers, HttpStatus.BAD_REQUEST);
		}
		Alumno currentAlumno = this.alumnoService.findAlumnoById(AlumnoId);
		if(currentAlumno == null){
			return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}
		currentAlumno.setId(alumno.getId());
		currentAlumno.setCurso(alumno.getCurso());
		currentAlumno.setId(alumno.getId());
		currentAlumno.setApoderado(alumno.getApoderado());	
		currentAlumno.setNombre(alumno.getNombre());
		currentAlumno.setApellido(alumno.getApellido());
		currentAlumno.setApoderado(alumno.getApoderado());
		this.alumnoService.saveAlumno(currentAlumno);
		return new ResponseEntity<Alumno>(currentAlumno, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.APODERADO_ADMIN)" )
	@RequestMapping(value = "/{alumnoId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteAlumno(@PathVariable("AlumnoId") int alumnoId){
		Alumno alumno = this.alumnoService.findAlumnoById(alumnoId);
		if(alumno == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.alumnoService.deleteAlumno(alumno);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
