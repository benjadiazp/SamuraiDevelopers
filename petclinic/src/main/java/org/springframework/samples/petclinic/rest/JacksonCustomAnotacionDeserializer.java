package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.Alumno;
import org.springframework.samples.petclinic.model.Anotacion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class JacksonCustomAnotacionDeserializer extends StdDeserializer<Anotacion>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JacksonCustomAnotacionDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Anotacion deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException{
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Anotacion anotacion = new Anotacion();
		Alumno alumno = new Alumno();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser); //nodo anotacion
		JsonNode alumno_node = node.get("alumno"); //nodo alumno
		alumno = mapper.treeToValue(alumno_node, Alumno.class);
		int anotacionId = node.get("id").asInt();
		String texto = node.get("texto").asText(null);
		
		if(!(anotacionId==0)) {
			anotacion.setId(anotacionId);
		}
		anotacion.setTexto(texto);
		anotacion.setAlumno(alumno);
		return anotacion;
	}
}
