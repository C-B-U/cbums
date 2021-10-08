package com.cbums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CbumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbumsApplication.class, args);
	}

}
