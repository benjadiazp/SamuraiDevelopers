package org.springframework.samurai.school.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samurai.school.model.Alumno;
import org.springframework.samurai.school.model.Apoderado;
import org.springframework.samurai.school.model.Asignatura;
import org.springframework.samurai.school.model.Curso;
import org.springframework.samurai.school.model.Evaluacion;
import org.springframework.samurai.school.model.EvaluacionAlumno;
import org.springframework.samurai.school.model.Profesor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class EvaluacionAlumnoSerializer extends StdSerializer<EvaluacionAlumno>{
	private static final long serialVersionUID = 1L;

	public EvaluacionAlumnoSerializer() {
		this(null);
	}
	
	protected EvaluacionAlumnoSerializer(Class<EvaluacionAlumno> evAl) {
		super(evAl); 
	}
	
	@Override
	public void serialize(EvaluacionAlumno evaluacionAlumno, JsonGenerator jgen, SerializerProvider provider) throws IOException{
		Format formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		jgen.writeStartObject();
		
		if(evaluacionAlumno.getId()==null) {
			jgen.writeNullField("id");
		}else {
			jgen.writeNumberField("id", evaluacionAlumno.getId());
		}
		jgen.writeNumberField("nota", evaluacionAlumno.getNota());
		
		Alumno alumno = evaluacionAlumno.getAlumno();
		jgen.writeObjectFieldStart("alumno");
		jgen.writeNumberField("id", alumno.getId());
		jgen.writeStringField("nombre", alumno.getNombre());
		jgen.writeStringField("apellido", alumno.getApellido());
		
		Apoderado apoderado = alumno.getApoderado();
		jgen.writeObjectFieldStart("apoderado");
		jgen.writeNumberField("id", apoderado.getId());
		jgen.writeStringField("nombre", apoderado.getNombre());
		jgen.writeStringField("apellido", apoderado.getApellido());
		jgen.writeEndObject();	// apoderado
		
		Curso curso = alumno.getCurso();
		jgen.writeObjectFieldStart("curso");
		jgen.writeNumberField("id", curso.getId());
		jgen.writeNumberField("grado", curso.getGrado());
		jgen.writeStringField("nivel", curso.getNivel());
		jgen.writeStringField("clase", curso.getClase());
		jgen.writeEndObject();	// curso
		
		jgen.writeEndObject();	// alumno
		
		Evaluacion evaluacion = evaluacionAlumno.getEvaluacion();
		jgen.writeObjectFieldStart("evaluacion");
		jgen.writeNumberField("id", evaluacion.getId());
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
		
		jgen.writeEndObject();	// evaluacionAlumno
	}
}
