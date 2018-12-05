package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.Alumno;
import org.springframework.samples.petclinic.model.Evaluacion;
import org.springframework.samples.petclinic.model.EvaluacionAlumno;

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
		jgen.writeStartObject();
		
		if(evaluacionAlumno.getId()==null) {
			jgen.writeNullField("id");
		}else {
			jgen.writeNumberField("id", evaluacionAlumno.getId());
		}
		
		Alumno alumno = evaluacionAlumno.getAlumno();
		Evaluacion evaluacion = evaluacionAlumno.getEvaluacion();
		
		jgen.writeObjectFieldStart("alumno");
		jgen.writeNumberField("id", alumno.getId());
		jgen.writeStringField("nombre", alumno.getNombre());
		jgen.writeStringField("apellido", alumno.getApellido());
		jgen.writeEndObject();
		
		jgen.writeObjectFieldStart("evaluacion");
		jgen.writeNumberField("id", evaluacion.getId());
		//aqui debería haber un jgen de fecha
		jgen.writeEndObject();
		jgen.writeEndObject();
	}
}
