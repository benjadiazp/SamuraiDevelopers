package org.springframework.samurai.school.rest;
import java.io.IOException;

import org.springframework.samurai.school.model.Curso;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
@SuppressWarnings("serial")
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
		JsonNode node = parser.getCodec().readTree(parser);
		int cursoId = node.get("id").asInt();
		int grado = (int) node.get("grado").asInt();
		String nivel = node.get("nivel").asText(null);
		String clase = node.get("clase").asText();
		if (!(cursoId == 0)) {
			curso.setId(cursoId);
		}
		curso.setGrado(grado);
		curso.setNivel(nivel);
		curso.setClase(clase);
		return curso;
	}
}
