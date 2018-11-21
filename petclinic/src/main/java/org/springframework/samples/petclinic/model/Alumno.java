package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.samples.petclinic.rest.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "alumno")
@JsonSerialize(using = AlumnoSerializer.class)
@JsonDeserialize(using = AlumnoDeserializer.class)
public class Alumno extends Persona {

    @ManyToOne
    @JoinColumn(name = "idapoderado")
    private Apoderado apoderado;
    
    @ManyToOne
    @JoinColumn(name = "idcurso")
    @NotEmpty
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno", fetch = FetchType.EAGER)
    private Set<Anotacion> anotaciones;

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Set<Anotacion> getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(Set<Anotacion> anotaciones) {
		this.anotaciones = anotaciones;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
    
    
    
}
