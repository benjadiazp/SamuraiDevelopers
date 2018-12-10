package org.springframework.samurai.school.rest;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samurai.school.model.Evaluacion;
import org.springframework.samurai.school.service.EvaluacionService;
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
@RequestMapping("api/evaluaciones")
public class EvaluacionRestController {
	
	@Autowired
	private EvaluacionService evaluacionService; 
	
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value="/{evaluacionId}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Evaluacion> getEvaluacion(@PathVariable("evaluacionId") int evaluacionId){
		Evaluacion evaluacion = this.evaluacionService.findEvaluacionById(evaluacionId);
		if(evaluacion==null) {
			return new ResponseEntity<Evaluacion>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Evaluacion>(evaluacion, HttpStatus.OK);
	}
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Evaluacion>> getevaluacion(){
		Collection<Evaluacion> evaluacion = this.evaluacionService.findAllEvaluacion();
		if(evaluacion.isEmpty()){
			return new ResponseEntity<Collection<Evaluacion>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Evaluacion>>(evaluacion, HttpStatus.OK);
	}
	
	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Evaluacion> addEvaluacion(@RequestBody @Valid Evaluacion evaluacion, BindingResult bindingResult, UriComponentsBuilder ucBuilder){
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors() || (evaluacion == null)){
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Evaluacion>(headers, HttpStatus.BAD_REQUEST);
		}
		this.evaluacionService.saveEvaluacion(evaluacion);
		headers.setLocation(ucBuilder.path("/api/evaluaciones/{id}").buildAndExpand(evaluacion.getId()).toUri());
		return new ResponseEntity<Evaluacion>(evaluacion, headers, HttpStatus.CREATED);
	}
	
		@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
		@RequestMapping(value = "/{evaluacionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Evaluacion> updateEvaluacion(@PathVariable("evaluacionId") int evaluacionId, @RequestBody @Valid Evaluacion evaluacion, BindingResult bindingResult){
			BindingErrorsResponse errors = new BindingErrorsResponse();
			HttpHeaders headers = new HttpHeaders();
			if(bindingResult.hasErrors() || (evaluacion == null)){
				errors.addAllErrors(bindingResult);
				headers.add("errors", errors.toJSON());
				return new ResponseEntity<Evaluacion>(headers, HttpStatus.BAD_REQUEST);
			}
			Evaluacion currentEvaluacion = this.evaluacionService.findEvaluacionById(evaluacionId);
			if(currentEvaluacion == null){
				return new ResponseEntity<Evaluacion>(HttpStatus.NOT_FOUND);
			}
			currentEvaluacion.setId(evaluacion.getId());
			currentEvaluacion.setAsignatura(evaluacion.getAsignatura());
			currentEvaluacion.setFecha(evaluacion.getFecha());
		
			this.evaluacionService.saveEvaluacion(currentEvaluacion);
			return new ResponseEntity<Evaluacion>(currentEvaluacion, HttpStatus.NO_CONTENT);
		}
		
		@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
		@RequestMapping(value="/{evaluacionId}",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Evaluacion> deleteEvaluacion(@PathVariable("evaluacionId") int evaluacionId){
			Evaluacion evaluacion = this.evaluacionService.findEvaluacionById(evaluacionId);
			if(evaluacion==null) {
				return new ResponseEntity<Evaluacion>(HttpStatus.NOT_FOUND);
			}
			System.out.println("Eliminando... " + evaluacion.toString());
			this.evaluacionService.deleteEvaluacion(evaluacion);
			return new ResponseEntity<Evaluacion>(evaluacion, HttpStatus.NO_CONTENT);
		}
	
}
