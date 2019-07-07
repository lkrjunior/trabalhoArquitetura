package com.uniritter.upphotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class UpphotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpphotosApplication.class, args);
	}

}
