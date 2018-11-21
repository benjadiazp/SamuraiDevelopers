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
@RequestMapping("api/cursos")
public class CursoRestController {

	@Autowired
	private CursoService cursoService;

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{cursoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Curso> getCurso(@PathVariable("cursoId") int cursoId){
		Curso curso = this.cursoService.findCursoById(cursoId);
		if(curso == null){
			return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Curso>> getAlumnos(){
		Collection<Curso> curso = this.cursoService.findAllCurso();
		
		if(curso.isEmpty()){
			return new ResponseEntity<Collection<Curso>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Curso>>(curso, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Curso> addCurso(@RequestBody @Valid Curso curso, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (curso == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Curso>(headers, HttpStatus.BAD_REQUEST);
		}
		this.cursoService.saveCurso(curso);
		headers.setLocation(ucBuilder.path("/api/Alumno/{id}").buildAndExpand(curso.getId()).toUri());
		return new ResponseEntity<Curso>(curso, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{cursoId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Curso> updateCurso(@PathVariable("CursoId") int CursoId, @RequestBody @Valid Curso curso, BindingResult bindingResult){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (curso == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Curso>(headers, HttpStatus.BAD_REQUEST);
		}
		Curso currentCurso = this.cursoService.findCursoById(CursoId);
		if(currentCurso == null){
			return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		}
		currentCurso.setId(curso.getId());
		currentCurso.setClase(curso.getClase());
		currentCurso.setGrado(curso.getGrado());
		currentCurso.setNivel(curso.getNivel());
		this.cursoService.saveCurso(currentCurso);
		return new ResponseEntity<Curso>(currentCurso, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.APODERADO_ADMIN)" )
	@RequestMapping(value = "/{cursoId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteCurso(@PathVariable("CursoId") int cursoId){
		Curso curso = this.cursoService.findCursoById(cursoId);
		if(curso == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.cursoService.deleteCurso(curso);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
