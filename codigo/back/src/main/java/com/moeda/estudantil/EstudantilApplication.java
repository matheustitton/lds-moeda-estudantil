package com.moeda.estudantil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EstudantilApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstudantilApplication.class, args);
	}

}
