package org.springframework.samples.petclinic.rest;
import java.io.IOException;

import org.springframework.samples.petclinic.model.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AsignaturaSerializer extends StdSerializer<Asignatura>{
	private static final long serialVersionUID = 1L;

	public AsignaturaSerializer() {
		this(null);
	}

	protected AsignaturaSerializer(Class<Asignatura> t) {
		super(t);
	}

	@Override
	public void serialize(Asignatura asignatura, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		if (asignatura.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", asignatura.getId());
		}
		jgen.writeStringField("name", asignatura.getNombre());
		// revisar esta parte hacia abajo no estoy seguro si es correcto como lo deje
		Profesor profesor = new Profesor();
		jgen.writeObjectFieldStart("profesor");
		jgen.writeNumberField("idprofesor", profesor.getId());

		jgen.writeEndObject();
	
	
	}

}