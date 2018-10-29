package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.rest.JacksonCustomOwnerDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomOwnerSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "profesor")
@JsonSerialize(using = JacksonCustomOwnerSerializer.class)
@JsonDeserialize(using = JacksonCustomOwnerDeserializer.class)
public class Profesor extends Persona {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor", fetch = FetchType.EAGER)
    private Set<Anotacion> anotaciones;

	public Set<Anotacion> getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(Set<Anotacion> anotaciones) {
		this.anotaciones = anotaciones;
	}
	
	
}
