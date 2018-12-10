package org.springframework.samurai.school.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samurai.school.rest.*;

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

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
    
    
    
}
