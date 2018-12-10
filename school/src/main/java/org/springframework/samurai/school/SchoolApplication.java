package org.springframework.samurai.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.samurai.school.repository.*;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AlumnoRepository.class)
public class SchoolApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SchoolApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
}
