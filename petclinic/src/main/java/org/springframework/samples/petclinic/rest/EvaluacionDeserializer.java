package org.springframework.samples.petclinic.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.samples.petclinic.model.Asignatura;
import org.springframework.samples.petclinic.model.Evaluacion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class EvaluacionDeserializer extends StdDeserializer<Evaluacion>{
	public EvaluacionDeserializer() {
		this(null);
	}

	public EvaluacionDeserializer(Class<Evaluacion> t) {
		super(t);
	}

	@Override
	public Evaluacion deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Evaluacion evaluacion = new Evaluacion();
		Asignatura asignatura = new Asignatura();
		ObjectMapper mapper = new ObjectMapper();
		Date fecha = null;
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode asignatura_node = node.get("asignatura");
		asignatura = mapper.treeToValue(asignatura_node, Asignatura.class);
		int evaluacionId = node.get("id").asInt();

		if (!(evaluacionId == 0)) {
			evaluacion.setId(evaluacionId);
		}
		
		String fechaStr = node.get("fecha").asText(null);
		try {
			fecha = formatter.parse(fechaStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		evaluacion.setAsignatura(asignatura);
		evaluacion.setFecha(fecha);
		return evaluacion;
	}
}
