package org.springframework.samples.petclinic.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.samples.petclinic.rest.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "Curso")
@JsonSerialize(using = CursoSerializer.class)
@JsonDeserialize(using = CursoDeserializer.class)
public class Curso  extends EntidadBase  {
	@Column(name = "grado")
	@NotEmpty
	private byte grado;
	
	@Column(name = "nivel")
	@NotEmpty
	private String nivel;
	
	@Column(name = "clase")
	@NotEmpty
	private String clase;
	
	public byte getGrado() {
		return grado;
	}
	public void setGrado(byte grado) {
		this.grado = grado;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	
}
