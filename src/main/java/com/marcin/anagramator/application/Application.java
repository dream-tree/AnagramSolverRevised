package com.marcin.anagramator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starting point of the application.
 * 
 * @author dream-tree
 * @version 3.00, June-July 2018
 */
@SpringBootApplication
@ComponentScan(basePackages="com.marcin.anagramator.*")
@EntityScan(basePackages="com.marcin.anagramator.domain")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
