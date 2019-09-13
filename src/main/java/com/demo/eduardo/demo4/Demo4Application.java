package com.demo.eduardo.demo4;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;


@SpringBootApplication
public class Demo4Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo4Application.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		// Get new specific session
		AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();

		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}

	// Messages bean moved to a line in application.properties file
	
}
