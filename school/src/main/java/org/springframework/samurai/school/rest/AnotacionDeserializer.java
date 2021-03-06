package org.springframework.samurai.school.rest;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.samurai.school.model.*;

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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Anotacion anotacion = new Anotacion();
		Alumno alumno = new Alumno();
		Profesor profesor = new Profesor();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser); //nodo anotacion
		JsonNode alumno_node = node.get("alumno");
		JsonNode profesor_node = node.get("profesor");
		alumno = mapper.treeToValue(alumno_node, Alumno.class);
		profesor = mapper.treeToValue(profesor_node, Profesor.class);
		
		int anotacionId = node.get("id").asInt();
		String texto = node.get("texto").asText(null);
		int tipo = node.get("tipo").asInt();
		Date fecha = null;
		String fechaStr = node.get("fecha").asText(null);
		try {
			fecha = formatter.parse(fechaStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		if(!(anotacionId==0)) {
			anotacion.setId(anotacionId);
		}
		anotacion.setTexto(texto);
		anotacion.setTipo(tipo);
		anotacion.setFecha(fecha);
		anotacion.setAlumno(alumno);
		anotacion.setProfesor(profesor);
		return anotacion;
	}
}


