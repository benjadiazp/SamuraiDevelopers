package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.Alumno;
import org.springframework.samples.petclinic.model.Anotacion;
import org.springframework.samples.petclinic.model.Profesor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Profesor profesor = new Profesor();
		Anotacion anotacion = new Anotacion();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser); //nodo profesor
		JsonNode anotacion_node = node.get("anotacion"); //nodo anotacon
		profesor = mapper.treeToValue(anotacion_node, Profesor.class);
		int anotacionId = node.get("id").asInt();
		String texto = node.get("texto").asText(null);
		
		if(!(anotacionId==0)) {
			anotacion.setId(anotacionId);
		}
		anotacion.setTexto(texto);
		anotacion.setProfesor(profesor);
		return profesor;
	}
}
