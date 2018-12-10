package org.springframework.samurai.school.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;
@MappedSuperclass
public class Persona extends EntidadBase {
	
	@Column(name = "nombre")
    @NotEmpty
    protected String nombre;

    @Column(name = "apellido")
    @NotEmpty
    protected String apellido;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
    
}
