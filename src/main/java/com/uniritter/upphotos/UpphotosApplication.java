package com.uniritter.upphotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "bo"})
@EntityScan(basePackages = "model")
@EnableJpaRepositories("repository")
public class UpphotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpphotosApplication.class, args);
	}

}
