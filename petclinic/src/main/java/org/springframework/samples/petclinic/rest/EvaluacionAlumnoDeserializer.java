package org.springframework.samples.petclinic.rest;

import java.io.IOException;
import org.springframework.samples.petclinic.model.*;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
public class EvaluacionAlumnoDeserializer extends StdDeserializer<EvaluacionAlumno>{
	
	public EvaluacionAlumnoDeserializer() {
		this(null); 
	}
	
	public EvaluacionAlumnoDeserializer(Class<EvaluacionAlumno> evAl) {
		super(evAl);
	}
	
	@Override
	public EvaluacionAlumno deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		EvaluacionAlumno evaluacionAlumno = new EvaluacionAlumno();
		Alumno alumno = new Alumno();
		Evaluacion evaluacion = new Evaluacion(); 

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode node_al = node.get("alumno");
		JsonNode node_ev = node.get("evaluacion");
		alumno = mapper.treeToValue(node_al, Alumno.class);
		evaluacion = mapper.treeToValue(node_ev, Evaluacion.class);
		
		int evaluacionAlumnoId = node.get("id").asInt(); 
		int nota = node.get("nota").asInt(); 
		
		if(!(evaluacionAlumnoId == 0)) {
			evaluacionAlumno.setId(evaluacionAlumnoId);
		}
		
		evaluacionAlumno.setNota(nota);
		evaluacionAlumno.setAlumno(alumno);
		evaluacionAlumno.setEvaluacion(evaluacion); 
		return evaluacionAlumno; 
	}
}

