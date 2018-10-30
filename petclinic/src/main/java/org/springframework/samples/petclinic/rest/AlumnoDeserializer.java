package org.springframework.samples.petclinic.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.samples.petclinic.model.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class AlumnoDeserializer extends StdDeserializer<Alumno>{
	public AlumnoDeserializer() {
		this(null);
	}

	public AlumnoDeserializer(Class<Pet> t) {
		super(t);
	}

	@Override
	public Alumno deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Alumno alumno = new Alumno();
		Apoderado apoderado = new Apoderado();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode apoderado_node = node.get("apoderado");
		apoderado = mapper.treeToValue(apoderado_node, Apoderado.class);
		int alumnoId = node.get("id").asInt();
		String nombre = node.get("nombre").asText(null);

		if (!(alumnoId == 0)) {
			alumno.setId(alumnoId);
		}
		alumno.setNombre(nombre);
		alumno.setApoderado(apoderado);
		return alumno;
	}
}
