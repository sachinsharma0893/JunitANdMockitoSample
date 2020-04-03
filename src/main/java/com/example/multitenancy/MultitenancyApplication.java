package com.example.multitenancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class MultitenancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultitenancyApplication.class, args);
	}

}
