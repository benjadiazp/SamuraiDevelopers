package cl.samuraidevelopers.sdsaa.domain;

import javax.validation.constraints.*;
public class Apoderado extends Usuario {

public Apoderado(@NotNull @NotEmpty(message = "Debe ingresar un RUT vàlido (Ej: 19123123).") int id, String nombre,
		@NotNull @NotEmpty(message = "Debe ingresar una contraseña.") String clave,
		@NotNull @NotEmpty int idApoderado) {
	super(id, nombre, clave);
}

public Apoderado() {}
  
}
