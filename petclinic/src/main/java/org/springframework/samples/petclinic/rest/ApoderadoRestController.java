/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.rest;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Apoderado;
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

/**
 * @author Vitaliy Fedoriv
 *
 */

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/apoderados")
public class ApoderadoRestController {

	@Autowired
	private ApoderadoService apoderadoService;

	@PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/*/apellido/{apellido}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Apoderado>> getApoderadosList(@PathVariable("apellido") String apellido) {
		if (apellido == null) {
			apellido = "";
		}
		Collection<Apoderado> apoderados = this.apoderadoService.findApoderadoByApellido(apellido);
		if (apoderados.isEmpty()) {
			return new ResponseEntity<Collection<Apoderado>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Apoderado>>(apoderados, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Apoderado>> getApoderados() {
		Collection<Apoderado> apoderados = this.apoderadoService.findAllApoderados();
		if (apoderados.isEmpty()) {
			return new ResponseEntity<Collection<Apoderado>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Apoderado>>(apoderados, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{apoderadoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Apoderado> getApoderado(@PathVariable("apoderadoId") int id) {
		Apoderado apoderado = null;
		apoderado = this.apoderadoService.findApoderadoById(id);
		if (apoderado == null) {
			return new ResponseEntity<Apoderado>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Apoderado>(apoderado, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Apoderado> addApoderado(@RequestBody @Valid Apoderado apoderado, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (apoderado == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Apoderado>(headers, HttpStatus.BAD_REQUEST);
		}
		this.apoderadoService.saveApoderado(apoderado);
		headers.setLocation(ucBuilder.path("/api/Apoderados/{id}").buildAndExpand(apoderado.getId()).toUri());
		return new ResponseEntity<Apoderado>(apoderado, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{apoderadoId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Apoderado> updateApoderado(@PathVariable("apoderadoId") int id, @RequestBody @Valid Apoderado apoderado,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (apoderado == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Apoderado>(headers, HttpStatus.BAD_REQUEST);
		}
		Apoderado currentApoderado = this.apoderadoService.findApoderadoById(id);
		if (currentApoderado == null) {
			return new ResponseEntity<Apoderado>(HttpStatus.NOT_FOUND);
		}
		currentApoderado.setNombre(apoderado.getNombre());
		currentApoderado.setId(apoderado.getId());
		currentApoderado.setApellido(apoderado.getApellido());
		
		
		this.apoderadoService.saveApoderado(currentApoderado);
		return new ResponseEntity<Apoderado>(currentApoderado, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.OWNER_ADMIN)" )
	@RequestMapping(value = "/{apoderadoId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteApoderado(@PathVariable("apoderadoId") int ApoderadoId) {
		Apoderado apoderado = this.apoderadoService.findApoderadoById(ApoderadoId);
		if (apoderado == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.apoderadoService.deleteApoderado(apoderado);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
