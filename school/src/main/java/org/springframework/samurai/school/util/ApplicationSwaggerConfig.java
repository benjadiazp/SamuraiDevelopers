/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samurai.school.util;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Java config for Springfox swagger documentation plugin
 * 
 * @author Samurai Developers
 *
 */

@Configuration
@EnableSwagger2
@ComponentScan(basePackages="org.springframework.samurai.school.rest")
public class ApplicationSwaggerConfig {

   @Bean
   public Docket customDocket(){
      return new Docket(DocumentationType.SWAGGER_2)
    		  .select()
              .apis(RequestHandlerSelectors.any())
              .paths(PathSelectors.any())
              .build()
              .apiInfo(getApiInfo());
   }

   private ApiInfo getApiInfo() {
	   return new ApiInfo(
		"REST School backend Api Documentation",
		"This is REST API documentation of the Spring School backend. If authentication is enabled, when calling the APIs use admin/admin",
		"1.0",
		"School backend terms of service",
		new Contact(
				"Samurai Developers",
				"https://github.com/muimuiasd/SamuraiDevelopers",
				"email por definir"),
		"Apache 2.0",
		"http://www.apache.org/licenses/LICENSE-2.0");
   }


}
