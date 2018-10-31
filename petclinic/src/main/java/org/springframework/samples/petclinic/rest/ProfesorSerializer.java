package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.Profesor;
import org.springframework.samples.petclinic.model.Anotacion;

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
		jgen.writeStringField("name", profesor.getNombre());
/*
		Anotacion anotacion = (Anotacion) profesor.getAnotaciones();
		jgen.writeObjectFieldStart("anotacion");
		jgen.writeNumberField("id", anotacion.getId()); 
		jgen.writeStringField("texto", anotacion.getTexto());
		jgen.writeNumberField("tipo", anotacion.getTipo());
		*/
		//jgen.writeStringField("fecha", anotacion.getFecha());
		/*
		jgen.writeArrayFieldStart("anotaciones");
		for (Anotacion anotacion : profesor.getAnotaciones()) {
			//jgen.writeStartObject();
			jgen.writeObjectFieldStart("anotacion");
			jgen.writeNumberField("id", anotacion.getId()); 
			jgen.writeStringField("texto", anotacion.getTexto());
			jgen.writeNumberField("tipo", anotacion.getTipo());
			// Pendiente: rellenar con otros datos.
			jgen.writeEndObject();
		}*/
		//jgen.writeEndArray();
		jgen.writeEndObject();
	}
}
