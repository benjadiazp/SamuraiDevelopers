package org.springframework.samples.petclinic.model;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.rest.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name = "apoderado")
@JsonSerialize(using = ApoderadoSerializer.class)
@JsonDeserialize(using = ApoderadoDeserializer.class)
public class Apoderado extends Persona {
	
	
}
