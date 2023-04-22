package com.example.gof;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
