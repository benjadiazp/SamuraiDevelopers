package org.springframework.samples.petclinic.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samples.petclinic.model.Alumno;
import org.springframework.samples.petclinic.model.Anotacion;
import org.springframework.samples.petclinic.model.Apoderado;

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
		
		//errores que no tengo idea qué son 
		Alumno alumno = anotacion.getAlumno();
		jgen.writeObjectFieldStart("alumno");
		jgen.writeNumberField("id", alumno.getId());
		jgen.writeStringField("nombre", alumno.getNombre());
		jgen.writeStringField("apellido",alumno.getApellido());
		jgen.writeEndObject();
		jgen.writeArrayFieldStart("alumno");
		/*for (Anotacion anotacion : alumno.getAnotaciones()) {
			jgen.writeStartObject();
			jgen.writeNumberField("id", anotacion.getId());
			// Pendiente: rellenar con otros datos.
			jgen.writeEndObject();
		} */ 
		jgen.writeEndArray();
		jgen.writeEndObject();
		
	}
}
