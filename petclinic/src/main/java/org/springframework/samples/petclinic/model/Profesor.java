package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.samples.petclinic.rest.*;

@Entity
@Table(name = "profesor")
@JsonSerialize(using = ProfesorSerializer.class)
@JsonDeserialize(using = ProfesorDeserializer.class)
public class Profesor extends Persona {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor", fetch = FetchType.EAGER)
    private Set<Anotacion> anotaciones;

	public Set<Anotacion> getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(Set<Anotacion> anotaciones) {
		this.anotaciones = anotaciones;
	}

	@Override
	public String toString() {
		return "Profesor [anotaciones=" + anotaciones + "]";
	}
	
	
}
