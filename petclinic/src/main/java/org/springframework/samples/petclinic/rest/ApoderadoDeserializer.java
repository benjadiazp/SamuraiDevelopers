package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ApoderadoDeserializer extends StdDeserializer<Apoderado>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApoderadoDeserializer() {
		this(null);
	}

	public ApoderadoDeserializer(Class<Apoderado> t) {
		super(t);
	}

	@Override
	public Apoderado deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Apoderado apoderado = new Apoderado();
		int id = node.get("id").asInt();
		String nombre = node.get("nombre").asText(null);
		String apellido = node.get("apellido").asText(null);

		if (!(id == 0)) {
			apoderado.setId(id);
		}
        apoderado.setNombre(nombre);
        apoderado.setApellido(apellido);
		return apoderado;
	}
	
}
