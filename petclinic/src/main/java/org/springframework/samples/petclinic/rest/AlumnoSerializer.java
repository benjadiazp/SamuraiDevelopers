package org.springframework.samples.petclinic.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samples.petclinic.model.Alumno;
import org.springframework.samples.petclinic.model.Apoderado;
import org.springframework.samples.petclinic.model.Curso;
import org.springframework.samples.petclinic.model.Anotacion;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AlumnoSerializer extends StdSerializer<Alumno>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlumnoSerializer() {
		this(null);
	}

	protected AlumnoSerializer(Class<Alumno> t) {
		super(t);
	}

	@Override
	public void serialize(Alumno alumno, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		Format formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		jgen.writeStartObject();
		if (alumno.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", alumno.getId());
		}
		jgen.writeStringField("nombre", alumno.getNombre());
		jgen.writeStringField("apellido", alumno.getApellido());

		Apoderado apoderado = alumno.getApoderado();
		jgen.writeObjectFieldStart("apoderado");
		jgen.writeNumberField("id", apoderado.getId());
		jgen.writeStringField("nombre", apoderado.getNombre());
		jgen.writeStringField("apellido", apoderado.getApellido());
		jgen.writeEndObject();
		
		Curso curso = alumno.getCurso();
		jgen.writeObjectFieldStart("curso");
		jgen.writeNumberField("id", curso.getId());
		jgen.writeNumberField("grado", curso.getGrado());
		jgen.writeStringField("nivel", curso.getNivel());
		jgen.writeStringField("clase", curso.getClase());
		jgen.writeEndObject();
		
		jgen.writeEndObject();
	}
}
