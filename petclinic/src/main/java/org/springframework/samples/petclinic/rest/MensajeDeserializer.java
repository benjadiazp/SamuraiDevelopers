package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class MensajeDeserializer extends StdDeserializer<Mensaje> {

	private static final long serialVersionUID = 1L;

	public MensajeDeserializer() {
		this(null);
	}
	
	public MensajeDeserializer(Class<Mensaje> a) {
		super(a);
	}

	@Override
	public Mensaje deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException{
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Mensaje mensaje = new Mensaje();
		Profesor profesor = new Profesor();
		Apoderado apoderado = new Apoderado();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode profesor_node = node.get("profesor");
		profesor = mapper.treeToValue(profesor_node, Profesor.class);
		
		JsonNode node2 = parser.getCodec().readTree(parser);
		JsonNode apoderado_node = node2.get("apoderado");
		apoderado = mapper.treeToValue(apoderado_node, Apoderado.class);
		
		int mensajeId = node.get("id").asInt();
		String texto = node.get("texto").asText(null);
		
		if(!(mensajeId==0)) {
			mensaje.setId(mensajeId);
		}
		mensaje.setTexto(texto);
		mensaje.setProfesor(profesor);
		mensaje.setApoderado(apoderado);
		return mensaje;
	}
}
