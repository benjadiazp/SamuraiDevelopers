package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ApoderadoSerializer extends StdSerializer<Apoderado>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApoderadoSerializer() {
		this(null);
	}

	public ApoderadoSerializer(Class<Apoderado> t) {
		super(t);
	}

	@Override
	public void serialize(Apoderado apoderado, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		

		jgen.writeStartObject();
		if (apoderado.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", apoderado.getId());
		}

		jgen.writeStringField("nombre", apoderado.getNombre());
		jgen.writeStringField("apellido", apoderado.getApellido());
		// write alumnos array
		jgen.writeArrayFieldStart("alumnos");
		jgen.writeEndArray(); // alumnos
		jgen.writeEndObject(); // apoderado
		
	}
	
}
