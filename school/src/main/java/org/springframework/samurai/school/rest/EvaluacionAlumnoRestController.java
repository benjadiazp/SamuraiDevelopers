package org.springframework.samurai.school.rest;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samurai.school.model.EvaluacionAlumno;
import org.springframework.samurai.school.service.EvaluacionAlumnoService;
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
@RequestMapping("api/evaluacionAlumno")
public class EvaluacionAlumnoRestController {

	@Autowired
	private EvaluacionAlumnoService evaluacionAlumnoService; 

	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value="/{evalAlumId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EvaluacionAlumno> getEvaluacionAlumno(@PathVariable("evalAlumId") int evalAlumId){
		EvaluacionAlumno evaluacionAlumno = this.evaluacionAlumnoService.findEvaluacionAlumnoById(evalAlumId);
		if(evaluacionAlumno==null) {
			return new ResponseEntity<EvaluacionAlumno>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EvaluacionAlumno>(evaluacionAlumno, HttpStatus.OK);
	}
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<EvaluacionAlumno>> getevaluacionAlumno(){
		Collection<EvaluacionAlumno> evaluacionAlumno = this.evaluacionAlumnoService.findAllEvaluacionAlumno();

		if(evaluacionAlumno.isEmpty()){
			return new ResponseEntity<Collection<EvaluacionAlumno>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<EvaluacionAlumno>>(evaluacionAlumno, HttpStatus.OK);
	}

	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EvaluacionAlumno> addEvaluacionAlumno(@RequestBody @Valid EvaluacionAlumno evaluacionAlumno, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (evaluacionAlumno == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<EvaluacionAlumno>(headers, HttpStatus.BAD_REQUEST);
		}
		this.evaluacionAlumnoService.saveEvaluacionAlumno(evaluacionAlumno);
		headers.setLocation(ucBuilder.path("/api/evaluacionAlumno/{id}").buildAndExpand(evaluacionAlumno.getId()).toUri());
		return new ResponseEntity<EvaluacionAlumno>(evaluacionAlumno, headers, HttpStatus.CREATED);
	}
	
	/* Modificar nota del alumno*/ 
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{evaluacionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EvaluacionAlumno> updateEvaluacionAlumno(@PathVariable("evaluacionAlumnoId") int evaluacionAlumnoId, @RequestBody @Valid EvaluacionAlumno evaluacionAlumno, BindingResult bindingResult){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (evaluacionAlumno == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<EvaluacionAlumno>(headers, HttpStatus.BAD_REQUEST);
		}
		EvaluacionAlumno currentEvaluacionAlumno = this.evaluacionAlumnoService.findEvaluacionAlumnoById(evaluacionAlumnoId);
		if(currentEvaluacionAlumno == null){
			return new ResponseEntity<EvaluacionAlumno>(HttpStatus.NOT_FOUND);
		}
		currentEvaluacionAlumno.setId(evaluacionAlumno.getId());
		//agregar más datos de EvaluacionAlumno
		this.evaluacionAlumnoService.saveEvaluacionAlumno(currentEvaluacionAlumno);
		return new ResponseEntity<EvaluacionAlumno>(currentEvaluacionAlumno, HttpStatus.NO_CONTENT);
	}

	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value="/{evaluacionId}",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<EvaluacionAlumno> deleteEvaluacionAlumno(@PathVariable("evaluacionAlumnoId") int evaluacionAlumnoId){
		EvaluacionAlumno evaluacionAlumno = this.evaluacionAlumnoService.findEvaluacionAlumnoById(evaluacionAlumnoId);
		if(evaluacionAlumno==null) {
			return new ResponseEntity<EvaluacionAlumno>(HttpStatus.NOT_FOUND);
		}
		this.evaluacionAlumnoService.deleteEvaluacionAlumno(evaluacionAlumno);
		return new ResponseEntity<EvaluacionAlumno>(evaluacionAlumno, HttpStatus.NO_CONTENT);
	}

}
