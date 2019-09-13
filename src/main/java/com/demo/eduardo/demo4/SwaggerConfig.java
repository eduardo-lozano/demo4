package com.demo.eduardo.demo4;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  // To tell the machine this is a Spring bean
@EnableSwagger2

public class SwaggerConfig {

	// Create a Docket Bean, to specify documentation of type SWAGGER_2
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2);
	}
}