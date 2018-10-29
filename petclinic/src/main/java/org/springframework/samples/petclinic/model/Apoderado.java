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
@Table(name = "apoderado")
@JsonSerialize(using = JacksonCustomOwnerSerializer.class)
@JsonDeserialize(using = JacksonCustomOwnerDeserializer.class)
public class Apoderado extends Persona {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "apoderado", fetch = FetchType.EAGER)
    private Set<Alumno> alumnos;

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	
}
