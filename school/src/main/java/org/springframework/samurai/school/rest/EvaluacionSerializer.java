package org.springframework.samurai.school.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samurai.school.model.Asignatura;
import org.springframework.samurai.school.model.Evaluacion;
import org.springframework.samurai.school.model.Profesor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class EvaluacionSerializer extends StdSerializer<Evaluacion> {
	private static final long serialVersionUID = 1L;

	public EvaluacionSerializer() {
		this(null);
	}

	protected EvaluacionSerializer(Class<Evaluacion> t) {
		super(t);
	}

	@Override
	public void serialize(Evaluacion evaluacion, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		Format formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		jgen.writeStartObject();
		if (evaluacion.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", evaluacion.getId());
		}
		jgen.writeStringField("fecha", formatter.format(evaluacion.getFecha()));
		
		Asignatura asignatura = evaluacion.getAsignatura();
		jgen.writeObjectFieldStart("asignatura");
		jgen.writeNumberField("id", asignatura.getId());
		jgen.writeStringField("nombre", asignatura.getNombre());
		
		Profesor profesor = asignatura.getProfesor();
		jgen.writeObjectFieldStart("profesor");
		jgen.writeNumberField("id", profesor.getId());
		jgen.writeStringField("nombre", profesor.getNombre());
		jgen.writeStringField("apellido", profesor.getApellido());
		jgen.writeEndObject();	// profesor
		
		jgen.writeEndObject();	// asignatura
		jgen.writeEndObject();	// evaluacion
	}
}
