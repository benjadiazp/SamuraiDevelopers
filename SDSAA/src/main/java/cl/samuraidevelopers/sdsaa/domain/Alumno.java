package cl.samuraidevelopers.sdsaa.domain;

import javax.validation.constraints.*;

public class Alumno extends Usuario{
  @NotNull @NotEmpty
  int idCurso;
  @NotNull @NotEmpty
  int idApoderado;
public Alumno(@NotNull @NotEmpty(message = "Debe ingresar un RUT vàlido (Ej: 19123123).") int id, String nombre,
		@NotNull @NotEmpty(message = "Debe ingresar una contraseña.") String clave, @NotNull @NotEmpty int idCurso,
		@NotNull @NotEmpty int idApoderado) {
	super(id, nombre, clave);
	this.idCurso = idCurso;
	this.idApoderado = idApoderado;
}

public Alumno() {}
public int getIdCurso() {
	return idCurso;
}
public void setIdCurso(int idCurso) {
	this.idCurso = idCurso;
}
public int getIdApoderado() {
	return idApoderado;
}
public void setIdApoderado(int idApoderado) {
	this.idApoderado = idApoderado;
}
  
  
}
