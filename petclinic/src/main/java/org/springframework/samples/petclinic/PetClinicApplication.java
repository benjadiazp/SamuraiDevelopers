package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.samples.petclinic.repository.*;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AlumnoRepository.class)
public class PetClinicApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PetClinicApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}
}
