package com.spring.priceGeneratorApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceGeneratorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceGeneratorAppApplication.class, args);
	}

}
