package cl.samuraidevelopers.sdsaa.domain;
import javax.validation.constraints.*;
public class Profesor extends Usuario{

	public Profesor(@NotNull @NotEmpty(message = "Debe ingresar un RUT vàlido (Ej: 19123123).") int id, String nombre,
			@NotNull @NotEmpty(message = "Debe ingresar una contraseña.") String clave) {
		super(id, nombre, clave);
		// TODO Auto-generated constructor stub
	}
	
	public Profesor() {}

	
}
