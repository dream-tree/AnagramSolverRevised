package com.marcin.anagramator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starting point of the application.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */

/* Version to run without embedded Tomcat web server 
 * (for building a war file and deploying it). 
 */
@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages="com.marcin.anagramator.*")
public class ApplicationServerNotEmbedded extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationServerNotEmbedded.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationServerNotEmbedded.class, args);
	}
}