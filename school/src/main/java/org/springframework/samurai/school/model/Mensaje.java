package org.springframework.samurai.school.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.samurai.school.rest.MensajeDeserializer;
import org.springframework.samurai.school.rest.MensajeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "mensaje")
@JsonSerialize(using = MensajeSerializer.class)
@JsonDeserialize(using = MensajeDeserializer.class)
public class Mensaje extends EntidadBase {
	@Column(name="texto")
	@NotEmpty
	private String texto; 
	
	@Column(name="fecha")
	private Date fecha;
	
	@ManyToOne
    @JoinColumn(name = "idemisor")
    private Profesor profesor;
	
	@ManyToOne
    @JoinColumn(name = "idreceptor")
    private Apoderado apoderado;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

	@Override
	public String toString() {
		return "Mensaje [texto=" + texto + ", fecha=" + fecha + ", profesor=" + profesor + ", apoderado=" + apoderado
				+ "]";
	}
	
	
}
