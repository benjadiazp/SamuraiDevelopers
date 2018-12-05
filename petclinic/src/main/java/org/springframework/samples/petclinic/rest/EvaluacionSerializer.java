package org.springframework.samples.petclinic.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samples.petclinic.model.Alumno;
import org.springframework.samples.petclinic.model.Anotacion;
import org.springframework.samples.petclinic.model.Apoderado;
import org.springframework.samples.petclinic.model.Asignatura;
import org.springframework.samples.petclinic.model.Curso;
import org.springframework.samples.petclinic.model.Evaluacion;

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

		Asignatura asignatura = evaluacion.getAsignatura();
		jgen.writeObjectFieldStart("asignatura");
		jgen.writeNumberField("id", asignatura.getId());
		jgen.writeStringField("nombre", asignatura.getNombre());
		jgen.writeEndObject();
		jgen.writeEndObject();
	}
}
