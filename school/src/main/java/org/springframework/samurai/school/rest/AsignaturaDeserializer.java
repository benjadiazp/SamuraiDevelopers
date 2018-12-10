package org.springframework.samurai.school.rest;
import java.io.IOException;

import org.springframework.samurai.school.model.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
@SuppressWarnings("serial")
public class AsignaturaDeserializer extends StdDeserializer<Asignatura>{
	public AsignaturaDeserializer() {
		this(null);
	}

	public AsignaturaDeserializer(Class<Asignatura> t) {
		super(t);
	}

	@Override
	public Asignatura deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		Asignatura asignatura = new Asignatura();
		Profesor profesor = new Profesor();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode profesor_node = node.get("profesor");
		profesor= mapper.treeToValue(profesor_node, Profesor.class);
		
		int asignaturaId = node.get("id").asInt();
		String nombre = node.get("nombre").asText(null);
		
		if (!(asignaturaId == 0)) {
			asignatura.setId(asignaturaId);
		}
		asignatura.setNombre(nombre);
		asignatura.setProfesor(profesor);
		return asignatura;
	}
}
