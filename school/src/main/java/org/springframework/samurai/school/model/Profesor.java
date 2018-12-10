package org.springframework.samurai.school.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samurai.school.rest.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "profesor")
@JsonSerialize(using = ProfesorSerializer.class)
@JsonDeserialize(using = ProfesorDeserializer.class)
public class Profesor extends Persona {
	
}
