package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class AnotacionDeserializer extends StdDeserializer<Anotacion> {

	private static final long serialVersionUID = 1L;

	public AnotacionDeserializer() {
		this(null);
	}
	
	public AnotacionDeserializer(Class<Anotacion> a) {
		super(a);
	}

	@Override
	public Anotacion deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException{
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Anotacion anotacion = new Anotacion();
		Alumno alumno = new Alumno();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser); //nodo anotacion
		System.out.println(mapper == null);
		System.out.println("Deserializer malo.");
		int anotacionId = node.get("id").asInt();
		String texto = node.get("texto").asText(null);
		if(!(anotacionId==0)) {
			anotacion.setId(anotacionId);
			anotacion.setTipo(1);
		}
		anotacion.setTexto(texto);
		return anotacion;
	}
}


