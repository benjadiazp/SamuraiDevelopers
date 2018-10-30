package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.*;

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
		jgen.writeStartObject();
		if (alumno.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", alumno.getId());
		}
		jgen.writeStringField("name", alumno.getNombre());

		Apoderado apoderado = alumno.getApoderado();
		jgen.writeObjectFieldStart("apoderado");
		jgen.writeNumberField("id", apoderado.getId());
		jgen.writeStringField("nombre", apoderado.getNombre());
		jgen.writeStringField("apellido", apoderado.getApellido());
		jgen.writeEndObject();
		jgen.writeArrayFieldStart("anotaciones");
		for (Anotacion anotacion : alumno.getAnotaciones()) {
			jgen.writeStartObject();
			jgen.writeNumberField("id", anotacion.getId());
			// Pendiente: rellenar con otros datos.
			jgen.writeEndObject();
		}
		jgen.writeEndArray();
		jgen.writeEndObject();
	}
}
