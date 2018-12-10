package org.springframework.samurai.school.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samurai.school.model.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class MensajeSerializer extends StdSerializer<Mensaje> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensajeSerializer() {
		this(null);
	}
	
	protected MensajeSerializer(Class<Mensaje> a) {
		super(a);
	}
	
	@Override
	public void serialize(Mensaje mensaje, JsonGenerator jgen, SerializerProvider provider) throws IOException{
		Format formatter = new SimpleDateFormat("yyyy/MM/dd");
		jgen.writeStartObject();
		if(mensaje.getId()==null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", mensaje.getId());
		} 
		jgen.writeStringField("texto", mensaje.getTexto());
		jgen.writeStringField("fecha", formatter.format(mensaje.getFecha()));

		Profesor profesor = mensaje.getProfesor();
		jgen.writeObjectFieldStart("profesor");
		jgen.writeNumberField("id", profesor.getId());
		jgen.writeStringField("nombre", profesor.getNombre());
		jgen.writeStringField("apellido",profesor.getApellido());
		jgen.writeEndObject();

		Apoderado apoderado = mensaje.getApoderado();
		jgen.writeObjectFieldStart("apoderado");
		jgen.writeNumberField("id", apoderado.getId());
		jgen.writeStringField("nombre", apoderado.getNombre());
		jgen.writeStringField("apellido",apoderado.getApellido());
		jgen.writeEndObject();
		
		jgen.writeEndObject();
		
	}
}
