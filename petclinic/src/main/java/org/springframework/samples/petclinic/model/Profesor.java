package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.samples.petclinic.rest.*;

@Entity
@Table(name = "profesor")
@JsonSerialize(using = ProfesorSerializer.class)
@JsonDeserialize(using = ProfesorDeserializer.class)
public class Profesor extends Persona {
	
}
