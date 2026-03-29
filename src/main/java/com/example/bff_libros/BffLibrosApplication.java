package com.example.bff_libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BffLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffLibrosApplication.class, args);
	}

}
