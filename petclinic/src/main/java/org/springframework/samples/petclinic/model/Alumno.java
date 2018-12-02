package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.samples.petclinic.rest.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
		if (anotaciones == null) {
			anotaciones = new HashSet<Anotacion>();
		}
		return anotaciones;
	}
	
	public void setAnotaciones(Set<Anotacion> anotaciones) {
		this.anotaciones = anotaciones;
	}
	
    public void addAnotacion(Anotacion anotacion) {
        getAnotaciones().add(anotacion);
        anotacion.setAlumno(this);
    }

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
    
    
    
}
