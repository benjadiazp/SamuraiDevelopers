package org.springframework.samples.petclinic.rest;

import java.io.IOException;


import org.springframework.samples.petclinic.model.Curso;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CursoSerializer extends StdSerializer<Curso>{
	private static final long serialVersionUID = 1L;

	public CursoSerializer() {
		this(null);
	}

	protected CursoSerializer(Class<Curso> t) {
		super(t);
	}

	@Override
	public void serialize(Curso curso, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		if (curso.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", curso.getId());
		}
		jgen.writeNumberField("grado",curso.getGrado());
		jgen.writeStringField("nivel",curso.getNivel());
		jgen.writeStringField("clase",curso.getClase());

		jgen.writeEndObject();

		
		}

	}

