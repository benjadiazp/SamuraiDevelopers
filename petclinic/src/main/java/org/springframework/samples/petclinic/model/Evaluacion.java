package org.springframework.samples.petclinic.model;

import java.sql.Date;
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

@Entity
@Table(name = "evaluacion")
public class Evaluacion extends EntidadBase{
	
    @Column(name="fecha")
	@NotEmpty
	private Date fecha;
    
	@ManyToOne
    @JoinColumn(name = "idasignatura")
    private Asignatura asignatura;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluacion", fetch = FetchType.EAGER)
    private Set<EvaluacionAlumno> evaluacionesAlumnos;

	public Set<EvaluacionAlumno> getEvaluacionesAlumnos() {
		return evaluacionesAlumnos;
	}

	public void setEvaluacionesAlumnos(Set<EvaluacionAlumno> evaluacionesAlumnos) {
		this.evaluacionesAlumnos = evaluacionesAlumnos;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
    
    
}
