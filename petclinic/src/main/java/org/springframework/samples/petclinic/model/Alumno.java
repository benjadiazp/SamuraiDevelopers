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
import org.springframework.samples.petclinic.rest.JacksonCustomPetDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomPetSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "alumno")
@JsonSerialize(using = JacksonCustomPetSerializer.class)
@JsonDeserialize(using = JacksonCustomPetDeserializer.class)
public class Alumno extends Persona {

    @ManyToOne
    @JoinColumn(name = "idApoderado")
    private Apoderado apoderado;
    
    @Column(name = "idCurso")
    @NotEmpty
    private int curso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno", fetch = FetchType.EAGER)
    private Set<Anotacion> anotaciones;

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	public Set<Anotacion> getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(Set<Anotacion> anotaciones) {
		this.anotaciones = anotaciones;
	}
    
    
    
}
