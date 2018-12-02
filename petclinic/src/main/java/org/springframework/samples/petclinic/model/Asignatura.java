package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.samples.petclinic.rest.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "Asignatura")
@JsonSerialize(using = AsignaturaSerializer.class)
@JsonDeserialize(using = AsignaturaDeserializer.class)
public class Asignatura extends EntidadBase {
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "idprofesor")
	@NotEmpty
	private Profesor profesor;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Asignatura [nombre=" + nombre + ", profesor=" + profesor + "]";
	}
	
	

}
