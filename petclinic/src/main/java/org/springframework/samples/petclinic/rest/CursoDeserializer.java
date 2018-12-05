package org.springframework.samples.petclinic.rest;
import java.io.IOException;


import org.springframework.samples.petclinic.model.Curso;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
public class CursoDeserializer extends StdDeserializer<Curso>{
	public CursoDeserializer() {
		this(null);
	}

	public CursoDeserializer(Class<Curso> t) {
		super(t);
	}

	@Override
	public Curso deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		Curso curso = new Curso();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser);
		int cursoId = node.get("id").asInt();
		int grado = (int) node.get("grado").asInt();
		String nivel = node.get("nivel").asText(null);
		String clase = node.get("clase").asText();
		if (!(cursoId == 0)) {
			curso.setId(cursoId);
		}
		curso.setClase(clase);
		curso.setNivel(nivel);
		curso.setGrado(grado);
		return curso;
	}
}
