package com.marcin.anagramator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Starting point of the application.
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */

/*
 * Version to run without embedded Tomcat web server (for building a war file
 * and deploying it).
 */
 
/*@SpringBootApplication
public class ApplicationServerNotEmbedded extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationServerNotEmbedded.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationServerNotEmbedded.class, args);
	}
}*/