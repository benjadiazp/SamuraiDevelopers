package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.rest.JacksonCustomOwnerDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomOwnerSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "anotacion")
@JsonSerialize(using = JacksonCustomOwnerSerializer.class)
@JsonDeserialize(using = JacksonCustomOwnerDeserializer.class)
public class Anotacion extends EntidadBase{
	@ManyToOne
    @JoinColumn(name = "idProfesor")
    private Profesor profesor;
	
	@ManyToOne
    @JoinColumn(name = "idAlumno")
    private Alumno alumno;

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	
}
