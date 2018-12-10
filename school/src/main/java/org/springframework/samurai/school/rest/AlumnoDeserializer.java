package org.springframework.samurai.school.rest;

import java.io.IOException;

import org.springframework.samurai.school.model.Alumno;
import org.springframework.samurai.school.model.Apoderado;
import org.springframework.samurai.school.model.Curso;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class AlumnoDeserializer extends StdDeserializer<Alumno> {
	public AlumnoDeserializer() {
		this(null);
	}

	public AlumnoDeserializer(Class<Alumno> t) {
		super(t);
	}

	@Override
	public Alumno deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		
		Alumno alumno = new Alumno();
		Apoderado apoderado = new Apoderado();
		Curso curso = new Curso();
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode apoderado_node = node.get("apoderado");
		JsonNode curso_node = node.get("curso");
		apoderado = mapper.treeToValue(apoderado_node, Apoderado.class);
		curso = mapper.treeToValue(curso_node, Curso.class);
		int alumnoId = node.get("id").asInt();
		String nombre = node.get("nombre").asText(null);
		String apellido = node.get("apellido").asText(null);

		if (!(alumnoId == 0)) {
			alumno.setId(alumnoId);
		}
		alumno.setNombre(nombre);
		alumno.setApellido(apellido);
		alumno.setApoderado(apoderado);
		alumno.setCurso(curso);
		return alumno;
	}
}
