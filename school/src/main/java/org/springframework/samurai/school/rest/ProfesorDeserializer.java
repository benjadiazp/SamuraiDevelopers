package org.springframework.samurai.school.rest;

import java.io.IOException;

import org.springframework.samurai.school.model.Profesor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ProfesorDeserializer extends StdDeserializer<Profesor> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfesorDeserializer() {
		this(null);
	}
	
	public ProfesorDeserializer(Class<Profesor> p) {
		super(p);
	}
	
	@Override
	public Profesor deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException{

		Profesor profesor = new Profesor();
		JsonNode node = parser.getCodec().readTree(parser); //nodo profesor
		int id = node.get("id").asInt();
		String nombre = node.get("nombre").asText(null);
		String apellido = node.get("apellido").asText(null);
		
		if(!(id==0)) {
			profesor.setId(id);
		}
		profesor.setNombre(nombre);
		profesor.setApellido(apellido);
		return profesor;
	}
}
