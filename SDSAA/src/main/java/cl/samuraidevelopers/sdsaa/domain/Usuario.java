package cl.samuraidevelopers.sdsaa.domain;

import javax.validation.constraints.*;

public class Usuario {
	@NotNull(message="Debe ingresar un RUT vàlido (Ej: 19123123).")
	int idUsuario;
	String nombre;
	@NotNull @NotEmpty(message="Debe ingresar una contraseña.")
	String claveUsuario;
	
	public Usuario() {}
	
	public Usuario(@NotNull @NotEmpty(message = "Debe ingresar un RUT vàlido (Ej: 19123123).") int id, String nombre,
			@NotNull @NotEmpty(message = "Debe ingresar una contraseña.") String clave) {
		super();
		this.idUsuario = id;
		this.nombre = nombre;
		this.claveUsuario = clave;
	}
	public int getId() {
		return idUsuario;
	}
	public void setId(int id) {
		this.idUsuario = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return claveUsuario;
	}
	public void setClave(String clave) {
		this.claveUsuario = clave;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", claveUsuario=" + claveUsuario + "]";
	}
	
	
}
