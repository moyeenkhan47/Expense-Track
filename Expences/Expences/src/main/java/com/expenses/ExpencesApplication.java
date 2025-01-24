package com.expenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExpencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpencesApplication.class, args);
	}

}
