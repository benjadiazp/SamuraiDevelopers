package org.springframework.samurai.school.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samurai.school.model.Alumno;
import org.springframework.samurai.school.model.Anotacion;
import org.springframework.samurai.school.model.Apoderado;
import org.springframework.samurai.school.model.Curso;
import org.springframework.samurai.school.model.Profesor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AnotacionSerializer extends StdSerializer<Anotacion> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnotacionSerializer() {
		this(null);
	}
	
	protected AnotacionSerializer(Class<Anotacion> a) {
		super(a);
	}
	
	@Override
	public void serialize(Anotacion anotacion, JsonGenerator jgen, SerializerProvider provider) throws IOException{
		Format formatter = new SimpleDateFormat("yyyy/MM/dd");
		jgen.writeStartObject();
		if(anotacion.getId()==null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", anotacion.getId());
		} 
		jgen.writeStringField("texto", anotacion.getTexto());
		jgen.writeNumberField("tipo", anotacion.getTipo());
		jgen.writeStringField("fecha", formatter.format(anotacion.getFecha()));
		
		Alumno alumno = anotacion.getAlumno();
		jgen.writeObjectFieldStart("alumno");
		jgen.writeNumberField("id", alumno.getId());
		jgen.writeStringField("nombre", alumno.getNombre());
		jgen.writeStringField("apellido",alumno.getApellido());
		
		
		Curso curso = alumno.getCurso();
		jgen.writeObjectFieldStart("curso");
		jgen.writeNumberField("id",curso.getId());
		jgen.writeNumberField("grado",curso.getGrado());
		jgen.writeStringField("nivel", curso.getNivel());
		jgen.writeStringField("clase", curso.getClase());
		jgen.writeEndObject();
		
		Apoderado apoderado = alumno.getApoderado();
		jgen.writeObjectFieldStart("apoderado");
		jgen.writeNumberField("id",apoderado.getId());
		jgen.writeStringField("nombre", apoderado.getNombre());
		jgen.writeStringField("apellido", apoderado.getApellido());
		jgen.writeEndObject();
		
		jgen.writeEndObject();	// alumno

		Profesor profesor = anotacion.getProfesor();
		jgen.writeObjectFieldStart("profesor");
		jgen.writeNumberField("id", profesor.getId());
		jgen.writeStringField("nombre", profesor.getNombre());
		jgen.writeStringField("apellido",profesor.getApellido());
		jgen.writeEndObject();	// profesor

		jgen.writeEndObject();	// anotacion
		
	}
}
