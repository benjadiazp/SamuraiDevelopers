package org.springframework.samples.petclinic.model;

import java.util.Date;
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
@Table(name = "evaluacion")
@JsonSerialize(using = EvaluacionSerializer.class)
@JsonDeserialize(using = EvaluacionDeserializer.class)
public class Evaluacion extends EntidadBase{
	
    @Column(name="fecha")
	@NotEmpty
	private Date fecha;
    
	@ManyToOne
    @JoinColumn(name = "idasignatura")
    private Asignatura asignatura;
	
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
