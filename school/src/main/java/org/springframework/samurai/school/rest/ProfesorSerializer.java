package org.springframework.samurai.school.rest;

import java.io.IOException;

import org.springframework.samurai.school.model.Profesor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ProfesorSerializer extends StdSerializer<Profesor>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfesorSerializer() {
		this(null);
	}
	
	protected ProfesorSerializer(Class<Profesor> p) {
		super(p);
	}
	
	@Override
	public void serialize(Profesor profesor, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		jgen.writeStartObject();
		if (profesor.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", profesor.getId());
		}
		jgen.writeStringField("nombre", profesor.getNombre());
		jgen.writeStringField("apellido", profesor.getApellido());

		jgen.writeEndObject();
	}
}
