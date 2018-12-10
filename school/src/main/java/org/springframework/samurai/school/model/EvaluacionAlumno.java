package org.springframework.samurai.school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samurai.school.rest.EvaluacionAlumnoDeserializer;
import org.springframework.samurai.school.rest.EvaluacionAlumnoSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "evaluacion_alumno")
@JsonSerialize(using = EvaluacionAlumnoSerializer.class)
@JsonDeserialize(using = EvaluacionAlumnoDeserializer.class)
public class EvaluacionAlumno extends EntidadBase{
	
	@Column(name="nota")
	private int nota;
	
	@ManyToOne
    @JoinColumn(name = "idalumno")
    private Alumno alumno;
	
	@ManyToOne
    @JoinColumn(name = "idevaluacion")
    private Evaluacion evaluacion;

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
}
